package com.bitmesra.bitotsav.database.models.login;

/**
 * Created by Batdroid on 6/2/17 for Bitotsav.
 */

public class SignUpResultBody {
    private String bitId;

    public SignUpResultBody(String bitId) {
        this.bitId = bitId;
    }

    public String getBitId() {
        return bitId;
    }

    public void setBitId(String bitId) {
        this.bitId = bitId;
    }
}
