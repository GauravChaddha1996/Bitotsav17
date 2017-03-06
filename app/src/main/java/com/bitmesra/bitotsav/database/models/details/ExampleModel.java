package com.bitmesra.bitotsav.database.models.details;

/**
 * Created by Batdroid on 4/3/17 for Bitotsav.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model being fetched in details activity
 * */
public class ExampleModel {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("club")
    @Expose
    private String club;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("participantsCount")
    @Expose
    private String participantsCount;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("points")
    @Expose
    private String points;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("uniqueId")
    @Expose
    private String uniqueId;
    @SerializedName("_isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("_flagship")
    @Expose
    private Boolean flagship;
    @SerializedName("rules")
    @Expose
    private List<String> rules = null;
    @SerializedName("venue")
    @Expose
    private String venue;
    @SerializedName("money")
    @Expose
    private String money;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getParticipantsCount() {
        return participantsCount;
    }

    public void setParticipantsCount(String participantsCount) {
        this.participantsCount = participantsCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getFlagship() {
        return flagship;
    }

    public void setFlagship(Boolean flagship) {
        this.flagship = flagship;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

