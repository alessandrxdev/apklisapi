package com.arr.example;

import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.arr.apklislib.payments.ApklisPay;
import com.arr.apklislib.ui.ApklisUpdateDialog;
import com.arr.apklislib.update.ApklisUpdate;
import com.arr.apklislib.update.callback.UpdateCallback;
import com.arr.apklislib.update.model.LastRelease;
import com.arr.example.databinding.ActivityMainBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate and get instance of binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        // set content view to binding's root
        setContentView(binding.getRoot());

        ApklisUpdate api = new ApklisUpdate(this);
        api.checkLastUpdate(
                this.getPackageName(),
                new UpdateCallback() {
                    @Override
                    public void onLastUpdate(LastRelease info) {
                        Spanned changelog = formatHtmlString(info.appChangelog());
                        new ApklisUpdateDialog(MainActivity.this)
                                .setTitle("Nueva Versión")
                                .setVersion(info.versionName())
                                .setChangelog(changelog.toString())
                                .show();
                    }

                    @Override
                    public void onError(Exception e) {}
                });

        
        // comprobar si se ha pagado la apk
        ApklisPay paid = new ApklisPay(this, this.getPackageName());
        if (paid.isPaid()) {
            Toast.makeText(this, "App pagada", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "App No pagada", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    private Spanned formatHtmlString(String htmlString) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(htmlString, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(htmlString);
        }
    }
}
