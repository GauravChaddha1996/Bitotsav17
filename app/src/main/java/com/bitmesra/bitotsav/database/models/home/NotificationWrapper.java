package com.bitmesra.bitotsav.database.models.home;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Batdroid on 30/1/17 for Bitotsav.
 */

public class NotificationWrapper extends RealmObject {
    RealmList<BitotsavNotification> notificationList;

    public NotificationWrapper() {
    }

    public NotificationWrapper(List<BitotsavNotification> notifications) {
        for (BitotsavNotification notification : notifications) {
            notificationList.add(notification);
        }
    }

    public List<BitotsavNotification> getNotificationList() {
        List<BitotsavNotification> bitotsavNotifications = new ArrayList<>();
        for (BitotsavNotification notification : notificationList) {
            bitotsavNotifications.add(notification);
        }
        return bitotsavNotifications;
    }

}
