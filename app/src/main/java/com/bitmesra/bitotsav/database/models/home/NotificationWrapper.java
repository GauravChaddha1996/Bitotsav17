package com.bitmesra.bitotsav.database.models.home;

import java.util.List;

/**
 * Created by Batdroid on 30/1/17 for Bitotsav.
 */

public class NotificationWrapper {
    List<BitotsavNotification> notificationList;

    public NotificationWrapper(boolean isLoading, List<BitotsavNotification> notificationList) {
        this.notificationList = notificationList;
    }

    public List<BitotsavNotification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<BitotsavNotification> notificationList) {
        this.notificationList = notificationList;
    }
}
