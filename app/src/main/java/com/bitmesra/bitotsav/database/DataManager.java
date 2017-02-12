package com.bitmesra.bitotsav.database;

import android.content.Context;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.events.EventDetailsDto;
import com.bitmesra.bitotsav.database.models.events.EventDto;
import com.bitmesra.bitotsav.database.models.events.EventItem;
import com.bitmesra.bitotsav.database.models.events.FlagshipItem;
import com.bitmesra.bitotsav.database.models.home.NotificationItem;
import com.bitmesra.bitotsav.features.EventDtoType;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class DataManager {

    NetworkManager networkManager = null;
    RealmManager realmManager = null;

    private DataManager() {

    }

    public static DataManager getDataManager() {
        return DataManagerHelper.INSTANCE;
    }

    /**
     * Notifications funtion
     */

    public Observable<List<NotificationItem>> getNotifications(Context context) {
        createNetworkManager(context);
        return networkManager.getNotifications();
    }

    /**
     * Event functions
     */
    public List<EventItem> getEventList() {
        List<EventItem> list = new ArrayList<>();
        list.add(new EventItem("Day 1", R.drawable.home1));
        list.add(new EventItem("Day 2", R.drawable.home2));
        list.add(new EventItem("Day 3", R.drawable.home1));
        list.add(new EventItem("Day 4", R.drawable.home2));
        list.add(new EventItem("Day 5", R.drawable.home1));
        return list;
    }

    public Observable<List<EventDto>> getTimelineList(Context context, int dayNumber) {
        createNetworkManager(context);
        return networkManager.getTimelineEvents(dayNumber);
    }

    public List<FlagshipItem> getFlagshipList() {
        List<FlagshipItem> list = new ArrayList<>();
        list.add(new FlagshipItem("Pukaar", "Show your inner drama queen #tanmay", R.drawable.home2));
        list.add(new FlagshipItem("Saptak", "Show your inner drama queen #tanmay", R.drawable.home2));
        list.add(new FlagshipItem("Rhapsody", "Show your inner drama queen #tanmay", R.drawable.home2));
        list.add(new FlagshipItem("Dance Saga", "Show your inner drama queen #tanmay", R.drawable.home2));
        list.add(new FlagshipItem("Stomp the yard", "Show your inner drama queen #tanmay", R.drawable.home2));
        list.add(new FlagshipItem("Talkies", "PSOC", R.drawable.home2));
        list.add(new FlagshipItem("Mr. and Miss Bitotsav", "Rotaract", R.drawable.home2));
        list.add(new FlagshipItem("Mun", "Unesquo", R.drawable.home2));
        list.add(new FlagshipItem("B-Plan", "EDC", R.drawable.home2));
        list.add(new FlagshipItem("Overnight coding", "ACM club", R.drawable.home2));
        list.add(new FlagshipItem("Takeshi's Castle", "Robolution", R.drawable.home2));
        return list;
    }

    public List<EventDto> getInformalList() {
        List<EventDto> list = new ArrayList<>();
        list.add(new EventDto("Rangmanch", EventDtoType.TYPE_INFORMAL, "7 pm", "IC", 10000, "Some rules"));
        list.add(new EventDto("I, Me, Myself", EventDtoType.TYPE_INFORMAL, "7 pm", "IC", 10000, "Some rules"));
        list.add(new EventDto("Tune up together", EventDtoType.TYPE_INFORMAL, "7 pm", "IC", 10000, "Some rules"));
        list.add(new EventDto("Foot loose", EventDtoType.TYPE_INFORMAL, "7 pm", "IC", 10000, "Some rules"));
        list.add(new EventDto("Panacha", EventDtoType.TYPE_INFORMAL, "7 pm", "IC", 10000, "Some rules"));
        return list;
    }

    /**
     * Flagship functions
     */
    public Observable<EventDetailsDto> getEventDetails(Context context, String eventName) {
        createNetworkManager(context);
        return networkManager.getEventDetails(eventName);
    }


    /**
     * Helpers classes and functions
     */

    private void createNetworkManager(Context context) {
        if (networkManager == null) {
            networkManager = new NetworkManager(context);
        }
    }

    private void createRealmManager() {
        if (realmManager == null) {
            realmManager = new RealmManager();
        }
    }

    public RealmManager getRealmManager() {
        createRealmManager();
        return realmManager;
    }

    private static class DataManagerHelper {
        private static final DataManager INSTANCE = new DataManager();
    }

}

