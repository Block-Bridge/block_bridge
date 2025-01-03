package me.quickscythe.blockbridge.web.token;

import me.quickscythe.blockbridge.BlockBridgeApi;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Request;

import java.util.*;

public class TokenManager {

    private final Map<String, Token> TOKENS = new HashMap<>();
    private final BlockBridgeApi bba;

    public TokenManager(BlockBridgeApi bba) {
        this.bba = bba;
    }

    public String requestNewToken(String ip) {
        if (!bba.getConfig().getData().has("allow")) {
            bba.getConfig().getData().put("allow", new JSONArray());
        }
        bba.getLogger().info("Requesting token for {}", ip);
        boolean allowed = false;
        JSONObject data = bba.getConfig().getData();
        for (int i = 0; i != data.getJSONArray("allow").length(); i++) {
            if (data.getJSONArray("allow").getString(i).equals(ip)) {
                allowed = true;
                break;
            }
        }
        if (allowed || ip.equals("0:0:0:0:0:0:0:1") || ip.equals("127.0.0.1")) {
            String token = UUID.randomUUID().toString();
            while (TOKENS.containsKey(token)) token = UUID.randomUUID().toString();
            TOKENS.put(token, new Token(token, ip, bba));
            return token;
        }
        return null;
    }

    public void removeToken(String token) {
        TOKENS.remove(token);
    }

    public Collection<Token> getTokens() {
        return TOKENS.values();
    }

    public String[] getTokens(String ip) {
        List<String> tokens = new ArrayList<>();
        for (Map.Entry<String, Token> entry : TOKENS.entrySet()) {
            if (entry.getValue().getIp().equals(ip)) {
                tokens.add(entry.getKey());
            }
        }
        return tokens.toArray(String[]::new);
    }

    public boolean validToken(Token token, Request request) {
        return token != null && token.getIp().equals(request.ip()) && !token.isExpired();
    }


    public Token getToken(String token) {
        return TOKENS.getOrDefault(token, null);
    }
}
