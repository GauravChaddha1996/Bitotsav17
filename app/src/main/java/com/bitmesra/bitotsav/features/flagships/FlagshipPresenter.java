package com.bitmesra.bitotsav.features.flagships;

import android.content.Context;

import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.database.models.events.FlagshipItem;

import java.util.List;

/**
 * Created by Batdroid on 5/2/17 for Bitotsav.
 */

public class FlagshipPresenter implements FlagshipPresenterInterface {
    Context context;
    FlagshipViewInterface viewInterface;
    DataManager dataManager;

    public FlagshipPresenter(Context context, FlagshipViewInterface viewInterface) {
        this.context = context;
        this.viewInterface = viewInterface;
        dataManager = DataManager.getDataManager();
    }

    @Override
    public List<FlagshipItem> getFlagshipEvents() {
        return dataManager.getFlagshipList();
    }
}
