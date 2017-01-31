package com.bitmesra.bitotsav.features.home;

import com.bitmesra.bitotsav.database.models.home.NotificationWrapper;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Batdroid on 31/1/17 for Bitotsav.
 */

public class RealmManager {
    private Realm realm = Realm.getDefaultInstance();

    public RealmManager() {
    }

    public NotificationWrapper getNotificationsWrapper() {
        RealmResults<NotificationWrapper> realmResults = realm.where(NotificationWrapper.class).findAll();
        if (realmResults.size() > 0) {
            return realmResults.get(0);
        } else {
            return null;
        }
    }

    public void saveNotificationWrapper(NotificationWrapper wrapper) {
        realm.executeTransaction(realm1 -> realm1.delete(NotificationWrapper.class));
        realm.executeTransactionAsync(realm1 -> realm1.copyToRealm(wrapper));
    }
}
