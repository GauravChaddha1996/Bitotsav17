package com.bitmesra.bitotsav.network.support;

import com.bitmesra.bitotsav.database.models.support.SupportResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Batdroid on 7/3/17 for Bitotsav.
 */

public interface SupportAPI {


    @GET("/social-support")
    Observable<SupportResponse> donate(@Query("email") String email, @Query("first_name") String firstName, @Query("last_name") String
            lastName, @Query("amount") String amount, @Query("contact") String phone,
                                       @Query("batch") String batch, @Query("message") String message);
}
