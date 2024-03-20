package com.arr.apklislib.update.model;
import com.google.gson.annotations.SerializedName;

public class UpdateInfo {
    
    @SerializedName("last_release")
    private LastRelease lastRelease;
    
    
    public LastRelease lastRelease(){
        return lastRelease;
    }
}
