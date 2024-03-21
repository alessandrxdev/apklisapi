package com.arr.apklislib.payments;

import android.content.ContentProviderClient;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;

public class ApklisPay {

    private String APKLIS_URL = "content://cu.uci.android.apklis.payment.provider/app/";
    private String APK_PAID = "paid";
    private String USER = "user_name";

    private Context mContext;
    private String packageName;

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
                        isPaid = cursor.getInt(cursor.getColumnIndex(APK_PAID)) > 0;
                        usuario = cursor.getString(cursor.getColumnIndex(USER));
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
            e.printStackTrace();
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
