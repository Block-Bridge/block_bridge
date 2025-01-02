package me.quickscythe.blockbridge.api.event.minecraft;

import me.quickscythe.blockbridge.api.event.Event;
import me.quickscythe.blockbridge.api.object.Player;

public class PlayerJoinEvent extends Event {
    private Player player;

    public PlayerJoinEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
