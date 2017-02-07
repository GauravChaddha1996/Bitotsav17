package com.bitmesra.bitotsav.database;

import android.content.Context;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.events.EventDto;
import com.bitmesra.bitotsav.database.models.events.EventItem;
import com.bitmesra.bitotsav.database.models.events.FlagshipItem;
import com.bitmesra.bitotsav.database.models.flagship.FlagshipDetailsDto;
import com.bitmesra.bitotsav.database.models.home.NotificationDto;
import com.bitmesra.bitotsav.database.models.login.SignUpBody;
import com.bitmesra.bitotsav.database.models.login.SignUpResultBody;
import com.bitmesra.bitotsav.features.EventDtoType;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
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
     * Signup funtion
     */

    public Observable<Response<SignUpResultBody>> signUp(Context context, SignUpBody body) {
        createNetworkManager(context);
        return networkManager.signUp(body);
    }

    /**
     * Home - Notifications functions
     */
    public Observable<NotificationDto> getRecentNotifications(Context context) {
        createNetworkManager(context);
        return networkManager.getRecentNotifications();
    }

    public Observable<NotificationDto> getNextNotifications(Context context, long id) {
        createNetworkManager(context);
        return networkManager.getNextNotifications(id);
    }

    public Observable<NotificationDto> getLatestNotifications(Context context, long id) {
        createNetworkManager(context);
        return networkManager.getLatestNotifications(id);
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
        list.add(new EventItem("Informals", R.drawable.home1));
        return list;
    }

    public Observable<List<EventDto>> getTimelineList(Context context, int dayNumber) {
        createNetworkManager(context);
        return networkManager.getTimelineEvents(dayNumber);
    }

    public List<FlagshipItem> getFlagshipList() {
        List<FlagshipItem> list = new ArrayList<>();
        list.add(new FlagshipItem("Pukaar", "Dramsoc", R.drawable.home1));
        list.add(new FlagshipItem("Saptak", "Music club", R.drawable.home2));
        list.add(new FlagshipItem("Rhapsody", "Music club", R.drawable.home1));
        list.add(new FlagshipItem("Dance Saga", "Dance club", R.drawable.home2));
        list.add(new FlagshipItem("Stomp the yard", "Dance club", R.drawable.home1));
        list.add(new FlagshipItem("Talkies", "PSOC", R.drawable.home2));
        list.add(new FlagshipItem("Mr. and Miss Bitotsav", "Rotaract", R.drawable.home1));
        list.add(new FlagshipItem("Mun", "Unesquo", R.drawable.home2));
        list.add(new FlagshipItem("B-Plan", "EDC", R.drawable.home1));
        list.add(new FlagshipItem("Overnight coding", "ACM club", R.drawable.home2));
        list.add(new FlagshipItem("Takeshi's Castle", "Robolution", R.drawable.home1));
        return list;
    }

    public List<EventDto> getInformalList() {
        List<EventDto> list = new ArrayList<>();
        list.add(new EventDto("Rangmanch", EventDtoType.TYPE_INFORMAL, "Dramsoc", "", "", ""));
        list.add(new EventDto("I,Me,Myself", EventDtoType.TYPE_INFORMAL, "Dramsoc", "", "", ""));
        list.add(new EventDto("Tune up together", EventDtoType.TYPE_INFORMAL, "Dance", "", "", ""));
        list.add(new EventDto("Foot loose", EventDtoType.TYPE_INFORMAL, "Dance", "", "", ""));
        list.add(new EventDto("Panacha", EventDtoType.TYPE_INFORMAL, "FAS", "", "", ""));
        list.add(new EventDto("Face painting", EventDtoType.TYPE_INFORMAL, "FAS", "", "", ""));
        list.add(new EventDto("Caricature", EventDtoType.TYPE_INFORMAL, "FAS", "", "", ""));
        list.add(new EventDto("Antakshari", EventDtoType.TYPE_INFORMAL, "Music", "", "", ""));
        list.add(new EventDto("Solo talent", EventDtoType.TYPE_INFORMAL, "Music", "", "", ""));
        list.add(new EventDto("Acoustic Alchemy", EventDtoType.TYPE_INFORMAL, "Music", "", "", ""));
        list.add(new EventDto("Food mania", EventDtoType.TYPE_INFORMAL, "Rotaract", "", "", ""));
        list.add(new EventDto("BIT Roadies", EventDtoType.TYPE_INFORMAL, "Rotaract", "", "", ""));
        list.add(new EventDto("Temple run", EventDtoType.TYPE_INFORMAL, "Rotaract", "", "", ""));
        list.add(new EventDto("Google it out", EventDtoType.TYPE_INFORMAL, "IET", "", "", ""));
        list.add(new EventDto("Blitzgreik", EventDtoType.TYPE_INFORMAL, "IET", "", "", ""));
        list.add(new EventDto("SNAP it", EventDtoType.TYPE_INFORMAL, "PSOC", "", "", ""));
        list.add(new EventDto("Short Story", EventDtoType.TYPE_INFORMAL, "PSOC", "", "", ""));
        list.add(new EventDto("Look for it", EventDtoType.TYPE_INFORMAL, "PSOC", "", "", ""));
        list.add(new EventDto("Beauty and the geek", EventDtoType.TYPE_INFORMAL, "LEO", "", "", ""));
        list.add(new EventDto("Twister", EventDtoType.TYPE_INFORMAL, "LEO", "", "", ""));
        list.add(new EventDto("Debate", EventDtoType.TYPE_INFORMAL, "Unesquo", "", "", ""));
        list.add(new EventDto("Jam", EventDtoType.TYPE_INFORMAL, "Unesquo", "", "", ""));
        list.add(new EventDto("Quiz", EventDtoType.TYPE_INFORMAL, "Unesquo", "", "", ""));
        list.add(new EventDto("Da Vinci code", EventDtoType.TYPE_INFORMAL, "IEEE", "", "", ""));
        list.add(new EventDto("Age of empires", EventDtoType.TYPE_INFORMAL, "IEEE", "", "", ""));
        list.add(new EventDto("Gyptic crossword", EventDtoType.TYPE_INFORMAL, "Litsoc", "", "", ""));
        list.add(new EventDto("Poetry Slam", EventDtoType.TYPE_INFORMAL, "Litsoc", "", "", ""));
        list.add(new EventDto("What am i;call it", EventDtoType.TYPE_INFORMAL, "NAPS", "", "", ""));
        list.add(new EventDto("@12:10", EventDtoType.TYPE_INFORMAL, "NAPS", "", "", ""));
        list.add(new EventDto("Naps quiz", EventDtoType.TYPE_INFORMAL, "NAPS", "", "", ""));
        list.add(new EventDto("You are hired", EventDtoType.TYPE_INFORMAL, "SMP", "", "", ""));
        list.add(new EventDto("Partner in drive", EventDtoType.TYPE_INFORMAL, "SMP", "", "", ""));
        list.add(new EventDto("EDC workshop", EventDtoType.TYPE_INFORMAL, "EDC", "", "", ""));
        list.add(new EventDto("Bussiness quiz", EventDtoType.TYPE_INFORMAL, "EDC", "", "", ""));
        list.add(new EventDto("Capture the flag", EventDtoType.TYPE_INFORMAL, "ACM", "", "", ""));
        list.add(new EventDto("Bug hunt", EventDtoType.TYPE_INFORMAL, "ACM", "", "", ""));
        list.add(new EventDto("R3", EventDtoType.TYPE_INFORMAL, "EPAC", "", "", ""));
        list.add(new EventDto("The Holey Pot", EventDtoType.TYPE_INFORMAL, "EPAC", "", "", ""));
        list.add(new EventDto("Road rash", EventDtoType.TYPE_INFORMAL, "Robolution", "", "", ""));
        list.add(new EventDto("Save the egg", EventDtoType.TYPE_INFORMAL, "SAE", "", "", ""));
        list.add(new EventDto("Auto quiz", EventDtoType.TYPE_INFORMAL, "SAE", "", "", ""));
        list.add(new EventDto("Criminal case", EventDtoType.TYPE_INFORMAL, "IETE", "", "", ""));
        return list;
    }

    /**
     * Flagship functions
     */
    public Observable<FlagshipDetailsDto> getFlagshipEvent(Context context, String eventName) {
        createNetworkManager(context);
        return networkManager.getFlagshipEvent(eventName);
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

