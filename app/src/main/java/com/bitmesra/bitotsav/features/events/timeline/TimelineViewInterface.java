package com.bitmesra.bitotsav.features.events.timeline;

import com.bitmesra.bitotsav.database.models.events.TimelineItem;

import java.util.List;

/**
 * Created by Batdroid on 5/2/17 for Bitotsav.
 */

public interface TimelineViewInterface {
    void updateTimelineEvents(List<TimelineItem> items);
}
