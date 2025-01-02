package me.quickscythe.blockbridge.fabric;

import me.quickscythe.blockbridge.fabric.listeners.ServerListener;
import me.quickscythe.blockbridge.fabric.utils.BlockBridgeFabricUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import org.json.JSONObject;

public class BlockBridgeFabric implements ModInitializer  {
    
    private JSONObject CONFIG;
    public final String MOD_ID = "block_bridge";
    private final String CONFIG_FILE = "config/blockbridge.json";



    @Override
    public void onInitialize() {
        //This will only init on Servers
        BlockBridgeFabricUtils.initializeServer(this);
        BlockBridgeFabricUtils.initializeLogger();
//        CONFIG = loadConfig();
//        configDefaults();



        //Register Events

        ServerListener mainListener = new ServerListener();
        ServerLifecycleEvents.SERVER_STOPPING.register(mainListener);
        ServerLifecycleEvents.SERVER_STARTED.register(mainListener);
        ServerLifecycleEvents.SERVER_STARTING.register(mainListener);
        ServerPlayConnectionEvents.JOIN.register(mainListener);
        ServerPlayConnectionEvents.DISCONNECT.register(mainListener);
        ServerMessageEvents.CHAT_MESSAGE.register(mainListener);

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
