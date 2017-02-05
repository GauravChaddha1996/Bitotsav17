package com.bitmesra.bitotsav.network.events.timeline;

import com.bitmesra.bitotsav.database.models.events.TimelineItem;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Batdroid on 5/2/17 for Bitotsav.
 */

public interface TimelineAPI {
    @GET("/events/timeline")
    Observable<List<TimelineItem>> getTimeline(@Query("day") int dayNumber);
}
