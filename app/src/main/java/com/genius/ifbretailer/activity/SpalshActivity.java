package com.genius.ifbretailer.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.genius.ifbretailer.R;
import com.genius.ifbretailer.utility.CreativePermission;
import com.genius.ifbretailer.utility.PrefManager;

public class SpalshActivity extends AppCompatActivity {
    private static final int PERMISSION_ALL = 100;
    private CreativePermission myPermission;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        CheckPermission();
    }

    private void showSplash() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (prefManager.getRemberFlag().equals("1")){
                    startActivity(new Intent(SpalshActivity.this, SurveyActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(SpalshActivity.this, LoginActivity.class));
                    finish();
                }

            }
        }, 2000);

    }


    private void CheckPermission() {
        if (!myPermission.hasPermissions()) {
            myPermission.reqPermisions();
        } else {
            setup();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ALL) {
            setup();

        } else {

        }
    }

    private void initialize() {
        myPermission = new CreativePermission(this, PERMISSION_ALL);
        prefManager=new PrefManager(SpalshActivity.this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                showSplash();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    private void setup() {

        showSplash();

    }
}
