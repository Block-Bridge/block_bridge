package me.quickscythe.blockbridge.paper;


import me.quickscythe.blockbridge.api.BotPlugin;

public class BlockBridgePaperPlugin extends BotPlugin {
    public BlockBridgePaperPlugin() {
        setName("BlockBridgePaper");

    }

    public void enable() {
        getLogger().info("{} enabled", getName());
    }
}
