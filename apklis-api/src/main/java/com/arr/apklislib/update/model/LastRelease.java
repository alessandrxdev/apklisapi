package com.arr.apklislib.update.model;

import com.google.gson.annotations.SerializedName;

public class LastRelease {

    @SerializedName("version_code")
    private int versionCode;

    @SerializedName("version_name")
    private String versionName;

    @SerializedName("published")
    private String datePublished;

    @SerializedName("changelog")
    private String changelog;

    @SerializedName("size")
    private int size;

    @SerializedName("icon")
    private String icon;

    public int versionCode() {
        return versionCode;
    }

    public String versionName() {
        return versionName;
    }

    public String datePublished() {
        return datePublished;
    }

    public String appChangelog() {
        return changelog;
    }

    public int appSize() {
        return size;
    }

    public String appIcon() {
        return icon;
    }
}
