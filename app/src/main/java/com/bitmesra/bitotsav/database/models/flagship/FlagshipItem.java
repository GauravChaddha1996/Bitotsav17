package com.bitmesra.bitotsav.database.models.flagship;

/**
 * Created by Batdroid on 5/2/17 for Bitotsav.
 */
/*
* Shown in flagship event list
* */
public class FlagshipItem {
    private String name;
    private String organizingClub;
    private int backgroundId;

    public FlagshipItem(String name, String organizingClub, int backgroundId) {
        this.name = name;
        this.organizingClub = organizingClub;
        this.backgroundId = backgroundId;
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

    public int getBackgroundId() {
        return backgroundId;
    }

    public void setBackgroundId(int backgroundId) {
        this.backgroundId = backgroundId;
    }
}
