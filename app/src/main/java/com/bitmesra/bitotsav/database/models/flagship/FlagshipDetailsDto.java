package com.bitmesra.bitotsav.database.models.flagship;

/**
 * Created by Batdroid on 7/2/17 for Bitotsav.
 */

public class FlagshipDetailsDto {
    private String venue;
    private String time;
    private int money;

    public FlagshipDetailsDto(String venue, String time, int money) {
        this.venue = venue;
        this.time = time;
        this.money = money;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
