package com.bitmesra.bitotsav.features.home;

import android.content.Context;

/**
 * Created by Batdroid on 26/1/17 for Bitotsav.
 */

public class HomePresenter implements HomePresenterInterface {
    private Context context;
    private HomeViewInterface viewInterface;

    public HomePresenter(Context context, HomeViewInterface viewInterface) {
        this.context = context;
        this.viewInterface = viewInterface;
    }
}
