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
        list.add(new FlagshipItem("Nukkad", "This is a street play competition.", R.drawable.nukkad));
        list.add(new FlagshipItem("Mr And Miss Bitotsav", "This event provides a platform to the fashion enthusiasts", R.drawable.mrmissbitotsav));
        list.add(new FlagshipItem("Rhapsody", "In this contest bands present musical performances from western genre and compete for the title. ", R.drawable.rhapsody));
        list.add(new FlagshipItem("Dance Saga", "This is a group event to showcase your dancing talent", R.drawable.dancesaga));
        list.add(new FlagshipItem("MUN", "If you could rule the world for a day, what would you do?", R.drawable.mun));
        list.add(new FlagshipItem("Saptak", "Eastern Battle of bands", R.drawable.saptak));
        list.add(new FlagshipItem("Stomp The Yard", "Sparks fly as the worlds of street dance collide in this exciting flagship event.", R.drawable.stomptheyard));
        list.add(new FlagshipItem("Talkies", "Short Film Making competition with theme related to the social theme of Bitotsav. ", R.drawable.talkies));
        return list;
    }

    public String getEventImageName(String name) {
        switch (name) {
            case "Nukkad":
                return "nukkad";
            case "Mr And Miss Bitotsav":
                return "mrmissbitotsav";
            case "Dance Saga":
                return "dancesaga";
            case "MUN":
                return "mun";
            case "Saptak":
                return "saptak";
            case "Rhapsody":
                return "rhapsody";
            case "Stomp The Yard":
                return "stomptheyard";
            case "Talkies":
                return "talkies";
            default:
                return "template_image";
        }
    }

    public String getEventDesc(String name) {
        switch (name) {

            case "Nukkad":
                return "This is a street play competition. Addressing the social issues, this event full of powerpacked and energetic performances.";
            case "Mr And Miss Bitotsav":
                return "This event provides a platform to the fashion enthusiasts. The participants get a chance to showcase their personality and confidence.";
            case "Dance Saga":
                return "This is a group event to showcase your dancing talent. The participants ostentatiously display their dance styles and leave the crowd wanting more.";
            case "MUN":
                return "If you could rule the world for a day, what would you do? The BIT MUN is your chance to become a world leader and solve issues at the highest level. ";
            case "Saptak":
                return "A contest where bands from various colleges present musical performances from eastern genre and compete for the title of \"best band\".";
            case "Rhapsody":
                return "In this contest bands present musical performances from western genre and compete for the title. ";
            case "Stomp The Yard":
                return "Sparks fly as the worlds of street dance collide in this exciting flagship event. All you have got to do is live up to the challenge of the rivals. So come with your big on attitude style and stomp the yard. Participate in one of the most awaited event of Bitotsav and expereience the real street dancing; experience what it is to fly.";
            case "Talkies":
                return "Short Film Making competition with theme related to the social theme of Bitotsav";
            default:
                return "";
        }
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

