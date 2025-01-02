package me.quickscythe.blockbridge.fabric.utils;

import me.quickscythe.blockbridge.api.config.ConfigClass;
import me.quickscythe.blockbridge.fabric.BlockBridgeFabricPlugin;

public class BlockBridgeConfig extends ConfigClass {
    public BlockBridgeConfig() {
        super(new BlockBridgeFabricPlugin(), "blockbridge", Defaults.config());
    }
}
