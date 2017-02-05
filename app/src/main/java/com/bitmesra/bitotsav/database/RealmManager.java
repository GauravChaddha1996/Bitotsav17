package com.bitmesra.bitotsav.database;

import com.bitmesra.bitotsav.database.models.events.TimelineDto;
import com.bitmesra.bitotsav.database.models.events.TimelineItem;
import com.bitmesra.bitotsav.database.models.home.NotificationDto;

import java.util.List;

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

    public List<TimelineItem> getTimelineEvents(int dayNumber) {
        RealmResults<TimelineDto> realmResults = realm.where(TimelineDto.class).findAll();
        for (TimelineDto dto : realmResults) {
            if (dto.getDayNumber() == dayNumber) {
                return dto.getList();
            }
        }
        return null;
    }

    public void saveTimelineEvents(TimelineDto dto) {
        realm.executeTransactionAsync(realm1 -> realm1.insertOrUpdate(dto));
    }
}
