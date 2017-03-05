package com.bitmesra.bitotsav.network.register;

import com.bitmesra.bitotsav.database.models.RegistrationResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Batdroid on 5/3/17 for Bitotsav.
 */

public interface RegistrationAPI {
    @GET("/registeration")
    Observable<RegistrationResponse> register(@Query("name") String name, @Query("email") String email,
                                              @Query("contact") String phone, @Query("college") String college,
                                              @Query("sap") String sap);

    @GET("/teamRegistration")
    Observable<RegistrationResponse> teamRegister(@Query("team") String teamname, @Query("email") String heademail,
                                                  @Query("contact") String headContact, @Query("events") String events,
                                                  @Query("members") String members,
                                                  @Query("teamhead") String headBitId, @Query("college") String college);
}
