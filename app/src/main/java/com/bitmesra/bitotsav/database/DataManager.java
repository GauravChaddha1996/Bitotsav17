package com.bitmesra.bitotsav.database;

import android.content.Context;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.events.EventItem;
import com.bitmesra.bitotsav.database.models.events.FlagshipItem;
import com.bitmesra.bitotsav.database.models.events.InformalItem;
import com.bitmesra.bitotsav.database.models.events.TimelineItem;
import com.bitmesra.bitotsav.database.models.home.NotificationDto;

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

    public Observable<List<TimelineItem>> getTimelineList(Context context, int dayNumber) {
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

    public List<InformalItem> getInformalList() {
        List<InformalItem> list = new ArrayList<>();
        list.add(new InformalItem("Rangmanch", "Dramsoc"));
        list.add(new InformalItem("I,Me,Myself", "Dramsoc"));
        list.add(new InformalItem("Tune up together", "Dance"));
        list.add(new InformalItem("Foot loose", "Dance"));
        list.add(new InformalItem("Panacha", "FAS"));
        list.add(new InformalItem("Face painting", "FAS"));
        list.add(new InformalItem("Caricature", "FAS"));
        list.add(new InformalItem("Antakshari", "Music"));
        list.add(new InformalItem("Solo talent", "Music"));
        list.add(new InformalItem("Acoustic Alchemy", "Music"));
        list.add(new InformalItem("Food mania", "Rotaract"));
        list.add(new InformalItem("BIT Roadies", "Rotaract"));
        list.add(new InformalItem("Temple run", "Rotaract"));
        list.add(new InformalItem("Google it out", "IET"));
        list.add(new InformalItem("Blitzgreik", "IET"));
        list.add(new InformalItem("SNAP it", "PSOC"));
        list.add(new InformalItem("Short Story", "PSOC"));
        list.add(new InformalItem("Look for it", "PSOC"));
        list.add(new InformalItem("Beauty and the geek", "LEO"));
        list.add(new InformalItem("Twister", "LEO"));
        list.add(new InformalItem("Debate", "Unesquo"));
        list.add(new InformalItem("Jam", "Unesquo"));
        list.add(new InformalItem("Quiz", "Unesquo"));
        list.add(new InformalItem("Da Vinci code", "IEEE"));
        list.add(new InformalItem("Age of empires", "IEEE"));
        list.add(new InformalItem("Gyptic crossword", "Litsoc"));
        list.add(new InformalItem("Poetry Slam", "Litsoc"));
        list.add(new InformalItem("What am i;call it", "NAPS"));
        list.add(new InformalItem("@12:10", "NAPS"));
        list.add(new InformalItem("Naps quiz", "NAPS"));
        list.add(new InformalItem("You are hired", "SMP"));
        list.add(new InformalItem("Partner in drive", "SMP"));
        list.add(new InformalItem("EDC workshop", "EDC"));
        list.add(new InformalItem("Bussiness quiz", "EDC"));
        list.add(new InformalItem("Capture the flag", "ACM"));
        list.add(new InformalItem("Bug hunt", "ACM"));
        list.add(new InformalItem("R3", "EPAC"));
        list.add(new InformalItem("The Holey Pot", "EPAC"));
        list.add(new InformalItem("Road rash", "Robolution"));
        list.add(new InformalItem("Save the egg", "SAE"));
        list.add(new InformalItem("Auto quiz", "SAE"));
        list.add(new InformalItem("Criminal case", "IETE"));
        return list;
    }

    public RealmManager getRealmManager() {
        createRealmManager();
        return realmManager;
    }

    private static class DataManagerHelper {
        private static final DataManager INSTANCE = new DataManager();
    }

}

