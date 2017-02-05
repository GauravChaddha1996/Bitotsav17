package com.bitmesra.bitotsav.database;

import android.content.Context;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.events.EventItem;
import com.bitmesra.bitotsav.database.models.home.NotificationDto;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Home - Notifications functions
     */
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

    /**
     * Event functions
     */
    public List<EventItem> getEventList() {
        List<EventItem> list = new ArrayList<>();
        list.add(new EventItem("Timeline", R.drawable.home2));
        list.add(new EventItem("Flagship", R.drawable.home1));
        list.add(new EventItem("Informal", R.drawable.home2));
        return list;
    }

    public RealmManager getRealmManager() {
        createRealmManager();
        return realmManager;
    }

    private static class DataManagerHelper {
        private static final DataManager INSTANCE = new DataManager();
    }

}

