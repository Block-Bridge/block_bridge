package me.quickscythe.blockbridge.paper.listeners;

import me.quickscythe.blockbridge.api.listener.Listener;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

public class ApiListener implements Listener.ApiChannelListener {

    @Override
    public void onMessage(me.quickscythe.blockbridge.api.event.api.ApiChannelMessageEvent event) {
        if (event.getAction().equalsIgnoreCase("send_message") && event.getTo().equalsIgnoreCase("minecraft"))
            Bukkit.getServer().sendMessage(Component.text(event.getMessage()));
    }
}
