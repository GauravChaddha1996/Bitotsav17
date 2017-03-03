package com.bitmesra.bitotsav.database.models.events;

import com.bitmesra.bitotsav.features.EventDtoType;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Batdroid on 7/2/17 for Bitotsav.
 */
/*
Model which is stored in realm
* */
public class EventDto extends RealmObject {
    @PrimaryKey
    private String name;
    private String _id;
    private String description = "description";
    private int eventDtoType = EventDtoType.TYPE_FLAGSHIP;
    private String time = "time";
    private String venue = "venue";
    private String money = "20";
    private String points = "points";
    private String participantsCount = " participants : 2";
    private String rules = "rules";
    private String imageurl = "";

    public EventDto() {
    }

    public EventDto(String name, int eventDtoType, String time, String venue, String money, String points, String participantsCount, String rules) {
        this.name = name;
        this.eventDtoType = eventDtoType;
        this.time = time;
        this.venue = venue;
        this.money = money;
        this.points = points;
        this.participantsCount = participantsCount;
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

    public String getDescription() {
        return description;
    }

    public EventDto setDescription(String description) {
        this.description = description;
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

    public String getMoney() {
        return money;
    }

    public EventDto setMoney(String money) {
        this.money = money;
        return this;
    }

    public String getPoints() {
        return points;
    }

    public EventDto setPoints(String points) {
        this.points = points;
        return this;
    }

    public String getNoOfParticipants() {
        return participantsCount;
    }

    public String getRules() {
        return rules;
    }

    public EventDto setRules(String rules) {
        this.rules = rules;
        return this;
    }

    public String get_id() {
        return _id;
    }

    public EventDto set_id(String _id) {
        this._id = _id;
        return this;
    }

    public String getParticipantsCount() {
        return participantsCount;
    }

    public EventDto setParticipantsCount(String participantsCount) {
        this.participantsCount = participantsCount;
        return this;
    }

    public String getImageurl() {
        return imageurl;
    }

    public EventDto setImageurl(String imageurl) {
        this.imageurl = imageurl;
        return this;
    }
}
