package me.quickscythe.blockbridge.api.event.minecraft;

import me.quickscythe.blockbridge.api.event.Event;
import me.quickscythe.blockbridge.api.object.Player;

public class PlayerLeaveEvent extends Event {
    private Player player;

    public PlayerLeaveEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
