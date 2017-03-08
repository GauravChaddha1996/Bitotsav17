package com.bitmesra.bitotsav.database.models.nights;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Batdroid on 8/3/17 for Bitotsav.
 */

public class NightsModel extends RealmObject {
    @PrimaryKey
    int id;
    String name;
    String links;
    @SerializedName("image1")
    String image1;
    @SerializedName("description")
    String desc;

    public NightsModel() {
    }

    public NightsModel(int id, String name, String links, String image1, String desc) {
        this.id = id;
        this.name = name;
        this.links = links;
        this.image1 = image1;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
