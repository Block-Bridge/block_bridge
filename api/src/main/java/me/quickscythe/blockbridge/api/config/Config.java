package me.quickscythe.blockbridge.api.config;


import org.json.JSONObject;

/**
 * Config is an interface for ConfigFiles
 */
public interface Config {


    /**
     * Saves file
     */
    void save();

    /**
     * Get data in Config
     * @return JSONObject
     */
    JSONObject getData();

    /**
     * Set data in Config. This will wipe all existing data in the Config.
     * @param data
     */
    void setData(JSONObject data);

    /**
     * Resets the config to the stored defaults.
     */
    void reset();
}
