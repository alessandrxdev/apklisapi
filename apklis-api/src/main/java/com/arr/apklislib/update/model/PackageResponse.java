package com.arr.apklislib.update.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class PackageResponse {
    @SerializedName("count")
    private long count;
    @SerializedName("results")
    private List<Result> results;

    public long getCount() { return count; }

    public List<Result> getResults() { return results; }
}
