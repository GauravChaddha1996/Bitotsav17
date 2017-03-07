package com.bitmesra.bitotsav.features.events.timeline;

import android.content.Context;

import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.database.models.details.EventDto;
import com.bitmesra.bitotsav.database.models.details.ExampleModel;
import com.bitmesra.bitotsav.features.EventDtoType;
import com.bitmesra.bitotsav.utils.Utils;

import java.util.ArrayList;
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
                        List<EventDto> list = new ArrayList<EventDto>();
                        for (ExampleModel model : items) {
                            list.add(eventDtoFromExampleModel(model.getName(),
                                    Utils.findEventDtoDayType(dayNumber), model));
                        }
                        dataManager.getRealmManager().saveTimelineEvents(list, dayNumber);
                    }
                })
                .subscribe(items -> {
                    viewInterface.hideAchievment();
                    List<EventDto> list = new ArrayList<>();
                    for (ExampleModel model : items) {
                        list.add(eventDtoFromExampleModel(model.getName(),
                                Utils.findEventDtoDayType(dayNumber), model));
                    }
                    viewInterface.updateTimelineEvents(list);
                }, throwable -> viewInterface.errorAchievment());
    }

    public void loadTimelineFromRealm(int dayNumber) {
        List<EventDto> list = dataManager.getRealmManager().getTimelineEvents(dayNumber);
        if (list.size() > 0) {
            viewInterface.updateTimelineEvents(list);
        }
    }

    private EventDto eventDtoFromExampleModel(String name, int eventDtoType, ExampleModel exampleModel) {
        return new EventDto()
                .setName(name)
                .set_id(exampleModel.getId())
                .setEventDtoType(eventDtoType)
                .setTime(exampleModel.getTime())
                .setVenue(exampleModel.getVenue())
                .setDescription(exampleModel.getAbout())
                .setRules(exampleModel.getDescription())
                .setEventDtoType(EventDtoType.TYPE_FLAGSHIP)
                .setMoney(exampleModel.getMoney())
                .setImageurl(exampleModel.getImage())
                .setPoints(exampleModel.getPoints())
                .setParticipantsCount(exampleModel.getParticipantsCount());
    }
}
