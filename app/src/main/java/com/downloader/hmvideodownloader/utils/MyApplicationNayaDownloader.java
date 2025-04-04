package com.downloader.hmvideodownloader.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.downloader.hmvideodownloader.BuildConfig;
import com.downloader.hmvideodownloader.R;
import com.google.android.material.color.DynamicColors;

import java.util.ArrayList;
import java.util.List;

import unified.vpn.sdk.ClientInfo;
import unified.vpn.sdk.CompletableCallback;
import unified.vpn.sdk.HydraTransportConfig;
import unified.vpn.sdk.OpenVpnTransportConfig;
import unified.vpn.sdk.SdkNotificationConfig;
import unified.vpn.sdk.TransportConfig;
import unified.vpn.sdk.UnifiedSdk;
import unified.vpn.sdk.UnifiedSdkConfig;

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
    UnifiedSdk unifiedSDK;

    @Override
    public void onCreate() {
        super.onCreate();

        DynamicColors.applyToActivitiesIfAvailable(this);

        applicationContext = this;

        initHydraSdk();
    }

    public void initHydraSdk() {

        if (AdsManager.id != ""){
            createNotificationChannel();
            SharedPreferences prefs = getPrefs();
            ClientInfo clientInfo = ClientInfo.newBuilder()
                    .addUrl(prefs.getString(BuildConfig.STORED_HOST_URL_KEY, BuildConfig.BASE_HOST))
                    .carrierId(prefs.getString(BuildConfig.STORED_CARRIER_ID_KEY, AdsManager.id)
                    ).build();
            List<TransportConfig> transportConfigList = new ArrayList<>();
            transportConfigList.add(HydraTransportConfig.create());
            transportConfigList.add(OpenVpnTransportConfig.tcp());
            transportConfigList.add(OpenVpnTransportConfig.udp());
            UnifiedSdk.update(transportConfigList, CompletableCallback.EMPTY);
            UnifiedSdkConfig config = UnifiedSdkConfig.newBuilder().build();
            unifiedSDK = UnifiedSdk.getInstance(clientInfo, config);
            SdkNotificationConfig notificationConfig = SdkNotificationConfig.newBuilder()
                    .title(getResources().getString(R.string.app_name))
                    .channelId(getPackageName())
                    .build();
            UnifiedSdk.update(notificationConfig);
            UnifiedSdk.setLoggingLevel(Log.VERBOSE);
        }

    }

    public void setNewHostAndCarrier(String hostUrl, String carrierId) {
        SharedPreferences prefs = getPrefs();
        if (TextUtils.isEmpty(hostUrl)) {
            prefs.edit().remove(BuildConfig.STORED_HOST_URL_KEY).apply();
        } else {
            prefs.edit().putString(BuildConfig.STORED_HOST_URL_KEY, hostUrl).apply();
        }
        if (TextUtils.isEmpty(carrierId)) {
            prefs.edit().remove(BuildConfig.STORED_CARRIER_ID_KEY).apply();
        } else {
            prefs.edit().putString(BuildConfig.STORED_CARRIER_ID_KEY, carrierId).apply();
        }
        initHydraSdk();
    }

    public SharedPreferences getPrefs() {
        return getSharedPreferences(BuildConfig.SHARED_PREFS, Context.MODE_PRIVATE);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getResources().getString(R.string.app_name) + "";
            String description = getResources().getString(R.string.app_name) + "" + getString(R.string.notify);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getPackageName(), name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
