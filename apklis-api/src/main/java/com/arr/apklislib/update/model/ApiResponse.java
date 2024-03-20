package com.arr.apklislib.update.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ApiResponse {

    @SerializedName("results")
    private List<UpdateInfo> updateInfo;

    public List<UpdateInfo> appUpdateInfo() {
        return updateInfo;
    }
}
