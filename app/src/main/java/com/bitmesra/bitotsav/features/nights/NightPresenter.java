package com.bitmesra.bitotsav.features.nights;

import android.content.Context;

import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.database.models.nights.NightsModel;

import java.util.List;

/**
 * Created by Batdroid on 5/2/17 for Bitotsav.
 */

public class NightPresenter implements NightPresenterInterface {
    Context context;
    NightViewInterface viewInterface;
    DataManager dataManager;

    public NightPresenter(Context context, NightViewInterface viewInterface) {
        this.context = context;
        this.viewInterface = viewInterface;
        dataManager = DataManager.getDataManager();
    }

    @Override
    public void getNightsEvents() {
        loadFromRealm();
        fetchFromServer();
    }

    private void loadFromRealm() {
        List<NightsModel> list = dataManager.getRealmManager().getNightEvents();
        if (list.size() > 0) {
            viewInterface.updateList(list);
        }
    }

    private void fetchFromServer() {
        dataManager.fetchNightList(context)
                .doOnSubscribe(() -> viewInterface.showAchievment())
                .doOnNext(nightsModels -> dataManager.getRealmManager().saveNightEvents(nightsModels))
                .subscribe(nightsModels -> {
                    for (int i = 0; i < nightsModels.size(); i++) {
                        nightsModels.get(i).setId(i);
                    }
                    viewInterface.hideAchievment();
                    viewInterface.updateList(nightsModels);
                }, throwable -> {
                    throwable.printStackTrace();
                    viewInterface.errorAchievment();
                });
    }
}
