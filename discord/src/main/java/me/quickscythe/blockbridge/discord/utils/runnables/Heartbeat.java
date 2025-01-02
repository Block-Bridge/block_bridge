package me.quickscythe.blockbridge.discord.utils.runnables;


import me.quickscythe.blockbridge.discord.BlockBridgeDiscord;
import me.quickscythe.blockbridge.discord.utils.BlockBridgeDiscordUtils;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Heartbeat extends TimerTask {

    private long config_check = 0L;
    private long token_check = 0L;
    private final BlockBridgeDiscord plugin;


    public Heartbeat(BlockBridgeDiscord plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {

        BlockBridgeDiscordUtils.getLogger().attemptQueue();
        long now = new Date().getTime();

        if (now - config_check >= BlockBridgeDiscordUtils.convertTime(5, TimeUnit.MINUTES)) {
            config_check = now;

            plugin.finish();
            BlockBridgeDiscordUtils.getLogger().log("Checking for expired tokens...");
            plugin.getApi().validateToken();
        }

    }
}
