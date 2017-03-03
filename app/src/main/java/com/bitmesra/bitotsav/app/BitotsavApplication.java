package com.bitmesra.bitotsav.app;

import android.app.Application;

import com.bitmesra.bitotsav.ui.FontsOverride;
import com.bitmesra.bitotsav.utils.Foreground;
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
        Foreground.init(this);
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Oswald/Oswald_Regular.ttf");
    }
}

