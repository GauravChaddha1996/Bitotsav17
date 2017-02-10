package com.bitmesra.bitotsav.network.details;

import com.bitmesra.bitotsav.database.models.events.EventDetailsDto;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Batdroid on 7/2/17 for Bitotsav.
 */

public interface DetailsAPI {
    @GET("/events")
    Observable<EventDetailsDto> getEventDetails(@Query("name") String flagshipName);
}
