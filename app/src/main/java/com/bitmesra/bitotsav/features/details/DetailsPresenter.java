package com.bitmesra.bitotsav.features.details;

import android.content.Context;

import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.database.models.events.EventDto;
import com.bitmesra.bitotsav.database.models.flagship.FlagshipDetailsDto;

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
        dataManager.getFlagshipEvent(context, name)
                .doOnNext(detailsDto -> dataManager.getRealmManager()
                        .saveDetailsDto(name, eventDtoType,
                                detailsDto.getTime(), detailsDto.getVenue()))
                .subscribe(detailsDto -> viewInterface.updateDetailView(detailsDto),
                        Throwable::printStackTrace);
        EventDto dto = dataManager.getRealmManager().getDetailsDto(name);
        if (dto != null) {
            viewInterface.updateDetailView(new FlagshipDetailsDto(dto.getVenue(), dto.getTime(), 50000));
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
