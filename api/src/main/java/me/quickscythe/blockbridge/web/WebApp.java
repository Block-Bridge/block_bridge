package me.quickscythe.blockbridge.web;
import me.quickscythe.blockbridge.BlockBridgeApi;
import me.quickscythe.blockbridge.api.BotPlugin;
import me.quickscythe.blockbridge.api.listener.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static spark.Spark.*;

public abstract class WebApp {

    private final BlockBridgeApi bba;
    private HashMap<Listener, BotPlugin> listeners = new HashMap<>();

    public WebApp(BlockBridgeApi bba) {
        this.bba = bba;

        setup();
    }

    protected void setup(){
        port(bba.WEB_PORT());
    }

    public BlockBridgeApi getApi(){
        return bba;
    }

    public void addListener(BotPlugin plugin, Listener listener) {
        listeners.put(listener, plugin);
    }

    public Set<Listener> getListeners() {
        return listeners.keySet();
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public void removeListeners(BotPlugin plugin) {
        List<Listener> remove = new ArrayList<>();
        for (Listener listener : listeners.keySet()) {

            if (listeners.get(listener).equals(plugin)) {
                remove.add(listener);
            }
        }
        for (Listener listener : remove) {
            removeListener(listener);
        }
    }

}



