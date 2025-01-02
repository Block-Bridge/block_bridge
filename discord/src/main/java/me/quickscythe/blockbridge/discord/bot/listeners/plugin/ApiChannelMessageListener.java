package me.quickscythe.blockbridge.discord.bot.listeners.plugin;


import me.quickscythe.blockbridge.api.event.api.ApiChannelMessageEvent;
import me.quickscythe.blockbridge.api.listener.Listener;
import me.quickscythe.blockbridge.discord.BlockBridgeDiscordPlugin;
import me.quickscythe.blockbridge.discord.utils.BlockBridgeDiscordUtils;

public class ApiChannelMessageListener implements Listener.ApiChannelListener {

    private final BlockBridgeDiscordPlugin plugin;

    public ApiChannelMessageListener(BlockBridgeDiscordPlugin plugin) {
        this.plugin = plugin;
        BlockBridgeDiscordUtils.getMain().getApi().getWebApp().addListener(plugin, this);
    }

    @Override
    public void onMessage(ApiChannelMessageEvent event) {
        if (event.getAction().equalsIgnoreCase("send_message") && event.getTo().equalsIgnoreCase("discord"))
            BlockBridgeDiscordUtils.getMain().getBot().getLogsChannel().sendMessage(event.getMessage()).queue();
    }
}
