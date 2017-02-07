package com.bitmesra.bitotsav.features.flagships.flagshipDetails;

import android.content.Context;

import com.bitmesra.bitotsav.database.DataManager;

/**
 * Created by Batdroid on 7/2/17 for Bitotsav.
 */

public class FlagshipDetailPresenter implements FlagshipDetailPresenterInterface {
    Context context;
    FlagshipDetailViewInterface viewInterface;
    DataManager dataManager;

    public FlagshipDetailPresenter(Context context, FlagshipDetailViewInterface viewInterface) {
        this.context = context;
        this.viewInterface = viewInterface;
        dataManager = DataManager.getDataManager();
    }



}
