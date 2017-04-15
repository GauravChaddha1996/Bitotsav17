package com.bitmesra.bitotsav.features.events.timeline;

/**
 * Created by Batdroid on 5/2/17 for Bitotsav.
 */

public interface TimelinePresenterInterface {
    void getTimelineEvents(int dayNumber);
    void fetchTimelineEvents(int dayNumber);
}
