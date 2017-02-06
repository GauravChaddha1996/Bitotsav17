package com.bitmesra.bitotsav.network.login;

import com.bitmesra.bitotsav.database.models.login.SignUpBody;
import com.bitmesra.bitotsav.database.models.login.SignUpResultBody;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Batdroid on 6/2/17 for Bitotsav.
 */

public interface SignUpAPI {

    @POST("/signup")
    Observable<Response<SignUpResultBody>> signUp(@Body SignUpBody body);
}
