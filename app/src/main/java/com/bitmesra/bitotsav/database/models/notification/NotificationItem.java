package com.bitmesra.bitotsav.database.models.notification;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Batdroid on 30/1/17 for Bitotsav.
 */

public class NotificationItem extends RealmObject {
    @PrimaryKey
    private String id;
    private String title;
    private String body;
    private String from;
    private long time;

    public NotificationItem() {
    }

    public NotificationItem(String id, String title, String body, String from, long time) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.from = from;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public NotificationItem setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NotificationItem setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBody() {
        return body;
    }

    public NotificationItem setBody(String body) {
        this.body = body;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public NotificationItem setFrom(String from) {
        this.from = from;
        return this;
    }

    public long getTime() {
        return time;
    }

    public NotificationItem setTime(long time) {
        this.time = time;
        return this;
    }
}
