package com.bitmesra.bitotsav.features.details;

import com.bitmesra.bitotsav.database.models.details.EventDto;

/**
 * Created by Batdroid on 7/2/17 for Bitotsav.
 */

public interface DetailsViewInterface {
    void showAchievment();
    void hideAchievment();
    void errorAchievment();
    void partialUpdateDetailView();
    void updateDetailView(EventDto eventDto);
}
