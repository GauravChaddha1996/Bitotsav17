package com.bitmesra.bitotsav.features.details;

import android.content.Context;

import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.database.models.events.EventDto;

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

    @Override
    public void fetchDetailsDto(String name, int eventDtoType) {
        dataManager.getEventDetails(context, name)
                .doOnNext(detailsDto -> dataManager.getRealmManager()
                        .saveDetailsDto(name, eventDtoType,
                                detailsDto.getTime(), detailsDto.getVenue(),
                                detailsDto.getMoney(), detailsDto.getRules()))
                .subscribe(detailsDto -> viewInterface.updateDetailView(new EventDto()
                                .setName(name)
                                .setEventDtoType(eventDtoType)
                                .setTime(detailsDto.getTime())
                                .setVenue(detailsDto.getVenue())
                                .setMoney(detailsDto.getMoney())
                                .setRules(detailsDto.getRules())),
                        Throwable::printStackTrace);
    }

    public void getDetailsDtoFromRealm(String name) {
        EventDto dto = dataManager.getRealmManager().getDetailsDto(name);
        if (dto != null) {
            viewInterface.updateDetailView(dto);
        }
    }

    @Override
    public boolean isTopicSubscribed(String name) {
        return dataManager.getRealmManager().isTopicSubscribed(name);
    }

    @Override
    public void unsubscribeFromTopic(String name) {
        dataManager.getRealmManager().removeSubscribedTopic(name);
    }

    @Override
    public void subscribeToTopic(String name) {
        dataManager.getRealmManager().saveSubscribedTopic(name);
    }
}
