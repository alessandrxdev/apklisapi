package com.arr.apklislib.update.model;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("last_release")
    private LastRelease lastRelease;

    public LastRelease getLastRelease() { return lastRelease; }
}
