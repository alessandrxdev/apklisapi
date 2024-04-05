package com.arr.apklislib.update.model;

import com.google.gson.annotations.SerializedName;

public class Permission {
    @SerializedName("description")
    private String description;
    @SerializedName("icon")
    private String icon;
    @SerializedName("name")
    private String name;

    public String getDescription() { return description; }

    public String getIcon() { return icon; }

    public String getName() { return name; }
}
