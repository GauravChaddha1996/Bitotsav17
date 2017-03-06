package com.bitmesra.bitotsav.database;

import android.content.Context;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.details.EventDto;
import com.bitmesra.bitotsav.database.models.details.ExampleModel;
import com.bitmesra.bitotsav.database.models.events.EventItem;
import com.bitmesra.bitotsav.database.models.flagship.FlagshipItem;
import com.bitmesra.bitotsav.database.models.notification.NotificationItem;
import com.bitmesra.bitotsav.database.models.payment.PayinfoResponse;
import com.bitmesra.bitotsav.database.models.payment.PaymentResponse;
import com.bitmesra.bitotsav.database.models.registration.RegistrationResponse;
import com.bitmesra.bitotsav.database.models.tshirt.TShirtBookResponse;
import com.bitmesra.bitotsav.database.models.tshirt.TeeinfoResponse;

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
     * Event, timeline and flagship functions
     */
    public List<EventItem> getEventList() {
        List<EventItem> list = new ArrayList<>();
        list.add(new EventItem("Day 1 (17th Mar)", R.drawable.home1));
        list.add(new EventItem("Day 2 (18th Mar)", R.drawable.home2));
        list.add(new EventItem("Day 3 (19th Mar)", R.drawable.home1));
        list.add(new EventItem("Day 4 (20th Mar)", R.drawable.home2));
        return list;
    }

    public Observable<List<EventDto>> getTimelineList(Context context, int dayNumber) {
        createNetworkManager(context);
        return networkManager.getTimelineEvents(dayNumber);
    }

    public List<FlagshipItem> getFlagshipList() {
        List<FlagshipItem> list = new ArrayList<>();
        list.add(new FlagshipItem("Nukkad", "", R.drawable.nukkad));
        list.add(new FlagshipItem("Mr. And Miss Bitotsav", "", R.drawable.mrmissbitotsav));
        list.add(new FlagshipItem("Rhapsody", "", R.drawable.rhapsody));
        list.add(new FlagshipItem("Dance Saga", "", R.drawable.dancesaga));
        list.add(new FlagshipItem("MUN", "", R.drawable.mun));
        list.add(new FlagshipItem("Saptak", "", R.drawable.saptak));
        list.add(new FlagshipItem("Stomp The Yard", "", R.drawable.stomptheyard));
        list.add(new FlagshipItem("Talkies", " ", R.drawable.talkies));
        list.add(new FlagshipItem("B-Plan", "", R.drawable.bplan));
        list.add(new FlagshipItem("CNC", "", R.drawable.cnc));
        return list;
    }

    /**
     * Helper events details functions
     */

    public String getEventImageName(String name) {
        switch (name) {
            case "Nukkad":
                return "nukkad";
            case "Mr. And Miss Bitotsav":
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
            case "B-Plan":
                return "bplan";
            case "CNC":
                return "cnc";
            default:
                return null;
        }
    }

    public String getEventDesc(String name) {
        return "Fetching the cool things about this event.... Just give us a sec";
    }


    public int getFlagshipId(String name) {
        switch (name) {
            case "Nukkad":
                return 1;
            case "Mr. And Miss Bitotsav":
                return 2;
            case "Rhapsody":
                return 3;
            case "Dance Saga":
                return 4;
            case "MUN":
                return 5;
            case "Saptak":
                return 6;
            case "Stomp The Yard":
                return 7;
            case "Talkies":
                return 8;
            case "B-Plan":
                return 9;
            case "CNC":
                return 10;
            default:
                return 0;
        }
    }

    /**
     * Flagship functions
     */
    public Observable<ExampleModel> getFlagshipEventDetails(Context context, int id) {
        createNetworkManager(context);
        return networkManager.getFlagshipEventDetails(id);
    }

    public Observable<ExampleModel> getDayEventDetails(Context context, String id) {
        createNetworkManager(context);
        return networkManager.getDayEventDetails(id);
    }


    /**
     * Registration and payment
     */
    public Observable<RegistrationResponse> register(Context context, String name, String email, String phone, String college, String sap) {
        createNetworkManager(context);
        return networkManager.register(name, email, phone, college, sap);
    }

    public Observable<RegistrationResponse> teamRegister(Context context, String teamName, String heademail, String headContact,
                                                         String events, String members, String headBitId, String college) {
        createNetworkManager(context);
        return networkManager.teamRegister(teamName, heademail, headContact, events, members, headBitId, college);
    }

    public Observable<PayinfoResponse> getPaymentInfo(Context context, String bitId, String email) {
        createNetworkManager(context);
        return networkManager.getPaymentInfo(bitId, email);
    }

    public Observable<PaymentResponse> getPaymentUrl(Context context, String bitId, String email, int packageNumber) {
        createNetworkManager(context);
        return networkManager.getPaymentUrl(bitId, email, packageNumber);
    }

    /**
     * T Shirt
     */
    public Observable<TShirtBookResponse> bookTShirt(Context context, String name, String email, String phone, String size, String college, String room) {
        createNetworkManager(context);
        return networkManager.bookTShirt(name, email, phone, size, college, room);
    }

    public Observable<TeeinfoResponse> getTeeInfo(Context context, String teeId, String email) {
        createNetworkManager(context);
        return networkManager.getTeeInfo(teeId, email);
    }

    public Observable<PaymentResponse> getTeePaymentUrl(Context context, String teeId, String email, String size) {
        createNetworkManager(context);
        return networkManager.getTShirtPaymentUrl(teeId, email, size);
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

