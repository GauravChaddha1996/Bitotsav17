package com.bitmesra.bitotsav.database.models.home;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Batdroid on 30/1/17 for Bitotsav.
 */

public class NotificationDto extends RealmObject {
    RealmList<NotificationItem> notificationList;

    public NotificationDto() {
    }

    public NotificationDto(List<NotificationItem> notifications) {
        for (NotificationItem notification : notifications) {
            notificationList.add(notification);
        }
    }

    public List<NotificationItem> getNotificationList() {
        List<NotificationItem> notificationItems = new ArrayList<>();
        for (NotificationItem notification : notificationList) {
            notificationItems.add(notification);
        }
        return notificationItems;
    }

}
