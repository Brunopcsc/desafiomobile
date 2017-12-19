package com.example.bruno.desafio.entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppsDetailEntity {
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("site")
    @Expose
    private String site;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getSite() {
        return site;
    }
}
