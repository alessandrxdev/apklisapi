package com.arr.apklislib.update;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.arr.apklislib.update.callback.UpdateCallback;
import com.arr.apklislib.update.model.ApiResponse;
import com.arr.apklislib.update.model.LastRelease;
import com.arr.apklislib.update.model.UpdateInfo;
import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class ApklisUpdate {

    private static final String API_URL = "https://api.apklis.cu/v1/application/?package_name=";
    private Context context;
    private OkHttpClient httpClient;
    private Gson gson;
    private Handler handler;

    public ApklisUpdate(Context context) {
        this.context = context;
        httpClient = new OkHttpClient();
        gson = new Gson();
        handler = new Handler(Looper.getMainLooper());
    }

    public void checkLastUpdate(String package_name, final UpdateCallback callback) {
        Request request = new Request.Builder().url(API_URL + package_name).build();
        httpClient
                .newCall(request)
                .enqueue(
                        new Callback() {
                            @Override
                            public void onResponse(Call call, Response response)
                                    throws IOException {
                                if (response.isSuccessful()) {
                                    String result = response.body().string();
                                    Log.e("Result: ", "" + result.toString());

                                    //
                                    ApiResponse api = gson.fromJson(result, ApiResponse.class);
                                    List<UpdateInfo> info = api.appUpdateInfo();

                                    if (info != null && !info.isEmpty()) {
                                        UpdateInfo updateInfo = info.get(0);
                                        LastRelease release = updateInfo.lastRelease();

                                        // compribar las version code
                                        int serverVersionCode = release.versionCode();
                                        int appVersionCode = getCurrentVersionCode();
                                        if (serverVersionCode > appVersionCode) {
                                            handler.post(() -> callback.onLastUpdate(release));
                                        }
                                    }

                                } else {
                                    handler.post(
                                            () ->
                                                    callback.onError(
                                                            new Exception(
                                                                    "Error occurred during network request")));
                                }
                            }

                            @Override
                            public void onFailure(Call call, IOException e) {
                                callback.onError(e);
                            }
                        });
    }

    private int getCurrentVersionCode() {
        // Obtener el versionCode actual de la aplicación
        try {
            return context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0)
                    .versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
