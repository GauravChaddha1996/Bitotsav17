package com.bitmesra.bitotsav.database;

import android.content.Context;

import com.bitmesra.bitotsav.database.models.events.EventDetailsDto;
import com.bitmesra.bitotsav.database.models.events.EventDto;
import com.bitmesra.bitotsav.database.models.home.NotificationItem;
import com.bitmesra.bitotsav.network.FakeInterceptor;
import com.bitmesra.bitotsav.network.details.DetailsAPI;
import com.bitmesra.bitotsav.network.events.timeline.TimelineAPI;
import com.bitmesra.bitotsav.network.home.NotificationAPI;

import java.util.List;

import okhttp3.OkHttpClient;
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

    public Observable<EventDetailsDto> getEventDetails(String eventName) {
        return retrofit.create(DetailsAPI.class).getEventDetails(eventName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
