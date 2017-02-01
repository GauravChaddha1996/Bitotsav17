package com.bitmesra.bitotsav.database;

import android.content.Context;

import com.bitmesra.bitotsav.database.models.home.NotificationDto;

import rx.Observable;

public class DataManager {

    NetworkManager networkManager = null;
    RealmManager realmManager = null;

    private DataManager() {

    }

    public static DataManager getDataManager() {
        return DataManagerHelper.INSTANCE;
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

    public Observable<NotificationDto> getRecentNotifications(Context context) {
        createNetworkManager(context);
        return networkManager.getRecentNotifications();
    }

    public Observable<NotificationDto> getNextNotifications(Context context, long id) {
        createNetworkManager(context);
        return networkManager.getNextNotifications(id);
    }

    public Observable<NotificationDto> getLatestNotifications(Context context, long id) {
        createNetworkManager(context);
        return networkManager.getLatestNotifications(id);
    }

    public RealmManager getRealmManager() {
        createRealmManager();
        return realmManager;
    }

    private static class DataManagerHelper {
        private static final DataManager INSTANCE = new DataManager();
    }

}

