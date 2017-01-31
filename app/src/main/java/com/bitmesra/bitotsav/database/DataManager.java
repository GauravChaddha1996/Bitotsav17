package com.bitmesra.bitotsav.database;

import android.content.Context;

import com.bitmesra.bitotsav.database.models.home.NotificationWrapper;
import com.bitmesra.bitotsav.features.home.RealmManager;

import rx.Observable;

public class DataManager {

    NetworkManager networkManager = null;
    RealmManager realmManager = null;

    private DataManager() {

    }

    public static DataManager getDataManager() {
        return DataManagerHelper.INSTANCE;
    }

    public Observable<NotificationWrapper> getHomeNotifications(Context context) {
        createNetworkManager(context);
        return networkManager.getNotifications();
    }

    public Observable<NotificationWrapper> getNextNotifications(Context context) {
        createNetworkManager(context);
        return networkManager.getNextNotifications();
    }

    public RealmManager getRealmManager() {
        createRealmManager();
        return realmManager;
    }

    private void createNetworkManager(Context context) {
        if (networkManager == null) {
            networkManager = new NetworkManager(context);
        }
    }

    private void createRealmManager() {
        if (realmManager == null) {
            realmManager = new RealmManager();
        }
    }

    private static class DataManagerHelper {
        private static final DataManager INSTANCE = new DataManager();
    }
}

