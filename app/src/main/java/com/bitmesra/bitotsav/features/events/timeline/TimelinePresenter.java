package com.bitmesra.bitotsav.features.events.timeline;

import android.content.Context;
import android.util.Log;

import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.database.models.events.TimelineDto;
import com.bitmesra.bitotsav.database.models.events.TimelineItem;

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
        dataManager.getTimelineList(context, dayNumber)
                .doOnNext(items -> {
                    if(items!=null || !items.isEmpty()) {
                        Log.d("rr","Items size:"+items.size());
                        Log.d("tag","Items 0:"+ items.get(0).toString());
                        dataManager.getRealmManager().saveTimelineEvents(
                                new TimelineDto(dayNumber, items));
                    }
                })
                .subscribe(items -> viewInterface.updateTimelineEvents(items),
                        Throwable::printStackTrace);
        List<TimelineItem> list = dataManager.getRealmManager().getTimelineEvents(dayNumber);
        if (list != null) {
            viewInterface.updateTimelineEvents(list);
        }
    }
}
