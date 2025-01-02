package me.quickscythe.blockbridge.fabric.utils;


import org.json.JSONObject;

public class Defaults {
    public static JSONObject config() {
        JSONObject config = new JSONObject();
        config.put("something", "something");
        return config;
    }
}
