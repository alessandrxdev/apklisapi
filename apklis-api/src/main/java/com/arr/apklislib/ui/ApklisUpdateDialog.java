package com.arr.apklislib.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.arr.apklislib.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ApklisUpdateDialog extends BottomSheetDialog {

    private Context mContext;
    private TextView nameApp, version, changelog;

    public ApklisUpdateDialog(@NonNull Context context) {
        super(context);

        // Inflate view
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_view_dialog_update, null);
        setContentView(view);

        nameApp = view.findViewById(R.id.title);
        version = view.findViewById(R.id.text_new_version);
        changelog = view.findViewById(R.id.changelog);
    }

    public ApklisUpdateDialog setChangelog(String string) {
        if (string != null) {
            changelog.setText(string);
        }
        return this;
    }

    public ApklisUpdateDialog setVersion(String string) {
        if (string != null) {
            version.setText(string);
        }
        return this;
    }

    public ApklisUpdateDialog setTitle(String string) {
        if (string != null) {
            nameApp.setText(string);
        }
        return this;
    }
}
