package com.bitmesra.bitotsav.database.models.events;

import io.realm.RealmObject;

/**
 * Created by Batdroid on 5/2/17 for Bitotsav.
 */

public class TimelineItem extends RealmObject {
    private String name;
    private String time;
    private String venue;

    public TimelineItem() {
    }

    public TimelineItem(String name, String time, String venue) {
        this.name = name;
        this.time = time;
        this.venue = venue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    @Override
    public String toString() {
        return name + " " + time + " at " + venue;
    }
}
