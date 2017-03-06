package com.bitmesra.bitotsav.database.models.tshirt;

/**
 * Created by Batdroid on 5/3/17 for Bitotsav.
 */

public class TShirtBookResponse {
    String teeId;
    String error;
    String email;
    String message;

    public TShirtBookResponse(String teeId, String error, String email, String message) {
        this.teeId = teeId;
        this.error = error;
        this.email = email;
        this.message = message;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
