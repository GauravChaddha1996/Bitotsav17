package com.bitmesra.bitotsav.database;

import android.content.Context;

import com.bitmesra.bitotsav.database.models.events.EventDto;
import com.bitmesra.bitotsav.database.models.events.ExampleModel;
import com.bitmesra.bitotsav.database.models.home.NotificationItem;
import com.bitmesra.bitotsav.network.details.DetailsAPI;
import com.bitmesra.bitotsav.network.home.NotificationAPI;
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

    public Observable<EventDto> getDayEventDetails(String id) {
        return retrofit.create(DetailsAPI.class).getDayEventDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    public Observable<ExampleModel> getFlagshipEventDetails(int id) {
        return retrofit.create(DetailsAPI.class).getFlagshipEventDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
