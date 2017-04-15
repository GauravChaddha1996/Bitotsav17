package com.bitmesra.bitotsav.database.models.todoranchi;

/**
 * Created by Batdroid on 7/3/17 for Bitotsav.
 */

public class TodoModel {
    String title;
    String desc;
    String url;

    public TodoModel(String title, String desc, String url) {
        this.title = title;
        this.desc = desc;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
