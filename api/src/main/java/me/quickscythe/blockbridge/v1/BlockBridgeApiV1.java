package me.quickscythe.blockbridge.v1;

import me.quickscythe.blockbridge.BlockBridgeApi;
import me.quickscythe.blockbridge.BlockBridgePlugin;
import me.quickscythe.blockbridge.api.config.ConfigClass;
import me.quickscythe.blockbridge.storage.StorageManager;
import me.quickscythe.blockbridge.v1.webapp.WebAppV1;
import me.quickscythe.blockbridge.web.WebApp;
import me.quickscythe.blockbridge.web.token.TokenManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BlockBridgeApiV1 extends ConfigClass implements BlockBridgeApi {


    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final TokenManager tokenManager = new TokenManager(this);
    private boolean DEBUG = false;

    private WebApp WEB_APP = null;
    private String token = null;

    public BlockBridgeApiV1() {
        super(new BlockBridgePlugin(), "webapp");
        StorageManager.init(this);

    }

    @Override
    public TokenManager getTokenManager() {
        return tokenManager;
    }


    public void init(boolean webapp) {
        checkConfigDefaults();
        getConfig().save();
        if (webapp) WEB_APP = new WebAppV1(this);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown hook is running...");
            // Place your cleanup code here
            StorageManager.getStorage().save();
        }));
    }


    @Override
    public void validateToken() {
        logger.info("Validating Token: {}", token);
        JSONObject data = appData("check_token", false);
        if (data.has("error") || token == null) {
            logger.info("Token Invalid: {}", token == null ? "Token is null" : data.getString("error"));
            token = generateNewToken();
            logger.info("New Token: {}", token);
        }
    }

    private String generateNewToken() {
        try {
            logger.info("Generating new token");
            URL url = URI.create(URL() + APP_ENTRY() + "/token").toURL();
            String result = new Scanner(url.openStream(), StandardCharsets.UTF_8).useDelimiter("\\A").next();
            logger.info(result);
            return new JSONObject(result).getString("success");
        } catch (IOException | JSONException e) {
            throw new RuntimeException("Failed to generate new token. Shutting down");
        }
    }

    @Override
    public JSONObject apiData(String path) {
        try {
            URL url = URI.create(URL() + API_ENTRY() + "/" + path).toURL();
            return new JSONObject(new Scanner(url.openStream(), StandardCharsets.UTF_8).useDelimiter("\\A").next());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JSONObject postData(String path, JSONObject data) {
        validateToken();
        try {
            URL url = URI.create(URL() + APP_ENTRY() + "/" + APP_VERSION() + "/" + token + "/" + path).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Step 3: Set the request method to POST
            connection.setRequestMethod("POST");

            // Step 4: Set the request headers
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            // Step 5: Write the POST data to the output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = data.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Step 6: Read the response
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Response Body: " + response.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public JSONObject appData(String path) {
        return appData(path, true);
    }

    @Override
    public JSONObject appData(String path, boolean validate) {
        if (validate) validateToken();
        try {
            URL url = URI.create(URL() + APP_ENTRY() + "/" + APP_VERSION() + "/" + token + "/" + path).toURL();
            try (Scanner scanner = new Scanner(url.openStream(), StandardCharsets.UTF_8)) {
                JSONObject result = new JSONObject(scanner.useDelimiter("\\A").next());
                scanner.close();
                return result;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String URL() {
        return getConfig().getData().getString("api_url");
    }

    @Override
    public String APP_ENTRY() {
        return getConfig().getData().getString("app_entry_point");
    }

    @Override
    public String API_ENTRY() {
        return getConfig().getData().getString("api_entry_point");
    }

    @Override
    public String APP_VERSION() {
        return getConfig().getData().getString("app_version");
    }

    @Override
    public int WEB_PORT() {
        return getConfig().getData().getInt("web_port");
    }

    private void checkConfigDefaults() {

        setDefault("api_entry_point", "/api");
        setDefault("app_entry_point", "/app");
        setDefault("api_url", "http://localhost:8585");
        setDefault("app_version", "v1");
        setDefault("web_port", 8585);
        setDefault("token_valid_time", 24);
        setDefault("allow", new JSONArray());
    }

    @Override
    public void allow(String ip) {
        getConfig().getData().getJSONArray("allow").put(ip);
        getConfig().save();
    }

    @Override
    public boolean isDebug() {
        return DEBUG;
    }

    @Override
    public WebApp getWebApp() {
        return WEB_APP;
    }


    @Override
    public int TOKEN_VALID_TIME() {
        return getConfig().getData().getInt("token_valid_time");
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}

