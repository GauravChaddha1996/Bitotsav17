package com.bitmesra.bitotsav.app;

import android.app.Application;

import com.google.firebase.messaging.FirebaseMessaging;

import io.realm.Realm;

public class BitotsavApplication extends Application {
    public BitotsavApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //  LeakCanary.install(this);
        Realm.init(this);
        FirebaseMessaging.getInstance().subscribeToTopic("everyone");
    }
}

