package com.bitmesra.bitotsav.features.home;

import android.content.Context;

import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.database.models.home.NotificationDto;

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
    public void getRecentNotifications() {
        //Making network call to fetch new notifications
        dataManager.getRecentNotifications(context)
                .doOnNext(this::saveNotifications)
                .subscribe(dto -> viewInterface.updateRecentNotifications(dto.getNotificationList()),
                        Throwable::printStackTrace);
        //Setting notifications stored in Realm
        NotificationDto dto = dataManager.getRealmManager().getNotificationDto();
        if (dto != null) {
            viewInterface.updateRecentNotifications(dto.getNotificationList());
        }
    }

    public void saveNotifications(NotificationDto dto) {
        dataManager.getRealmManager().saveNotificationDto(dto);
    }

    @Override
    public void getNextNotifications(long id) {
        dataManager.getNextNotifications(context, id)
                .subscribe(dto -> viewInterface.updateNextNotifications(dto.getNotificationList()));
    }

    @Override
    public void getLatestNotifications(long id) {
        dataManager.getLatestNotifications(context, id)
                .subscribe(dto -> viewInterface.updateLatestNotifications(dto.getNotificationList()));
    }
}
