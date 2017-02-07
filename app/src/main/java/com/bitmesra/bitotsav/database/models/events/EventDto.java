package com.bitmesra.bitotsav.database.models.events;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Batdroid on 7/2/17 for Bitotsav.
 */

public class EventDto extends RealmObject {
    @PrimaryKey
    private String name;
    private int eventDtoType;
    private String club;
    private String time;
    private String venue;
    private String dayNumber;

    public EventDto() {
    }

    public EventDto(String name, int eventDtoType, String club, String time, String venue, String dayNumber) {
        this.name = name;
        this.eventDtoType = eventDtoType;
        this.club = club;
        this.time = time;
        this.venue = venue;
        this.dayNumber = dayNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEventDtoType() {
        return eventDtoType;
    }

    public void setEventDtoType(int eventDtoType) {
        this.eventDtoType = eventDtoType;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
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

    public String getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(String dayNumber) {
        this.dayNumber = dayNumber;
    }
}
