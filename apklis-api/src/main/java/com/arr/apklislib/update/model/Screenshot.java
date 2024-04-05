package com.arr.apklislib.update.model;

import com.google.gson.annotations.SerializedName;

public class Screenshot {
    @SerializedName("description")
    private String description;
    @SerializedName("id")
    private long id;
    @SerializedName("img")
    private String img;

    public String getDescription() { return description; }

    public long getID() { return id; }

    public String getImg() { return img; }
}
