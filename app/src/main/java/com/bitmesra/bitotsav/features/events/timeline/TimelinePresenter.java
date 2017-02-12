package com.bitmesra.bitotsav.features.events.timeline;

import android.content.Context;

import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.database.models.events.EventDto;

import java.util.List;

/**
 * Created by Batdroid on 5/2/17 for Bitotsav.
 */

public class TimelinePresenter implements TimelinePresenterInterface {
    Context context;
    TimelineViewInterface viewInterface;
    DataManager dataManager;

    public TimelinePresenter(Context context, TimelineViewInterface viewInterface) {
        this.context = context;
        this.viewInterface = viewInterface;
        dataManager = DataManager.getDataManager();
    }

    @Override
    public void getTimelineEvents(int dayNumber) {
        loadTimelineFromRealm(dayNumber);
        fetchTimelineEvents(dayNumber);
    }

    @Override
    public void fetchTimelineEvents(int dayNumber) {
        dataManager.getTimelineList(context, dayNumber)
                .doOnSubscribe(() -> viewInterface.showAchievment())
                .doOnNext(items -> {
                    if (items != null || !items.isEmpty()) {
                        dataManager.getRealmManager().saveTimelineEvents(items, dayNumber);
                    }
                })
                .subscribe(items -> {
                            viewInterface.hideAchievment();
                            viewInterface.hideLoading();
                            viewInterface.updateTimelineEvents(items);
                        },
                        throwable -> {
                            viewInterface.errorAchievment();
                            viewInterface.showError();
                        });
    }

    public void loadTimelineFromRealm(int dayNumber) {
        List<EventDto> list = dataManager.getRealmManager().getTimelineEvents(dayNumber);
        if (list.size() > 0) {
            viewInterface.updateTimelineEvents(list);
        } else {
            viewInterface.showLoading();
        }
    }
}
