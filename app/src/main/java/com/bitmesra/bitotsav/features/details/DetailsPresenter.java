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
                .doOnSubscribe(() -> viewInterface.showAchievment())
                .doOnNext(detailsDto -> {
                    viewInterface.hideLoading();
                    dataManager.getRealmManager()
                            .saveDetailsDto(name, eventDtoType,
                                    detailsDto.getTime(), detailsDto.getVenue(),
                                    detailsDto.getMoney(), detailsDto.getRules());
                })
                .subscribe(detailsDto -> {
                            viewInterface.hideAchievment();
                            viewInterface.updateDetailView(new EventDto()
                                    .setName(name)
                                    .setEventDtoType(eventDtoType)
                                    .setTime(detailsDto.getTime())
                                    .setVenue(detailsDto.getVenue())
                                    .setMoney(detailsDto.getMoney())
                                    .setRules(detailsDto.getRules()));
                        },
                        throwable -> {
                            viewInterface.errorAchievment();
                            viewInterface.showError();
                        });
    }

    public void getDetailsDtoFromRealm(String name) {
        EventDto dto = dataManager.getRealmManager().getDetailsDto(name);
        if (dto != null) {
            dto.addChangeListener(element -> viewInterface.updateDetailView((EventDto) element));
            viewInterface.updateDetailView(dto);
        } else {
            viewInterface.showLoading();
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

    public String getImageName(String name) {
        return dataManager.getEventImageName(name);
    }
    public String getDescription(String name) {
        return dataManager.getEventDesc(name);
    }
}
