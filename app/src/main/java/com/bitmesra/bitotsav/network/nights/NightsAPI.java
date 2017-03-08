package com.bitmesra.bitotsav.network.nights;

import com.bitmesra.bitotsav.database.models.nights.NightsModel;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Batdroid on 8/3/17 for Bitotsav.
 */

public interface NightsAPI {
    @GET("/nights")
    Observable<List<NightsModel>> getNightsList();
}
