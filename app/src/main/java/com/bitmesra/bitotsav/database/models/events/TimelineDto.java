package com.bitmesra.bitotsav.database.models.events;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Batdroid on 5/2/17 for Bitotsav.
 */

public class TimelineDto extends RealmObject {
    @PrimaryKey
    int dayNumber;
    RealmList<TimelineItem> list = new RealmList<>();

    public TimelineDto() {
    }

    public TimelineDto(int dayNumber, List<TimelineItem> list) {
        this.dayNumber = dayNumber;
        this.list.addAll(list);
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public RealmList<TimelineItem> getList() {
        return list;
    }

    public void setList(RealmList<TimelineItem> list) {
        this.list = list;
    }
}
