package com.bitmesra.bitotsav.database.models.events;

/**
 * Created by Batdroid on 5/2/17 for Bitotsav.
 */

public class EventItem {
    private String type;
    private int backGroundImageId;

    public EventItem(String type, int backGroundImageId) {
        this.type = type;
        this.backGroundImageId = backGroundImageId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBackGroundImageId() {
        return backGroundImageId;
    }

    public void setBackGroundImageId(int backGroundImageId) {
        this.backGroundImageId = backGroundImageId;
    }
}
