package com.arr.apklislib.update.callback;

import com.arr.apklislib.update.model.LastRelease;

public interface UpdateCallback {

    void onLastUpdate(LastRelease info);

    void onError(Exception e);
}
