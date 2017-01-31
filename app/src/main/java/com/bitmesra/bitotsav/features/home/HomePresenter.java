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
        if(wrapper!=null) {
            viewInterface.updateNotificationData(wrapper.getNotificationList());
        }
/*

        BitotsavNotification b1 = new BitotsavNotification(0, "This is notif 1", "time at 8:30");
        BitotsavNotification b2 = new BitotsavNotification(1, "This is notif 2", "time at 7:30");
        BitotsavNotification b3 = new BitotsavNotification(2, "This is notif 3", "time at 6:30");
        BitotsavNotification b4 = new BitotsavNotification(3, "This is notif 4", "time at 4:30");
        BitotsavNotification b5 = new BitotsavNotification(4, "This is notif 5", "time at 3:30");
        BitotsavNotification b6 = new BitotsavNotification(5, "This is notif 6", "time at 5:30");
        BitotsavNotification b7 = new BitotsavNotification(6, "This is notif 7", "time at 7:30");
        BitotsavNotification b8 = new BitotsavNotification(7, "This is notif 8", "time at 3:30");
        BitotsavNotification b9 = new BitotsavNotification(8, "This is notif 9", "time at 2:30");
        BitotsavNotification b10 = new BitotsavNotification(9, "This is notif 10", "time at 8:30");
        List<BitotsavNotification> list = new ArrayList<>();
        list.add(b1);
        list.add(b2);
        list.add(b3);
        list.add(b4);
        list.add(b5);
        list.add(b6);
        list.add(b7);
        list.add(b8);
        list.add(b9);
        list.add(b10);
        viewInterface.updateNotificationData(list);
*/
    }
}
