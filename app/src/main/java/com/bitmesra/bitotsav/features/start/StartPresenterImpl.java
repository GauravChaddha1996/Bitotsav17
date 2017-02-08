package com.bitmesra.bitotsav.features.start;

import android.os.Handler;
import android.os.Message;

import com.bitmesra.bitotsav.database.DataManager;

class StartPresenterImpl implements StartPresenterInterface {
    private DataManager manager;
    private StartViewInterface viewInterface;

    StartPresenterImpl(StartViewInterface viewInterface) {
        this.viewInterface = viewInterface;
        manager = DataManager.getDataManager();
        startHomeActivityTimer();
    }

    private void startHomeActivityTimer() {
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                viewInterface.goToHomeActivity();
            }
        };
        handler.sendEmptyMessageDelayed(0,3*1000);
    }
}
