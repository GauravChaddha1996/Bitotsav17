package com.bitmesra.bitotsav.features.events.flagship;

import com.bitmesra.bitotsav.database.models.events.FlagshipItem;

import java.util.List;

/**
 * Created by Batdroid on 5/2/17 for Bitotsav.
 */

public interface FlagshipPresenterInterface {
    List<FlagshipItem> getFlagshipEvents();
}
