package com.bitmesra.bitotsav.database;

import android.content.Context;

import com.bitmesra.bitotsav.database.models.home.NotificationWrapper;
import com.bitmesra.bitotsav.network.FakeInterceptor;

import rx.Observable;

public class DataManager {

    NetworkManager networkManager = null;

    private DataManager() {

    }

    public static DataManager getDataManager() {
        return DataManagerHelper.INSTANCE;
    }

    public Observable<NotificationWrapper> getHomeNotifications(Context context) {
        createNetworkManager(context);
        return networkManager.getNotifications();
    }

    private void createNetworkManager(Context context) {
        if (networkManager == null) {
            networkManager = new NetworkManager(new FakeInterceptor(context));
        }
    }

    private static class DataManagerHelper {
        private static final DataManager INSTANCE = new DataManager();
    }
}

