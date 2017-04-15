package com.bitmesra.bitotsav.features.details;

/**
 * Created by Batdroid on 7/2/17 for Bitotsav.
 */

public interface DetailsPresenterInterface {
    boolean isTopicSubscribed(String name);

    void fetchDetailsDto(String name, String id, int eventDtoType);

    void subscribeToTopic(String name);

    void unsubscribeFromTopic(String name);
}
