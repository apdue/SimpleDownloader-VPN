package com.downloader.hmvideodownloader.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.google.android.material.color.DynamicColors;

@SuppressLint("Registered")
public class MyApplicationNayaDownloader extends Application {
    public static MyApplicationNayaDownloader applicationContext;

    public static MyApplicationNayaDownloader getAppContext() {
        return applicationContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        DynamicColors.applyToActivitiesIfAvailable(this);

        applicationContext = this;
    }

}
