package me.quickscythe.blockbridge.fabric;

import me.quickscythe.blockbridge.api.BotPlugin;

public class BlockBridgeFabricPlugin extends BotPlugin {
    public BlockBridgeFabricPlugin() {
        setName("BlockBridgeFabric");

    }

    public void enable() {
        getLogger().info("{} enabled", getName());
    }
}
