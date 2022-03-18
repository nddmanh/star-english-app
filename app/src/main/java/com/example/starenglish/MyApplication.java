package com.example.starenglish;

import android.app.Application;

import com.example.starenglish.data.DataLocalManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
    }
}
