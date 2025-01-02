package me.quickscythe.blockbridge.paper.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.quickscythe.blockbridge.paper.utils.BlockBridgePaperUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ServerListener implements Listener {

    public ServerListener(JavaPlugin plugin){
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        BlockBridgePaperUtils.getApi().appData("save_player?a=" + e.getPlayer().getName() + "&b=" + e.getPlayer().getUniqueId() + "&c=" + e.getPlayer().getAddress().toString());
        BlockBridgePaperUtils.getLogger().log(BlockBridgePaperUtils.getApi().appData("join?a=" + e.getPlayer().getUniqueId()).toString());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        BlockBridgePaperUtils.getApi().appData("save_player?a=" + e.getPlayer().getName() + "&b=" + e.getPlayer().getUniqueId() + "&c=" + e.getPlayer().getAddress().toString());
        BlockBridgePaperUtils.getLogger().log(BlockBridgePaperUtils.getApi().appData("leave?a=" + e.getPlayer().getName()).toString() + "&b=" + e.getPlayer().getUniqueId());
    }

//    @Override
//    public void onPlayDisconnect(ServerPlayNetworkHandler handler, MinecraftServer server) {
//        BlockBridgeFabricUtils.getApi().appData("save_player?a=" + handler.getPlayer().getName().getString() + "&b=" + handler.getPlayer().getUuid() + "&c=" + handler.getConnectionAddress().toString());
//        BlockBridgeFabricUtils.getLogger().log(BlockBridgeFabricUtils.getApi().appData("leave?a=" + handler.getPlayer().getName().getString()).toString() + "&b=" + handler.getPlayer().getUuid());
//
//    }


    @EventHandler
    public void onPlayerChat(AsyncChatEvent e){
        BlockBridgePaperUtils.getLogger().log(BlockBridgePaperUtils.getApi().appData("chat?a=" + e.getPlayer().getName()).toString() + "&b=" + e.getPlayer().getUniqueId() + "&c=" + format(e.message().toString()));
    }


    private String format(String name) {
        return URLEncoder.encode(name, StandardCharsets.UTF_8);
    }
}
