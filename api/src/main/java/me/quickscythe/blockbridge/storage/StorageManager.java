package me.quickscythe.blockbridge.storage;

import me.quickscythe.blockbridge.BlockBridgeApi;

public class StorageManager {

    private static Storage storage;

    public static void init(BlockBridgeApi api){
        storage = new Storage(api);
    }

    //StorageManager.getStorage().saveData("data", "value");

    public static Storage getStorage(){
        return storage;
    }
}
