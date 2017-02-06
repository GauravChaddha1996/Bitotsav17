package com.bitmesra.bitotsav.database;

import android.content.Context;

import com.bitmesra.bitotsav.database.models.events.TimelineItem;
import com.bitmesra.bitotsav.database.models.home.NotificationDto;
import com.bitmesra.bitotsav.database.models.login.SignUpBody;
import com.bitmesra.bitotsav.database.models.login.SignUpResultBody;
import com.bitmesra.bitotsav.network.FakeInterceptor;
import com.bitmesra.bitotsav.network.events.timeline.TimelineAPI;
import com.bitmesra.bitotsav.network.home.HomeNotificationAPI;
import com.bitmesra.bitotsav.network.login.SignUpAPI;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Response;
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
    private OkHttpClient client;
    private FakeInterceptor fakeInterceptor;
    private Context context;

    NetworkManager(Context context) {
        this.context = context;
        fakeInterceptor = new FakeInterceptor(context);
        client = new OkHttpClient
                .Builder()
                .addInterceptor(fakeInterceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://bitotsav.in")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public Observable<Response<SignUpResultBody>> signUp(SignUpBody body) {
        return retrofit.create(SignUpAPI.class).signUp(body)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<NotificationDto> getRecentNotifications() {
        return retrofit.create(HomeNotificationAPI.class).getRecentNotifications()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<NotificationDto> getNextNotifications(long id) {
        return retrofit.create(HomeNotificationAPI.class).getNextNotifications(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<NotificationDto> getLatestNotifications(long id) {
        return retrofit.create(HomeNotificationAPI.class).getLatestNotifications(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<TimelineItem>> getTimelineEvents(int dayNumber) {
        return retrofit.create(TimelineAPI.class).getTimeline(dayNumber)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
