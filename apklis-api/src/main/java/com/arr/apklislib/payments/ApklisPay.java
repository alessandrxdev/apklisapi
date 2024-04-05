package com.arr.apklislib.payments;

import android.content.ContentProviderClient;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

public class ApklisPay {
    private static final String TAG = "ApklisPay";
    private static final String APKLIS_URL = "content://cu.uci.android.apklis.payment.provider/app/";
    private static final String APK_PAID = "paid";
    private static final String USER = "user_name";

    private final Context mContext;
    private final String packageName;

    private boolean isPaid = false;
    private String usuario = null;

    public ApklisPay(Context context, String packageName) {
        this.mContext = context;
        this.packageName = packageName;
        checkPayment();
    }

    private void checkPayment() {
        Uri provider = Uri.parse(APKLIS_URL + packageName);

        ContentProviderClient content = null;
        try {
            content = mContext.getContentResolver().acquireContentProviderClient(provider);
            if (content != null) {
                Cursor cursor = content.query(provider, null, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        int paidColumnIndex = cursor.getColumnIndex(APK_PAID);
                        int userColumnIndex = cursor.getColumnIndex(USER);
                        isPaid = cursor.getInt(paidColumnIndex) > 0;
                        usuario = cursor.getString(userColumnIndex);
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        content.close();
                    } else {
                        content.release();
                    }
                    cursor.close();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "checkPayment: Fail", e);
            ;
        } finally {
            if (content != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                content.release();
            }
        }
    }

    public boolean isPaid() {
        return isPaid;
    }

    public String userName() {
        return usuario;
    }
}
