package me.quickscythe.blockbridge.api.event.minecraft;

import me.quickscythe.blockbridge.api.event.Event;
import me.quickscythe.blockbridge.api.object.MinecraftServer;
import me.quickscythe.blockbridge.api.object.Player;

public class ServerPingEvent extends Event {

    private Player player;
    private MinecraftServer server;

    public ServerPingEvent(Player player, MinecraftServer server) {
        this.player = player;
        this.server = server;
    }

    public Player getPlayer() {
        return player;
    }

    public MinecraftServer getServer() {
        return server;
    }

}
