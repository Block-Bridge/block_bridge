package me.quickscythe.blockbridge.api.listener;

import me.quickscythe.blockbridge.api.event.api.ApiChannelMessageEvent;
import me.quickscythe.blockbridge.api.event.minecraft.*;

public interface Listener {


    interface JoinListener extends Listener {
        void onJoin(PlayerJoinEvent event);
    }
    interface LeaveListener extends Listener {
        void onLeave(PlayerLeaveEvent event);
    }

    interface StatusListener extends Listener {
        void onStatusChange(ServerStatusChangeEvent event);
    }
    interface ChatListener extends Listener {
        void onPlayerChat(PlayerChatEvent event);
    }
    interface PingListener extends Listener {
        void onPing(ServerPingEvent event);
    }

    interface ApiChannelListener extends Listener {
        void onMessage(ApiChannelMessageEvent event);
    }
}
