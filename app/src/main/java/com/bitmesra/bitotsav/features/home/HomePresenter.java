package com.bitmesra.bitotsav.features.home;

import android.content.Context;

import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.database.models.home.NotificationWrapper;

/**
 * Created by Batdroid on 26/1/17 for Bitotsav.
 */

public class HomePresenter implements HomePresenterInterface {
    private Context context;
    private HomeViewInterface viewInterface;
    private DataManager dataManager;

    public HomePresenter(Context context, HomeViewInterface viewInterface) {
        this.context = context;
        this.viewInterface = viewInterface;
        dataManager = DataManager.getDataManager();
    }

    @Override
    public void getNotificationData() {
        dataManager.getHomeNotifications(context)
                .doOnNext(wrapper -> dataManager.getRealmManager().saveNotificationWrapper(wrapper))
                .subscribe(notificationWrapper -> viewInterface.
                                updateNotificationData(notificationWrapper.getNotificationList()),
                        Throwable::printStackTrace);
        NotificationWrapper wrapper = dataManager.getRealmManager().getNotificationsWrapper();
        if (wrapper != null) {
            viewInterface.updateNotificationData(wrapper.getNotificationList());
        }
    }

    @Override
    public void getMoreNotifications() {
        dataManager.getNextNotifications(context)
                .subscribe(wrapper -> {
                    viewInterface.updateNotificationData(wrapper.getNotificationList());
                });
    }
}
