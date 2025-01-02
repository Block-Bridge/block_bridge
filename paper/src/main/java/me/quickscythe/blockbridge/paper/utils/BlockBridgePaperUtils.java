package me.quickscythe.blockbridge.paper.utils;

import me.quickscythe.blockbridge.BlockBridgeApi;
import me.quickscythe.blockbridge.api.BotPlugin;
import me.quickscythe.blockbridge.paper.BlockBridgePaper;
import me.quickscythe.blockbridge.paper.listeners.ApiListener;
import me.quickscythe.blockbridge.paper.utils.logging.LoggerUtils;
import me.quickscythe.blockbridge.v1.BlockBridgeApiV1;

public class BlockBridgePaperUtils {
    private static BlockBridgeApi api;

    static LoggerUtils loggerUtils;
    static BlockBridgePaper blockBridge;
    static BlockBridgeConfig config;

    public static void initializeLogger() {
        loggerUtils = new LoggerUtils();
        loggerUtils.log("Starting BlockBridge");
    }

    public static void initializeServer(BlockBridgePaper blockBridge) {
        BlockBridgePaperUtils.blockBridge = blockBridge;
        api = new BlockBridgeApiV1();
        api.init(true);
        config = new BlockBridgeConfig();
        BotPlugin plugin = config.getConfig().getPlugin();
        api.getWebApp().addListener(plugin, new ApiListener());
//        BotPlugin plugin = config.getConfig().getPlugin();
//        BlockBridgeDiscordUtils.getPluginLoader().registerPlugin(plugin);
//        BlockBridgeDiscordUtils.getPluginLoader().enablePlugin(plugin);
    }

    public static BlockBridgeConfig getConfigManager() {
        return config;
    }

    public static BlockBridgeApi getApi() {
        return api;
    }

    public static LoggerUtils getLogger(){
        return loggerUtils;
    }

    public static BlockBridgePaper getMod() {
        return blockBridge;
    }

    public static void end() {
        getConfigManager().finish();
        BlockBridgePaperUtils.getApi().appData("status?a=offline");
        getLogger().log("Sent status ping to API. Status: Offline");
    }
}
