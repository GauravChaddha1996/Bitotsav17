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
    private String time;
    private String venue;
    private int money;
    private String rules;

    public EventDto() {
    }

    public EventDto(String name, int eventDtoType, String time, String venue, int money, String rules) {
        this.name = name;
        this.eventDtoType = eventDtoType;
        this.time = time;
        this.venue = venue;
        this.money = money;
        this.rules = rules;
    }

    public String getName() {
        return name;
    }

    public EventDto setName(String name) {
        this.name = name;
        return this;
    }

    public int getEventDtoType() {
        return eventDtoType;
    }

    public EventDto setEventDtoType(int eventDtoType) {
        this.eventDtoType = eventDtoType;
        return this;
    }

    public String getTime() {
        return time;
    }

    public EventDto setTime(String time) {
        this.time = time;
        return this;
    }

    public String getVenue() {
        return venue;
    }

    public EventDto setVenue(String venue) {
        this.venue = venue;
        return this;
    }

    public int getMoney() {
        return money;
    }

    public EventDto setMoney(int money) {
        this.money = money;
        return this;
    }

    public String getRules() {
        return rules;
    }

    public EventDto setRules(String rules) {
        this.rules = rules;
        return this;
    }
}
