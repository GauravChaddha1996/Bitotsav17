package com.bitmesra.bitotsav.database.models.login;

/**
 * Created by Batdroid on 6/2/17 for Bitotsav.
 */

public class SignUpBody {
    private String firstName;
    private String lastName;
    private String bitId;
    private String dOB;
    private String email;
    private String phone;

    public SignUpBody() {
    }

    public SignUpBody(String firstName, String lastName, String bitId, String dOB, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bitId = bitId;
        this.dOB = dOB;
        this.email = email;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public SignUpBody setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SignUpBody setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getBitId() {
        return bitId;
    }

    public SignUpBody setBitId(String bitId) {
        this.bitId = bitId;
        return this;
    }

    public String getdOB() {
        return dOB;
    }

    public SignUpBody setdOB(String dOB) {
        this.dOB = dOB;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SignUpBody setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public SignUpBody setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
