package com.downloader.hmvideodownloader.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.LifecycleObserver;

import com.downloader.hmvideodownloader.screens.SplashActivityNayaDownloader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import java.util.Date;

public class OpenAdManagerSplashNayaDownloader implements LifecycleObserver, Application.ActivityLifecycleCallbacks {

    private static final String LOG_TAG = "AppOpenManager";
    public static boolean isLoaded = false;
    public static AppOpenAd appOpenAd = null;
    public static AppOpenAd.AppOpenAdLoadCallback loadCallback;
    public static long loadTime = 0;
    private final Application myApplication;
    private Activity currentActivity;

    public OpenAdManagerSplashNayaDownloader(Application myApplication) {
        this.myApplication = myApplication;
        this.myApplication.registerActivityLifecycleCallbacks(this);
    }
    public static void fetchAd(Activity activity, AdsManagerNayaDownloader.AdFinished adFinished) {
        Log.d("OpenOpen", "show: ");

        if (isLoaded){
            appOpenAd.show(activity);
            appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    isLoaded = false;
                    fetchAd(activity);
                    adFinished.onAdFinished();
                }
            });
        } else {
            fetchAd(activity);
            adFinished.onAdFinished();
        }
    }


    public static void fetchAd(Activity activity) {
        loadCallback =
                new AppOpenAd.AppOpenAdLoadCallback() {

                    @Override
                    public void onAdLoaded(AppOpenAd ad) {
                        Log.d("OpenOpen", "onAdLoaded: ");
                        appOpenAd = ad;
                        isLoaded = true;
                        loadTime = (new Date()).getTime();
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        // Handle the error.
                        appOpenAd = null;
                        isLoaded = false;
                        Log.d("OpenOpen", "loadAdError: "+loadAdError.toString());
                    }

                };
        if (SplashActivityNayaDownloader.isConnectedToInternet(activity)
                && !new PrefManagerVideoNayaDownloader(activity).getString(SplashActivityNayaDownloader.TAG_OPENAPPID).contains("sandeep")) {
            AdRequest request = getAdRequest();
            AppOpenAd.load(
                    activity, new PrefManagerVideoNayaDownloader(activity).getString(SplashActivityNayaDownloader.TAG_OPENAPPID), request,
                    AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
        }

    }

    public static AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    /**
     * Utility method to check if ad was loaded more than n hours ago.
     */
    public static boolean wasLoadTimeLessThanNHoursAgo(long numHours) {
        long dateDifference = (new Date()).getTime() - loadTime;
        long numMilliSecondsPerHour = 3600000;
        return (dateDifference < (numMilliSecondsPerHour * numHours));
    }

    /**
     * Utility method that checks if ad exists and can be shown.
     */
    public static boolean isAdAvailable() {
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4);
    }

    /**
     * ActivityLifecycleCallback methods
     */
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        currentActivity = null;
    }

}

