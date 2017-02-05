package com.bitmesra.bitotsav.database.models.events;

/**
 * Created by Batdroid on 5/2/17 for Bitotsav.
 */

public class InformalItem {
    private String name;
    private String organizingClub;

    public InformalItem(String name, String organizingClub) {
        this.name = name;
        this.organizingClub = organizingClub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizingClub() {
        return organizingClub;
    }

    public void setOrganizingClub(String organizingClub) {
        this.organizingClub = organizingClub;
    }
}
