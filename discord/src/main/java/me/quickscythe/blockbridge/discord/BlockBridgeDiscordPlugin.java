package me.quickscythe.blockbridge.discord;

import me.quickscythe.blockbridge.api.BotPlugin;
import me.quickscythe.blockbridge.discord.bot.listeners.plugin.ApiChannelMessageListener;

public class BlockBridgeDiscordPlugin extends BotPlugin {

    public BlockBridgeDiscordPlugin() {
        setName("BlockBridgeDiscord");
    }

    public void enable() {
        System.out.println(getName() + " enabled");

        new ApiChannelMessageListener(this);
    }
}
