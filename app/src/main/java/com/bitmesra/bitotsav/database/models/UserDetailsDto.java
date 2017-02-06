package com.bitmesra.bitotsav.database.models;

import com.bitmesra.bitotsav.database.models.login.SignUpBody;

import io.realm.RealmObject;

/**
 * Created by Batdroid on 6/2/17 for Bitotsav.
 */

public class UserDetailsDto extends RealmObject {
    private String firstName;
    private String lastName;
    private String bitId;
    private String dOB;
    private String email;
    private String phone;

    public UserDetailsDto() {
    }

    public UserDetailsDto(SignUpBody body) {
        this.firstName = body.getFirstName();
        this.lastName = body.getLastName();
        this.bitId = body.getBitId();
        this.dOB = body.getdOB();
        this.email = body.getEmail();
        this.phone = body.getPhone();
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDetailsDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDetailsDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getBitId() {
        return bitId;
    }

    public UserDetailsDto setBitId(String bitId) {
        this.bitId = bitId;
        return this;
    }

    public String getdOB() {
        return dOB;
    }

    public UserDetailsDto setdOB(String dOB) {
        this.dOB = dOB;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDetailsDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserDetailsDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
