package com.bitmesra.bitotsav.database;

import com.bitmesra.bitotsav.database.models.UserDetailsDto;
import com.bitmesra.bitotsav.database.models.events.EventDto;
import com.bitmesra.bitotsav.database.models.home.NotificationDto;
import com.bitmesra.bitotsav.features.EventDtoType;

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

    public void saveUserDetails(UserDetailsDto detailsDto) {
        realm.executeTransaction(realm1 -> realm1.delete(UserDetailsDto.class));
        realm.executeTransaction(realm1 -> realm1.copyToRealm(detailsDto));
    }

    public UserDetailsDto getUserDetails() {
        return realm.where(UserDetailsDto.class).findFirst();
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

    public List<EventDto> getTimelineEvents(int dayNumber) {
        RealmResults<EventDto> realmResults = realm.where(EventDto.class)
                .equalTo("eventDtoType", findEventDtoDayType(dayNumber)).findAll();
        System.out.println(realmResults.toString());
        return realmResults;
    }

    public void saveTimelineEvents(List<EventDto> dtos, int dayNumber) {
        int temp = findEventDtoDayType(dayNumber);
        for (EventDto dto : dtos) {
            dto.setEventDtoType(temp);
        }
        realm.executeTransactionAsync(realm1 -> realm1.insertOrUpdate(dtos));
    }

    private int findEventDtoDayType(int dayNumber) {
        switch (dayNumber) {
            case 1:
                return EventDtoType.TYPE_DAY1;
            case 2:
                return EventDtoType.TYPE_DAY2;
            case 3:
                return EventDtoType.TYPE_DAY3;
            case 4:
                return EventDtoType.TYPE_DAY4;
            default:
                return EventDtoType.TYPE_DAY1;
        }
    }
}
