package com.bitmesra.bitotsav.network.timeline;

import com.bitmesra.bitotsav.database.models.details.EventDto;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Batdroid on 5/2/17 for Bitotsav.
 */

public interface TimelineAPI {
    @GET("/events/day")
    Observable<List<EventDto>> getTimeline(@Query("day") int dayNumber);
}
