package com.bitmesra.bitotsav.features.nights;

import com.bitmesra.bitotsav.database.models.nights.NightsModel;

import java.util.List;

/**
 * Created by Batdroid on 5/2/17 for Bitotsav.
 */

public interface NightViewInterface {
    void showAchievment();
    void hideAchievment();
    void errorAchievment();
    void updateList(List<NightsModel> list);
}
