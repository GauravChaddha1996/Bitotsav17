package com.bitmesra.bitotsav.features.home;

/**
 * Created by Batdroid on 26/1/17 for Bitotsav.
 */

public interface HomePresenterInterface {
    public void getRecentNotifications();
    public void getNextNotifications(long id);
    public void getLatestNotifications(long id);
}
