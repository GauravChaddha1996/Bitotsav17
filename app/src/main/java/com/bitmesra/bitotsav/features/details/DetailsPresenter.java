package com.bitmesra.bitotsav.features.details;

import android.content.Context;

import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.database.models.details.EventDto;
import com.bitmesra.bitotsav.database.models.details.ExampleModel;
import com.bitmesra.bitotsav.features.EventDtoType;

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
    public void fetchDetailsDto(String name, String id, int eventDtoType) {
        if (eventDtoType == EventDtoType.TYPE_FLAGSHIP) {
            dataManager.getFlagshipEventDetails(context, getFlagshipId(name))
                    .doOnSubscribe(() -> viewInterface.showAchievment())
                    .doOnNext(eventDto -> dataManager.getRealmManager()
                            .saveDetailsDto(eventDtoFromExampleModel(name, eventDtoType, eventDto)))
                    .subscribe(eventDto -> {
                        viewInterface.hideAchievment();
                        viewInterface.updateDetailView(eventDtoFromExampleModel(name, eventDtoType, eventDto));
                    }, throwable -> {
                        throwable.printStackTrace();
                        viewInterface.errorAchievment();
                    });
        } else {
            dataManager.getDayEventDetails(context, id)
                    .doOnSubscribe(() -> viewInterface.showAchievment())
                    .doOnNext(exampleModel -> dataManager.getRealmManager()
                            .saveDetailsDto(eventDtoFromExampleModel(name, eventDtoType, exampleModel)))
                    .subscribe(detailsDto -> {
                                viewInterface.hideAchievment();
                                //viewInterface.updateDetailView(eventDtoFromExampleModel(name, eventDtoType, detailsDto));
                            },
                            throwable -> {
                                viewInterface.errorAchievment();
                                throwable.printStackTrace();
                            });

        }
    }

    public void getDetailsDtoFromRealm(String name) {
        EventDto dto = dataManager.getRealmManager().getDetailsDto(name);
        if (dto != null) {
            dto.addChangeListener(element -> viewInterface.updateDetailView((EventDto) element));
            viewInterface.updateDetailView(dto);
        } else {
            viewInterface.partialUpdateDetailView();
        }
    }

    public void removeChangeListener(String name) {
        EventDto dto = dataManager.getRealmManager().getDetailsDto(name);
        if (dto != null) dto.removeChangeListeners();
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

    private int getFlagshipId(String name) {
        return dataManager.getFlagshipId(name);
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
