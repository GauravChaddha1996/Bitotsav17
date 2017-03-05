package com.bitmesra.bitotsav.network.payment;

import com.bitmesra.bitotsav.database.models.PaymentResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Batdroid on 6/3/17 for Bitotsav.
 */

public interface PaymentAPI {
    @GET("/payInfo")
    Observable<PaymentResponse> getPaymentInfo(@Query("bitId") String bitId, @Query("email") String email);
}
