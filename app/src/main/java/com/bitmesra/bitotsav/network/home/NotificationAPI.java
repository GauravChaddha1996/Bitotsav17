package com.bitmesra.bitotsav.network.home;

import com.bitmesra.bitotsav.database.models.notification.NotificationItem;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Batdroid on 12/2/17 for Bitotsav.
 */

public interface NotificationAPI {
    @GET(("/notifications"))
    Observable<List<NotificationItem>> getNotifications();
}
