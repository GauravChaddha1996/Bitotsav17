package com.bitmesra.bitotsav.network.tshirt;

import com.bitmesra.bitotsav.database.models.payment.PaymentResponse;
import com.bitmesra.bitotsav.database.models.tshirt.TShirtBookResponse;
import com.bitmesra.bitotsav.database.models.tshirt.TeeinfoResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Batdroid on 6/3/17 for Bitotsav.
 */

public interface TShirtAPI {
    @GET("/teereg")
    Observable<TShirtBookResponse> bookTShirt(@Query("name") String name, @Query("email") String email,
                                              @Query("contact") String phone, @Query("size") String size,
                                              @Query("college") String college, @Query("hostel") String room);

    @GET("/teeInfo")
    Observable<TeeinfoResponse> getTeeInfo(@Query("teeId") String teeId, @Query("email") String email);

    @GET("/paymentTee")
    Observable<PaymentResponse> getPaymentUrl(@Query("teeId") String teeId, @Query("email") String email, @Query("size") String size);
}
