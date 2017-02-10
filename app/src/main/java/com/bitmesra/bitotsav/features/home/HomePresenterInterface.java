package com.bitmesra.bitotsav.features.home;

import com.bitmesra.bitotsav.database.models.home.NotificationItem;

import io.realm.RealmResults;

/**
 * Created by Batdroid on 26/1/17 for Bitotsav.
 */

public interface HomePresenterInterface {
    public RealmResults<NotificationItem> getNotifications();
}
