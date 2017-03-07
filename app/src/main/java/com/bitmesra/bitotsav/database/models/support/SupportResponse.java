package com.bitmesra.bitotsav.database.models.support;

/**
 * Created by Batdroid on 7/3/17 for Bitotsav.
 */

public class SupportResponse {
    String url;
    String error;
    String message;

    public SupportResponse(String url, String error, String message) {
        this.url = url;
        this.error = error;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
