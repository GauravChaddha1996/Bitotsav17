package com.bitmesra.bitotsav.database;

import android.content.Context;

import com.bitmesra.bitotsav.database.models.PaymentResponse;
import com.bitmesra.bitotsav.database.models.RegistrationResponse;
import com.bitmesra.bitotsav.database.models.events.EventDto;
import com.bitmesra.bitotsav.database.models.events.ExampleModel;
import com.bitmesra.bitotsav.database.models.home.NotificationItem;
import com.bitmesra.bitotsav.network.details.DetailsAPI;
import com.bitmesra.bitotsav.network.home.NotificationAPI;
import com.bitmesra.bitotsav.network.payment.PaymentAPI;
import com.bitmesra.bitotsav.network.register.RegistrationAPI;
import com.bitmesra.bitotsav.network.timeline.TimelineAPI;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Batdroid on 30/1/17 for Bitotsav.
 */

public class NetworkManager {
    private Retrofit retrofit;
    private Context context;

    NetworkManager(Context context) {
        this.context = context;
        retrofit = new Retrofit.Builder()
                .baseUrl("http://bitotsav.in")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public Observable<List<NotificationItem>> getNotifications() {
        return retrofit.create(NotificationAPI.class).getNotifications()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<EventDto>> getTimelineEvents(int dayNumber) {
        return retrofit.create(TimelineAPI.class).getTimeline(dayNumber)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<ExampleModel> getDayEventDetails(String id) {
        return retrofit.create(DetailsAPI.class).getDayEventDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<ExampleModel> getFlagshipEventDetails(int id) {
        return retrofit.create(DetailsAPI.class).getFlagshipEventDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<RegistrationResponse> register(String name, String email, String phone, String college, String sap) {
        return retrofit.create(RegistrationAPI.class).register(name, email, phone, college, sap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<RegistrationResponse> teamRegister(String teamName, String heademail, String headContact,
                                                     String events, String members, String headBitId, String college) {
        return retrofit.create(RegistrationAPI.class).teamRegister(teamName, heademail, headContact,
                events, members, headBitId, college)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<PaymentResponse> getPaymentInfo(String bitid, String email) {
        return retrofit.create(PaymentAPI.class).getPaymentInfo(bitid,email)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
