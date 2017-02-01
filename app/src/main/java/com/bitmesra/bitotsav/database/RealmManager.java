package com.bitmesra.bitotsav.database;

import com.bitmesra.bitotsav.database.models.home.NotificationDto;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Batdroid on 31/1/17 for Bitotsav.
 */

public class RealmManager {
    private Realm realm = Realm.getDefaultInstance();

    public RealmManager() {
    }

    public NotificationDto getNotificationDto() {
        RealmResults<NotificationDto> realmResults = realm.where(NotificationDto.class).findAll();
        if (realmResults.size() > 0) {
            return realmResults.get(0);
        } else {
            return null;
        }
    }

    public void saveNotificationDto(NotificationDto dto) {
        realm.executeTransaction(realm1 -> realm1.delete(NotificationDto.class));
        realm.executeTransactionAsync(realm1 -> realm1.copyToRealm(dto));
    }
}
