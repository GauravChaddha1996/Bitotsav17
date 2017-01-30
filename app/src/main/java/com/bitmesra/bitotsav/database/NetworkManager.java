package com.bitmesra.bitotsav.database;

import com.bitmesra.bitotsav.database.models.home.BitotsavNotification;
import com.bitmesra.bitotsav.database.models.home.NotificationWrapper;
import com.bitmesra.bitotsav.network.FakeInterceptor;
import com.bitmesra.bitotsav.network.home.HomeNotificationAPI;

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

class NetworkManager {
    List<BitotsavNotification> bitotsavNotificationList = null;
    private Retrofit retrofit;
    private OkHttpClient client;

    NetworkManager(FakeInterceptor fakeInterceptor) {
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

    public Observable<NotificationWrapper> getNotifications() {
        return retrofit.create(HomeNotificationAPI.class).getNotifications()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

/*
        enqueue(new Callback<List<BitotsavNotification>>() {
            @Override
            public void onResponse(Call<List<BitotsavNotification>> call, Response<List<BitotsavNotification>> response) {

                bitotsavNotificationList = response.body();
                Log.d("Response body", bitotsavNotificationList.get(0).toString());
                Log.d("Response body", bitotsavNotificationList.get(1).toString());
                Log.d("Response body", bitotsavNotificationList.get(2).toString());
                Log.d("Response body", bitotsavNotificationList.get(3).toString());
                Log.d("Response body", bitotsavNotificationList.get(4).toString());
            }

            @Override
            public void onFailure(Call<List<BitotsavNotification>> call, Throwable t) {
                BitotsavNotification b1 = new BitotsavNotification(0, "This is notif 1", "time at 8:30");
                BitotsavNotification b2 = new BitotsavNotification(1, "This is notif 2", "time at 7:30");
                BitotsavNotification b3 = new BitotsavNotification(2, "This is notif 3", "time at 6:30");
                BitotsavNotification b4 = new BitotsavNotification(3, "This is notif 4", "time at 4:30");
                BitotsavNotification b5 = new BitotsavNotification(4, "This is notif 5", "time at 3:30");
                bitotsavNotificationList.add(b1);
                bitotsavNotificationList.add(b2);
                bitotsavNotificationList.add(b3);
                bitotsavNotificationList.add(b4);
                bitotsavNotificationList.add(b5);
            }
        });*/
    }
}
