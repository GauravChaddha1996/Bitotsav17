package com.bitmesra.bitotsav.features.events;

import com.bitmesra.bitotsav.database.models.events.EventItem;

import java.util.List;

/**
 * Created by Batdroid on 5/2/17 for Bitotsav.
 */

public interface EventPresenterInterface {
    List<EventItem> getEventList();
}
