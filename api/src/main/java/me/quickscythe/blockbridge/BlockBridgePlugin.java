package me.quickscythe.blockbridge;

import me.quickscythe.blockbridge.api.BotPlugin;

public class BlockBridgePlugin extends BotPlugin {

    public BlockBridgePlugin() {
        setName("BlockBridgeCore");
    }

    public void enable() {
        System.out.println(getName() + " enabled");
    }
}
