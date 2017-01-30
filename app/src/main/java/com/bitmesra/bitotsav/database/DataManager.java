package com.bitmesra.bitotsav.database;

public class DataManager {
    private DataManager() {

    }

    private static class DataManagerHelper {
        private static final DataManager INSTANCE = new DataManager();
    }

    public static DataManager getDataManager() {
        return DataManagerHelper.INSTANCE;
    }
}

