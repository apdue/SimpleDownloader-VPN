package com.downloader.hmvideodownloader.utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import unified.vpn.sdk.CompletableCallback;
import unified.vpn.sdk.TrackingConstants;
import unified.vpn.sdk.UnifiedSdk;
import unified.vpn.sdk.VpnException;


public class ClearService extends Service {

    public static Context context;

    private static PrefManagerVideoNayaDownloader prf;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        context = this;

        prf = new PrefManagerVideoNayaDownloader(context);

        Log.d("ClearService", "Service Started");
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ClearService", "Service Destroyed");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.e("ClearService", "END");
        //Code here
        stopVPN();
    }

    private void stopVPN() {
        Log.e("ClearService", "stopVPN");

        try {
            UnifiedSdk.getInstance().getVpn().stop(TrackingConstants.GprReasons.M_UI, new CompletableCallback() {
                @Override
                public void complete() {
                    Log.d("TAGVPN", "stopVPN complete ");
                    stopSelf();

                }

                @Override
                public void error(@NonNull VpnException e) {
                    Log.d("TAGVPN", "stopVPN error E: " + e);
                    stopSelf();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TAGVPN", "stopVPN Catch E: " + e);
            stopSelf();

        }
    }
}
