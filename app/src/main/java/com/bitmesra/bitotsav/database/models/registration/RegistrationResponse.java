package com.bitmesra.bitotsav.database.models.registration;

/**
 * Created by Batdroid on 5/3/17 for Bitotsav.
 */

/***
 * Response we get when we try to register a user
 * */
public class RegistrationResponse {
    String bitId;
    String error;
    String message;
    String email;

    public RegistrationResponse(String bitId, String error, String message, String email) {
        this.bitId = bitId;
        this.error = error;
        this.message = message;
        this.email = email;
    }

    public String getBitId() {
        return bitId;
    }

    public void setBitId(String bitId) {
        this.bitId = bitId;
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
}
