package com.bitmesra.bitotsav.features.events;

import android.content.Context;

import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.database.models.events.EventItem;

import java.util.List;

/**
 * Created by Batdroid on 5/2/17 for Bitotsav.
 */

public class EventPresenter implements EventPresenterInterface {
    Context context;
    EventViewInterface viewInterface;
    DataManager dataManager;

    public EventPresenter(Context context, EventViewInterface viewInterface) {
        this.context = context;
        this.viewInterface = viewInterface;
        dataManager = DataManager.getDataManager();
    }

    @Override
    public List<EventItem> getEventList() {
        return dataManager.getEventList();
    }
}
