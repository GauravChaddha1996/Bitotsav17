package com.bitmesra.bitotsav.database.models.events;

/**
 * Created by Batdroid on 7/2/17 for Bitotsav.
 */

public class EventDetailsDto {
    private String name;
    private String time;
    private String venue;
    private int money;
    private String rules;

    public EventDetailsDto(String name, String time, String venue, int money, String rules) {
        this.name = name;
        this.time = time;
        this.venue = venue;
        this.money = money;
        this.rules = rules;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }
}
