package com.bitmesra.bitotsav.features.events.informal;

import android.content.Context;

import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.database.models.events.EventDto;

import java.util.List;

/**
 * Created by Batdroid on 5/2/17 for Bitotsav.
 */

public class InformalPresenter implements InformalPresenterInterface {
    Context context;
    InformalViewInterface viewInterface;
    DataManager dataManager;

    public InformalPresenter(Context context, InformalViewInterface viewInterface) {
        this.context = context;
        this.viewInterface = viewInterface;
        dataManager = DataManager.getDataManager();
    }

    @Override
    public List<EventDto> getInformalEvents() {
        return dataManager.getInformalList();
    }
}
