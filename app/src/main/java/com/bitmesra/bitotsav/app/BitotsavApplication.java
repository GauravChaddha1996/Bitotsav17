package com.bitmesra.bitotsav.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import io.realm.Realm;

public class BitotsavApplication extends Application {
    public BitotsavApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        Realm.init(this);
    }
}

