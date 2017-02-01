package com.bitmesra.bitotsav.network.home;

import com.bitmesra.bitotsav.database.models.home.NotificationDto;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Batdroid on 30/1/17 for Bitotsav.
 */

public interface HomeNotificationAPI {
    @GET("/notification/recent")
    Observable<NotificationDto> getRecentNotifications();
    @GET("/notification/next")
    Observable<NotificationDto> getNextNotifications(@Query("id")long id);
    @GET("/notification/latest")
    Observable<NotificationDto> getLatestNotifications(@Query("id")long id);
}
