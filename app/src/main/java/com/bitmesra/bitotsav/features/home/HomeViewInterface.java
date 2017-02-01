package com.bitmesra.bitotsav.features.home;

import com.bitmesra.bitotsav.database.models.home.NotificationItem;

import java.util.List;

/**
 * Created by Batdroid on 26/1/17 for Bitotsav.
 */

public interface HomeViewInterface {
    void updateRecentNotifications(List<NotificationItem> notifications);
    void updateNextNotifications(List<NotificationItem> notifications);
    void updateLatestNotifications(List<NotificationItem> notifications);
}
