package me.quickscythe.blockbridge.fabric.utils;

import me.quickscythe.blockbridge.BlockBridgeApi;
import me.quickscythe.blockbridge.api.BotPlugin;
import me.quickscythe.blockbridge.fabric.BlockBridgeFabric;
import me.quickscythe.blockbridge.fabric.listeners.ApiListener;
import me.quickscythe.blockbridge.fabric.utils.logging.LoggerUtils;
import me.quickscythe.blockbridge.v1.BlockBridgeApiV1;
import net.minecraft.server.MinecraftServer;

public class BlockBridgeFabricUtils {
    private static BlockBridgeApi api;

    static LoggerUtils loggerUtils;
    static BlockBridgeFabric blockBridge;
    static BlockBridgeConfig config;
    static MinecraftServer serverInstance;

    public static void initializeLogger() {
        loggerUtils = new LoggerUtils();
        loggerUtils.log("Starting BlockBridge");
    }

    public static void setServerInstance(MinecraftServer server) {
        serverInstance = server;
    }

    public static MinecraftServer getServerInstance() {
        return serverInstance;
    }

    public static void initializeServer(BlockBridgeFabric blockBridge) {
        BlockBridgeFabricUtils.blockBridge = blockBridge;
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

    public static BlockBridgeFabric getMod() {
        return blockBridge;
    }

    public static void end() {
        getConfigManager().finish();
        BlockBridgeFabricUtils.getApi().appData("status?a=offline");
        getLogger().log("Sent status ping to API. Status: Offline");
    }
}
