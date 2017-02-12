package com.bitmesra.bitotsav.features.home;

import android.content.Context;

import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.database.models.home.NotificationItem;

import io.realm.RealmResults;

/**
 * Created by Batdroid on 26/1/17 for Bitotsav.
 */

public class HomePresenter implements HomePresenterInterface {
    private Context context;
    private DataManager dataManager;
    private HomeViewInterface viewInterface;

    public HomePresenter(Context context, HomeViewInterface viewInterface) {
        this.context = context;
        dataManager = DataManager.getDataManager();
        this.viewInterface = viewInterface;
    }

    @Override
    public RealmResults<NotificationItem> getNotifications() {
        return dataManager.getRealmManager().getNotificationDto();
    }

    public void getNotificationsFromServer() {
        dataManager.getNotifications(context)
                .subscribe(items -> {
                    viewInterface.hideLoading();
                    dataManager.getRealmManager().saveNotifications(items);
                }, throwable -> viewInterface.showError());
    }
}
