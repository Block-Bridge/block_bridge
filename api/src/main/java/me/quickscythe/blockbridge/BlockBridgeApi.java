package me.quickscythe.blockbridge;

import me.quickscythe.blockbridge.api.config.ConfigFile;
import me.quickscythe.blockbridge.web.WebApp;
import me.quickscythe.blockbridge.web.token.TokenManager;
import org.json.JSONObject;
import org.slf4j.Logger;


public interface BlockBridgeApi {

    void validateToken();

    JSONObject appData(String path);

    JSONObject appData(String path, boolean validate);

    JSONObject apiData(String path);

    JSONObject postData(String path, JSONObject data);

    void allow(String ip);
    boolean isDebug();

    WebApp getWebApp();
    Logger getLogger();
    TokenManager getTokenManager();

    void init(boolean webapp);

    ConfigFile getConfig();

    int TOKEN_VALID_TIME();
    String URL();
    String APP_ENTRY();
    String API_ENTRY();
    String APP_VERSION();
    int WEB_PORT();


}
