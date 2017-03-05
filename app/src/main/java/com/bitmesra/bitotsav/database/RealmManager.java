package com.bitmesra.bitotsav.database;

import android.util.Log;

import com.bitmesra.bitotsav.database.models.SubscribedTopics;
import com.bitmesra.bitotsav.database.models.events.EventDto;
import com.bitmesra.bitotsav.database.models.home.NotificationItem;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

import static com.bitmesra.bitotsav.utils.Utils.findEventDtoDayType;

/**
 * Created by Batdroid on 31/1/17 for Bitotsav.
 */

public class RealmManager {
    private Realm realm = Realm.getDefaultInstance();

    public RealmManager() {
    }


    /**
     * Notifications functions
     */
    public RealmResults<NotificationItem> getNotificationDto() {
        return Realm.getDefaultInstance().where(NotificationItem.class).findAllSorted("time", Sort.DESCENDING);
    }

    public void saveNotifications(List<NotificationItem> items) {
        Realm.getDefaultInstance().executeTransactionAsync(realm1 -> {
            for (NotificationItem item : items) {
                realm1.insert(item);
            }
        });
    }

    public void addNotificationItem(NotificationItem item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(realm1 -> realm1.insert(item));
    }


    /**
     * Timeline functions
     */
    public List<EventDto> getTimelineEvents(int dayNumber) {
        RealmResults<EventDto> realmResults = realm.where(EventDto.class)
                .equalTo("eventDtoType", findEventDtoDayType(dayNumber)).findAll();
        return realmResults;
    }

    public void saveTimelineEvents(List<EventDto> dtos, int dayNumber) {
        int temp = findEventDtoDayType(dayNumber);
        for (EventDto dto : dtos) {
            dto.setEventDtoType(temp);
            if (!dto.getImageurl().trim().isEmpty()) {
                Log.d("Tag", dto.getName() + " :" + dto.getImageurl());
            }
        }
        realm.executeTransactionAsync(realm1 -> realm1.insertOrUpdate(dtos));

    }

    /**
     * Event details functions
     */
    public EventDto getDetailsDto(String eventname) {
        return realm.where(EventDto.class).equalTo("name", eventname).findFirst();
    }

    public void saveDetailsDto(EventDto eventDto) {
        realm.executeTransaction(realm1 -> {
            EventDto dto = realm1.where(EventDto.class).equalTo("_id", eventDto.get_id()).findFirst();
            if (dto != null) {
                dto.deleteFromRealm();
            }
            realm1.insert(eventDto);
        });
    }

    /***
     * Subscribed topic functions
     */

    public boolean isTopicSubscribed(String name) {
        if (realm.where(SubscribedTopics.class).equalTo("topic", name).findFirst() != null) {
            return true;
        }
        return false;
    }

    public void saveSubscribedTopic(String name) {
        realm.executeTransactionAsync(realm1 -> realm1.insert(new SubscribedTopics(name)));
    }

    public void removeSubscribedTopic(String name) {
        realm.executeTransactionAsync(realm1 -> realm1.where(SubscribedTopics.class)
                .equalTo("topic", name).findFirst().deleteFromRealm());
    }
}
