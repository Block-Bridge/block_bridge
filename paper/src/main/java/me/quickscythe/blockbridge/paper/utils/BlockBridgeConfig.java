package me.quickscythe.blockbridge.paper.utils;

import me.quickscythe.blockbridge.api.config.ConfigClass;
import me.quickscythe.blockbridge.paper.BlockBridgePaperPlugin;

public class BlockBridgeConfig extends ConfigClass {
    public BlockBridgeConfig() {
        super(new BlockBridgePaperPlugin(), "blockbridge", Defaults.config());
    }
}
