package me.quickscythe.blockbridge.fabric.listeners;

import me.quickscythe.blockbridge.api.listener.Listener;
import me.quickscythe.blockbridge.fabric.utils.BlockBridgeFabricUtils;
import net.minecraft.text.Text;

public class ApiListener implements Listener.ApiChannelListener {

    @Override
    public void onMessage(me.quickscythe.blockbridge.api.event.api.ApiChannelMessageEvent event) {
        if (event.getAction().equalsIgnoreCase("send_message") && event.getTo().equalsIgnoreCase("minecraft"))
            BlockBridgeFabricUtils.getServerInstance().sendMessage(Text.literal(event.getMessage()));
    }
}
