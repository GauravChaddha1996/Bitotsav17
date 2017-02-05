package com.bitmesra.bitotsav.features.events.informal;

import com.bitmesra.bitotsav.database.models.events.InformalItem;

import java.util.List;

/**
 * Created by Batdroid on 5/2/17 for Bitotsav.
 */

public interface InformalPresenterInterface {
    List<InformalItem> getInformalEvents();
}
