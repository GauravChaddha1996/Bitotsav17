package com.bitmesra.bitotsav.network.payment;

import com.bitmesra.bitotsav.database.models.payment.PayinfoResponse;
import com.bitmesra.bitotsav.database.models.payment.PaymentResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Batdroid on 6/3/17 for Bitotsav.
 */

public interface PaymentAPI {
    @GET("/payInfo")
    Observable<PayinfoResponse> getPaymentInfo(@Query("bitId") String bitId, @Query("email") String email);

    @GET("/payment")
    Observable<PaymentResponse> getPaymentUrl(@Query("bitId") String bitId, @Query("email") String email, @Query("package") int packageNumber);
}
