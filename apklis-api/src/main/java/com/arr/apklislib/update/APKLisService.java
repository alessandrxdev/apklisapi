package com.arr.apklislib.update;

import com.arr.apklislib.update.model.PackageResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APKLisService {
    @Headers({"User-Agent: ApplifyCU/1.0.0", "Content-Type: application/json"})
    @GET("/application/")
    public Single<PackageResponse> getPackageResponse(@Query("package_name") String packageName);
}
