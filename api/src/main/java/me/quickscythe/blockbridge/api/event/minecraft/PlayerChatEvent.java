package me.quickscythe.blockbridge.api.event.minecraft;

import me.quickscythe.blockbridge.api.event.Event;
import me.quickscythe.blockbridge.api.object.Player;

public class PlayerChatEvent extends Event {
    private Player player;
    private String message;

    public PlayerChatEvent(Player player, String message) {
        this.player = player;
        this.message = message;
    }

    public Player getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }


}
