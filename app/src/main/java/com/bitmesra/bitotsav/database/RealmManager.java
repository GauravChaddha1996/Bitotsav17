package com.bitmesra.bitotsav.database;

import com.bitmesra.bitotsav.database.models.SubscribedTopics;
import com.bitmesra.bitotsav.database.models.UserDetailsDto;
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

    public void saveUserDetails(UserDetailsDto detailsDto) {
        realm.executeTransaction(realm1 -> realm1.delete(UserDetailsDto.class));
        realm.executeTransaction(realm1 -> realm1.copyToRealm(detailsDto));
    }

    public UserDetailsDto getUserDetails() {
        return realm.where(UserDetailsDto.class).findFirst();
    }

    public RealmResults<NotificationItem> getNotificationDto() {
        return Realm.getDefaultInstance().where(NotificationItem.class).findAllSorted("time", Sort.DESCENDING);
    }

    public void addNotificationItem(NotificationItem item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(realm1 -> realm1.insert(item));
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

    public EventDto getDetailsDto(String eventname) {
        return realm.where(EventDto.class).equalTo("name", eventname).findFirst();
    }

    public void saveDetailsDto(String eventname, int eventDtoType, String time, String venue) {
        realm.executeTransaction(realm1 -> {
            EventDto dto = realm1.where(EventDto.class).equalTo("name", eventname).findFirst();
            if (dto == null) {
                dto = new EventDto();
                dto.setName(eventname);
            }
            dto.setEventDtoType(eventDtoType);
            dto.setTime(time);
            dto.setVenue(venue);
            realm1.insertOrUpdate(dto);
        });
    }

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
