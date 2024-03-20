package com.arr.apklislib;

import android.content.ContentProviderClient;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.RemoteException;

import android.database.Cursor;
import android.content.Context;
import android.content.ContentProviderClient;
import android.net.Uri;
import android.os.Build;
import android.os.RemoteException;

public class ApklisUtils {

    private static final String APKLIS_PROVIDER =
            "content://cu.uci.android.apklis.payment.provider/app/";
    private static final String APKLIS_PAID = "paid";
    private static final String APKLIS_USER_NAME = "user_name";

    public static PurchaseInfo isPurchased(Context context, String packageId) {
        boolean paid = false;
        String userName = null;
        Uri providerURI = Uri.parse(APKLIS_PROVIDER + packageId);

        ContentProviderClient contentResolver = null; // Inicializa el ContentProviderClient a null

        try {
            contentResolver =
                    context.getContentResolver().acquireContentProviderClient(providerURI);

            if (contentResolver != null) { // Verifica si contentResolver no es null
                Cursor cursor = contentResolver.query(providerURI, null, null, null, null);

                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        paid = cursor.getInt(cursor.getColumnIndex(APKLIS_PAID)) > 0;
                        userName = cursor.getString(cursor.getColumnIndex(APKLIS_USER_NAME));
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        contentResolver.close();
                    } else {
                        contentResolver.release();
                    }
                    cursor.close();
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            if (contentResolver != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                contentResolver.release();
            }
        }

        return new PurchaseInfo(paid, userName);
    }

    public static class PurchaseInfo {
        private boolean paid;
        private String userName;

        public PurchaseInfo(boolean paid, String userName) {
            this.paid = paid;
            this.userName = userName;
        }

        public boolean isPaid() {
            return paid;
        }

        public String getUserName() {
            return userName;
        }
    }
}
