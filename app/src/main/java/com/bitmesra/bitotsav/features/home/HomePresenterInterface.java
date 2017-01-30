package com.bitmesra.bitotsav.features.home;

import com.bitmesra.bitotsav.database.models.home.BitotsavNotification;

import java.util.List;

/**
 * Created by Batdroid on 26/1/17 for Bitotsav.
 */

public interface HomePresenterInterface {
    public List<BitotsavNotification> getNotificationData();
}
