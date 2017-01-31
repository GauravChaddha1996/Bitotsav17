package com.bitmesra.bitotsav.database.models.home;

import io.realm.RealmObject;

/**
 * Created by Batdroid on 30/1/17 for Bitotsav.
 */

public class BitotsavNotification extends RealmObject{
    private long id;
    private String title;
    private String time;

    public BitotsavNotification() {
    }

    public BitotsavNotification(long id, String title, String time) {
        this.id = id;
        this.title = title;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "id:" + id + " title:" + title + " time:" + time + "\n";
    }
}
