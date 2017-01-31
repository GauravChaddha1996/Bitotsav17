package com.bitmesra.bitotsav.network.home;

import com.bitmesra.bitotsav.database.models.home.NotificationWrapper;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Batdroid on 30/1/17 for Bitotsav.
 */

public interface HomeNotificationAPI {
    @GET("/notification/recent")
    Observable<NotificationWrapper> getNotifications();
    @GET("/notification/next")
    Observable<NotificationWrapper> getNextNotifications(@Query("id")long id);
}
