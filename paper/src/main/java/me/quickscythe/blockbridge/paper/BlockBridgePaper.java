package me.quickscythe.blockbridge.paper;

import me.quickscythe.blockbridge.paper.listeners.ServerListener;
import me.quickscythe.blockbridge.paper.utils.BlockBridgePaperUtils;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

public class BlockBridgePaper extends JavaPlugin {
    
    private JSONObject CONFIG;
    public final String MOD_ID = "block_bridge";
    private final String CONFIG_FILE = "config/blockbridge.json";



    @Override
    public void onEnable() {
        //This will only init on Servers
        BlockBridgePaperUtils.initializeServer(this);
        BlockBridgePaperUtils.initializeLogger();
//        CONFIG = loadConfig();
//        configDefaults();



        //Register Events

        new ServerListener(this);
//        ServerLifecycleEvents.SERVER_STOPPING.register(mainListener);
//        ServerLifecycleEvents.SERVER_STARTED.register(mainListener);
//        ServerLifecycleEvents.SERVER_STARTING.register(mainListener);
//        ServerPlayConnectionEvents.JOIN.register(mainListener);
//        ServerPlayConnectionEvents.DISCONNECT.register(mainListener);
//        ServerMessageEvents.CHAT_MESSAGE.register(mainListener);

    }

    @Override
    public void onDisable() {
        BlockBridgePaperUtils.end();
    }


//    private void configDefaults() {
//        setDefault("api_url", "http://localhost:8585");
//        setDefault("api_entry_point", "/api");
//        setDefault("app_entry_point", "/app");
//        setDefault("app_version", "v1");
//
//        saveConfig();
//    }
}
