package com.bitmesra.bitotsav.network.flagship;

import com.bitmesra.bitotsav.database.models.flagship.FlagshipDetailsDto;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Batdroid on 7/2/17 for Bitotsav.
 */

public interface FlagshipAPI {
    @GET("/flagship")
    Observable<FlagshipDetailsDto> getFlagshipDetails(@Query("name") String flagshipName);
}
