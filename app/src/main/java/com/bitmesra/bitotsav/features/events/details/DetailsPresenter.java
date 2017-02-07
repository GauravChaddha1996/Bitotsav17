package com.bitmesra.bitotsav.features.events.details;

import android.content.Context;

import com.bitmesra.bitotsav.database.DataManager;

/**
 * Created by Batdroid on 7/2/17 for Bitotsav.
 */

public class DetailsPresenter implements DetailsPresenterInterface {
    Context context;
    DetailsViewInterface viewInterface;
    DataManager dataManager;

    public DetailsPresenter(Context context, DetailsViewInterface viewInterface) {
        this.context = context;
        this.viewInterface = viewInterface;
        dataManager = DataManager.getDataManager();
    }
}
