package me.quickscythe.blockbridge.web;


import org.json.JSONObject;

public class Feedback {

    public static String json(String message) {
        return "{\"feedback\":\"" + message + "\"}";
    }

    public static class Errors {
        public static String json(String message) {
            return "{\"error\":\"" + message + "\"}";
        }
    }

    public static class Success {
        public static String json(String message) {
            return "{\"success\":\"" + message + "\"}";
        }
    }

    public static class Objects {
        public static String json(Object object){
            return new JSONObject(object).toString();
        }
    }
}
