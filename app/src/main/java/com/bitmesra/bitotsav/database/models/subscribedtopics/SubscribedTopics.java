package com.bitmesra.bitotsav.database.models.subscribedtopics;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Batdroid on 9/2/17 for Bitotsav.
 */

/***
 * Subscribed topics of user
 * */
public class SubscribedTopics extends RealmObject {
    @PrimaryKey
    String topic;

    public SubscribedTopics() {
    }

    public SubscribedTopics(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
