package com.bitmesra.bitotsav.database.models;

/**
 * Created by Batdroid on 5/3/17 for Bitotsav.
 */

public class TeeinfoResponse {
    String teeId;
    String error;
    String message;
    String email;
    String name;
    String college;

    public TeeinfoResponse(String teeId, String error, String message, String email, String name, String college) {
        this.teeId = teeId;
        this.error = error;
        this.message = message;
        this.email = email;
        this.name = name;
        this.college = college;
    }

    public String getTeeId() {
        return teeId;
    }

    public void setTeeId(String teeId) {
        this.teeId = teeId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
