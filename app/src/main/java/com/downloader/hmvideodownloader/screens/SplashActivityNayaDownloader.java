package com.downloader.hmvideodownloader.screens;

import static unified.vpn.sdk.OpenVpnTransport.TRANSPORT_ID_TCP;
import static unified.vpn.sdk.OpenVpnTransport.TRANSPORT_ID_UDP;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.downloader.hmvideodownloader.BuildConfig;
import com.downloader.hmvideodownloader.MainActivityNayaDownloader;
import com.downloader.hmvideodownloader.R;
import com.downloader.hmvideodownloader.utils.AdsManagerNayaDownloader;
import com.downloader.hmvideodownloader.utils.ClearService;
import com.downloader.hmvideodownloader.utils.JsonParserNayaDownloader;
import com.downloader.hmvideodownloader.utils.MyApplicationNayaDownloader;
import com.downloader.hmvideodownloader.utils.OpenAdManagerNayaDownloader;
import com.downloader.hmvideodownloader.utils.OpenAdManagerSplashNayaDownloader;
import com.downloader.hmvideodownloader.utils.PrefManagerVideoNayaDownloader;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import unified.vpn.sdk.AuthMethod;
import unified.vpn.sdk.Callback;
import unified.vpn.sdk.CompletableCallback;
import unified.vpn.sdk.HydraTransport;
import unified.vpn.sdk.SessionConfig;
import unified.vpn.sdk.TrackingConstants;
import unified.vpn.sdk.TrafficRule;
import unified.vpn.sdk.UnifiedSdk;
import unified.vpn.sdk.User;
import unified.vpn.sdk.VpnException;
import unified.vpn.sdk.VpnState;

public class SplashActivityNayaDownloader extends AppCompatActivity {

    private static final String url = "https://guide3.myappadmin.xyz/gb/" + "simplenaya_vpn.php";

    private String selectedCountry = "us";

    public static PrefManagerVideoNayaDownloader prf;

    public static final String BASE_HOST_URL = "BASE_HOST_URL";
    public static final String CARRIER_ID_KEY = "CARRIER_ID_KEY";

    public static final String number_of_videos = "number_of_videos";

    public static final String video_1 = "video_1";
    public static final String video_2 = "video_2";
    public static final String video_3 = "video_3";
    public static final String video_4 = "video_4";
    public static final String video_5 = "video_5";
    public static final String video_6 = "video_6";
    public static final String video_7 = "video_7";
    public static final String video_8 = "video_8";
    public static final String video_9 = "video_9";
    public static final String video_10 = "video_10";
    public static final String video_11 = "video_11";
    public static final String video_12 = "video_12";
    public static final String video_13 = "video_13";
    public static final String video_14 = "video_14";
    public static final String video_15 = "video_15";
    public static final String video_16 = "video_16";
    public static final String video_17 = "video_17";
    public static final String video_18 = "video_18";
    public static final String video_19 = "video_19";
    public static final String video_20 = "video_20";
    public static final String video_21 = "video_21";
    public static final String video_22 = "video_22";
    public static final String video_23 = "video_23";
    public static final String video_24 = "video_24";
    public static final String video_25 = "video_25";
    public static final String video_26 = "video_26";
    public static final String video_27 = "video_27";
    public static final String video_28 = "video_28";
    public static final String video_29 = "video_29";
    public static final String video_30 = "video_30";

    public static final String selectedCountryTag = "selectedCountry";

    public static final String enable_splash_auto_connect = "enable_splash_auto_connect";
    public static final String enable_vpn_screen = "enable_vpn_screen";

    public static final String interstitial_type = "interstitial_type";
    public static final String proxy_username = "proxy_username";
    public static final String proxy_password = "proxy_password";
    public static final String proxy_port = "proxy_port";
    public static final String proxy_host = "proxy_host";
    public static final String webview_url = "webview_url";

    public static final String status_dummy_two_back_enabled = "status_dummy_two_back_enabled";
    Map<String, String> params = new HashMap<>();
    public static final String inter_ad_type = "inter_ad_type";
    public static final String dummy_three_screen = "dummy_three_screen";
    public static final String status_dummy_three_enabled = "status_dummy_three_enabled";
    private String st1, st2, st3, st4, st5, st6;
    private static final String TAG_SUCCESS = "success";
    public static final String status_dummy_three_back_enabled = "status_dummy_three_back_enabled";
    public static final String TAG_APP_ID_AD_UNIT_ID = "app_id_ad_unit_id";
    public AppOpenAd appOpenAd;
    public static final String TAG_NATIVEIDSMALL_fifteen = "nativeid_small";
    public static final String ADMOB_INTERSTITIAL_FREQUENCY = "ADMOB_INTERSTITIAL_FREQUENCY";
    public static final String TAG_NATIVEID = "nativeid";
    public static final String status_dummy_five_back_enabled = "status_dummy_five_back_enabled";
    public static final String TAG_OPENAPPID = "openappid";
    public static final String status_dummy_two_enabled = "status_dummy_two_enabled";
    public static final String dummy_two_screen = "dummy_two_screen";
    public static final String TAG_OPENAPP_ADS_ENABLED = "openapp_ads_enabled";
    public static final String status_dummy_one_enabled_fifteen = "status_dummy_one_enabled";
    public static final String status_dummy_one_back_enabled = "status_dummy_one_back_enabled";
    public static final String status_dummy_five_enabled = "status_dummy_five_enabled";
    public static final String exit_screen = "exit_screen";
    public static final String TAG_INTERSTITIALSPLASH = "interstitialsplash";
    public static final String status_dummy_four_back_enabled = "status_dummy_four_back_enabled";
    public static final String dummy_one_screen = "dummy_one_screen";
    public static final String home_screen = "home_screen";
    private final Executor backgroundExecutor = Executors.newSingleThreadExecutor();
    public static final String TAG_PKG = "pkg";
    public static boolean isFirstStart;
    private static final String TAG = "TAGSPLASHH";
    private final JsonParserNayaDownloader jsonParserNayaDownloader = new JsonParserNayaDownloader();
    public static final String dummy_four_screen = "dummy_four_screen";
    private int rds = 0;
    private static OpenAdManagerNayaDownloader openAdManagerNayaDownloaderFourteenVideo;
    private String sf1, sf2, sf3, sf4, sf5, sf6;
    private String rUrl = "sandeep";
    public static final String TAG_INTERSTITIALMAIN = "interstitialmain";
    private int success = 0;
    public static final String status_dummy_four_enabled = "status_dummy_four_enabled";

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }

    private static String username = "brd-customer-hl_2951b71e-zone-datacenter_proxy1";
    private static String password = "38sy7x3rzt3y";
    private static String proxyHost = "brd.superproxy.io";
    private static int proxyPort = 22225;

//    public static Response response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.activity_splash);
        prf = new PrefManagerVideoNayaDownloader(this);

        String BASE_HOST = prf.getString(BASE_HOST_URL);
        String CARRIER_ID = prf.getString(CARRIER_ID_KEY);

        if (BASE_HOST == null) {
            BASE_HOST = BuildConfig.BASE_HOST;
        }

        if (CARRIER_ID == null) {
            CARRIER_ID = AdsManagerNayaDownloader.id;
        }

        ((MyApplicationNayaDownloader) getApplication()).setNewHostAndCarrier(BASE_HOST, CARRIER_ID);

        Log.d("TAGVER", "version: " + Build.VERSION.SDK_INT);
        AdsManagerNayaDownloader.initializeAdMob(this);
        initialization();

        isFirstStart = prf.getBoolean("firstStart");
        if (isFirstStart) {
            System.out.println("Rajan_isFirstStart" + isFirstStart);
            prf.setBoolean("firstStart", false);
            prf.setString("rUrl", "notset");
        }


        try {
            startService(new Intent(getBaseContext(), ClearService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (prf.getString("rUrl").contains("notset")) {
            checkIns();
        } else {
            rUrl = prf.getString("rUrl");
            new BackgroundSplashTask().execute();
        }

        ((MyApplicationNayaDownloader) getApplication()).initHydraSdk();

    }

    private void initialization() {
        selectedCountry = "us";
        prf.setString("startclicktext", "Start");
        prf.setInt(ADMOB_INTERSTITIAL_FREQUENCY, 1);
        prf.setString(TAG_INTERSTITIALMAIN, "ca-app-pub-3940256099942544/103317371200");
        prf.setString("rd", "0");
        prf.setString(home_screen, "text");
        prf.setString(dummy_two_screen, "ad");

        prf.setString(status_dummy_three_back_enabled, "false");
        prf.setString(exit_screen, "exit_screen");
        prf.setString(status_dummy_three_enabled, "false");
        prf.setString(status_dummy_four_back_enabled, "false");
        prf.setString(TAG_NATIVEID, "c");
        prf.setString(TAG_OPENAPP_ADS_ENABLED, "no");
        prf.setString("skipfirstscreen", "1");
        prf.setString(TAG_NATIVEIDSMALL_fifteen, "ca-app-pub-3940256099942544/2247696110");
        prf.setString(status_dummy_one_back_enabled, "false");
        prf.setString(dummy_three_screen, "ad");
        prf.setString(TAG_APP_ID_AD_UNIT_ID, "ca-app-pub-3940256099942544~3347511713");
        prf.setString(TAG_OPENAPPID, "ca-app-pub-3940256099942544/341983529400");
        prf.setString(status_dummy_five_enabled, "false");
        prf.setString(dummy_four_screen, "ad");

        prf.setString(selectedCountryTag, "");
        prf.setString(enable_vpn_screen, "false");

        prf.setString(BASE_HOST_URL, "https://awebhtpo3u8g5t.ecoweb-network.com");
        prf.setString(CARRIER_ID_KEY, "touchvpn");

        prf.setString(interstitial_type, "admob");
        prf.setString(proxy_username, "admob");
        prf.setString(proxy_password, "admob");
        prf.setInt(proxy_port, 101);
        prf.setString(proxy_host, "admob");
        prf.setString(webview_url, "admob");

        prf.setString(status_dummy_two_back_enabled, "false");
        prf.setString(status_dummy_one_enabled_fifteen, "false");
        prf.setString(status_dummy_five_back_enabled, "false");
        prf.setString(TAG_INTERSTITIALSPLASH, "no");

        prf.setString(status_dummy_two_enabled, "false");
        prf.setString(dummy_one_screen, "ad");
        prf.setString(status_dummy_four_enabled, "false");
        prf.setString(inter_ad_type, "inter");


        prf.setString(video_2, "admob");
        prf.setString(video_3, "admob");
        prf.setString(video_4, "admob");
        prf.setString(video_5, "admob");
        prf.setString(video_6, "admob");
        prf.setString(video_6, "admob");
        prf.setString(video_7, "admob");
        prf.setString(video_8, "admob");
        prf.setString(video_9, "admob");
        prf.setString(video_10, "admob");
        prf.setString(video_11, "admob");
        prf.setString(video_12, "admob");
        prf.setString(video_13, "admob");
        prf.setString(video_14, "admob");
        prf.setString(video_15, "admob");
        prf.setString(video_16, "admob");
        prf.setString(video_17, "admob");
        prf.setString(video_18, "admob");
        prf.setString(video_19, "admob");
        prf.setString(video_20, "admob");
        prf.setString(video_21, "admob");
        prf.setString(video_22, "admob");
        prf.setString(video_23, "admob");
        prf.setString(video_24, "admob");
        prf.setString(video_25, "admob");
        prf.setString(video_26, "admob");
        prf.setString(video_27, "admob");
        prf.setString(video_28, "admob");
        prf.setString(video_29, "admob");
        prf.setString(video_30, "admob");

        prf.setInt(number_of_videos, 0);

    }

    void checkIns() {
        InstallReferrerClient rfClient = InstallReferrerClient.newBuilder(this).build();
        backgroundExecutor.execute(() -> getInsRClient(rfClient));
    }

    private void makeallStr() {
        sf1 = makeStrFormat("or", 3, 8);
        sf2 = makeStrFormat("gc", 10, 13);
        sf3 = makeStrConcat("ut", "_m");
        sf5 = makeStrConcat("med", "mui");
        sf6 = makeStrConcat("google-", "yalp");
        sf4 = makeStrConcat("sou", "ecr");
    }

    private void makeAllStrScnd() {
        st3 = "drsd";
        st2 = "sgcl";
        st4 = "sucp";
        st6 = "rgpy";
        st5 = "mdup";
        st1 = "rornd";
    }

    private String makeStrFormat(String str, int i, int j) {
        String rstring = "nicganicidlid";
        StringBuilder sb = new StringBuilder(rstring);
        return str.concat(sb.substring(i, j));
    }

    private String makeStrConcat(String str, String str2) {
        StringBuilder sb2 = new StringBuilder(str2);
        sb2.reverse();
        return str.concat(sb2.toString());
    }

    void getInsRClient(InstallReferrerClient rfClient) {

        rfClient.startConnection(new InstallReferrerStateListener() {
            @Override
            public void onInstallReferrerSetupFinished(int responseCode) {
                switch (responseCode) {
                    case InstallReferrerClient.InstallReferrerResponse.OK:
                        ReferrerDetails response = null;
                        try {
                            response = rfClient.getInstallReferrer();
                            String rUrltemp = response.getInstallReferrer();

                            makeallStr();
                            makeAllStrScnd();

                            //array to hold replacements
                            String[][] replacements = {{sf1, st1}, {sf2, st2}, {sf3, st3}, {sf4, st4}, {sf5, st5}, {sf6, st6}};

                            //loop over the array and replace
                            String strOutput = rUrltemp;
                            for (String[] replacement : replacements) {
                                strOutput = strOutput.replaceAll(replacement[0], replacement[1]);
                            }

                            rUrl = strOutput;

                            prf.setString("rUrl", rUrl);

                            new BackgroundSplashTask().execute();

                        } catch (RemoteException e) {

                            rUrl = "sandeep_exception_notset";
                            prf.setString("rUrl", rUrl);
                            new BackgroundSplashTask().execute();

                            e.printStackTrace();
                            return;
                        }

                        // End the connection
                        rfClient.endConnection();

                        break;
                    case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:

                        rUrl = "not_supported_notset";
                        prf.setString("rUrl", rUrl);
                        new BackgroundSplashTask().execute();


                        // API not available on the current Play Store app.
                        Log.d("Tag", "FEATURE_NOT_SUPPORTED");
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:

                        rUrl = "unavailable_notset";
                        prf.setString("rUrl", rUrl);
                        new BackgroundSplashTask().execute();
                        // Connection couldn't be established.
                        Log.d("Tag", "SERVICE_UNAVAILABLE");
                        break;
                }
            }

            @Override
            public void onInstallReferrerServiceDisconnected() {

            }
        });
    }

    public SharedPreferences getPrefs() {
        return getSharedPreferences(prf.getString("SHARED_PREFS"), Context.MODE_PRIVATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private String getisdevmode() {
        if (Integer.valueOf(Build.VERSION.SDK_INT).intValue() == 16) {
            return String.valueOf(Settings.Secure.getInt(getContentResolver(), "development_settings_enabled", 0));
        }
        if (Integer.valueOf(Build.VERSION.SDK_INT).intValue() >= 17) {
            return String.valueOf(Settings.Secure.getInt(getContentResolver(), "development_settings_enabled", 0));
        }
        return String.valueOf(0);
    }

    private void startActivity() {

        if (new PrefManagerVideoNayaDownloader(SplashActivityNayaDownloader.this).getString(TAG_OPENAPP_ADS_ENABLED).contains("yes")) {
            openAdManagerNayaDownloaderFourteenVideo = new OpenAdManagerNayaDownloader(MyApplicationNayaDownloader.getAppContext());
        }

        Intent intent;

        if (new PrefManagerVideoNayaDownloader(SplashActivityNayaDownloader.this).getString(SplashActivityNayaDownloader.status_dummy_one_enabled_fifteen).contains("true")) {
            intent = new Intent(SplashActivityNayaDownloader.this, FirstActivityNayaDownloader.class);
        } else if (new PrefManagerVideoNayaDownloader(SplashActivityNayaDownloader.this).getString(SplashActivityNayaDownloader.status_dummy_two_enabled).contains("true")) {
            intent = new Intent(SplashActivityNayaDownloader.this, SecondActivityNayaDownloader.class);
        } else if (new PrefManagerVideoNayaDownloader(SplashActivityNayaDownloader.this).getString(SplashActivityNayaDownloader.status_dummy_three_enabled).contains("true")) {
            intent = new Intent(SplashActivityNayaDownloader.this, ThirdActivityNayaDownloader.class);
        } else if (new PrefManagerVideoNayaDownloader(SplashActivityNayaDownloader.this).getString(SplashActivityNayaDownloader.status_dummy_four_enabled).contains("true")) {
            intent = new Intent(SplashActivityNayaDownloader.this, FourthActivityNayaDownloader.class);
        } else if (new PrefManagerVideoNayaDownloader(this).getString(SplashActivityNayaDownloader.status_dummy_five_enabled).contains("true")) {
            intent = new Intent(this, FifthActivityNayaDownloader.class);
        } else {
            intent = new Intent(SplashActivityNayaDownloader.this, MainActivityNayaDownloader.class);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        if (prf.getString(TAG_INTERSTITIALSPLASH).equalsIgnoreCase("yes")) {
            AppOpenAd.AppOpenAdLoadCallback loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
                @Override
                public void onAdLoaded(AppOpenAd ad) {
                    Log.d("TAGGGGGE", "onAdLoaded:");
                    appOpenAd = ad;
                    appOpenAd.show(SplashActivityNayaDownloader.this);
                    appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            startActivity(intent);
                        }
                    });
                }

                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    Log.d("TAGGGGGE", "OPEN onAdFailedToLoad : " + loadAdError.getMessage());
                    startActivity(intent);
                }
            };
            com.google.android.gms.ads.AdRequest request = new com.google.android.gms.ads.AdRequest.Builder().build();
            AppOpenAd.load(SplashActivityNayaDownloader.this, prf.getString(TAG_OPENAPPID), request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
        } else {
            startActivity(intent);
        }
    }

    private class BackgroundSplashTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute: ");
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            Log.d(TAG, "doInBackground: ");

            params.put(TAG_PKG, getApplicationContext().getPackageName());

            try {
                PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                String version = pInfo.versionName;
                params.put("version", version);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            params.put("isdevmode", getisdevmode());

            params.put("rUrl", prf.getString("rUrl"));

            // getting JSON string from URL
            JSONObject json = jsonParserNayaDownloader.makeHttpRequest(url, "POST", params);

            try {
                success = json.getInt(TAG_SUCCESS);

                if (success == 1) {

                    if (isConnectedToInternet(SplashActivityNayaDownloader.this)) {

                        prf.setString(status_dummy_one_back_enabled, json.getString(status_dummy_one_back_enabled));
                        prf.setString(status_dummy_three_enabled, json.getString(status_dummy_three_enabled));
                        prf.setString(status_dummy_two_back_enabled, json.getString(status_dummy_two_back_enabled));
                        prf.setString(status_dummy_four_enabled, json.getString(status_dummy_four_enabled));
                        prf.setString(status_dummy_four_back_enabled, json.getString(status_dummy_four_back_enabled));
                        prf.setString(status_dummy_five_back_enabled, json.getString(status_dummy_five_back_enabled));
                        prf.setString(status_dummy_one_enabled_fifteen, json.getString(status_dummy_one_enabled_fifteen));
                        prf.setString(status_dummy_three_back_enabled, json.getString(status_dummy_three_back_enabled));
                        prf.setString(status_dummy_five_enabled, json.getString(status_dummy_five_enabled));
                        prf.setString(status_dummy_two_enabled, json.getString(status_dummy_two_enabled));

                    } else {

                        prf.setString(status_dummy_one_back_enabled, "false");
                        prf.setString(status_dummy_three_enabled, "false");
                        prf.setString(status_dummy_two_back_enabled, "false");
                        prf.setString(status_dummy_four_enabled, "false");
                        prf.setString(status_dummy_four_back_enabled, "false");
                        prf.setString(status_dummy_five_back_enabled, "false");
                        prf.setString(status_dummy_one_enabled_fifteen, "false");
                        prf.setString(status_dummy_three_back_enabled, "false");
                        prf.setString(status_dummy_five_enabled, "false");
                        prf.setString(status_dummy_two_enabled, "false");
                    }

                    selectedCountry = json.getString("selectedCountry");
                    prf.setString(TAG_APP_ID_AD_UNIT_ID, json.getString(TAG_APP_ID_AD_UNIT_ID));
                    prf.setString(dummy_two_screen, json.getString(dummy_two_screen));

                    prf.setString(TAG_NATIVEIDSMALL_fifteen, json.getString(TAG_NATIVEIDSMALL_fifteen));
                    prf.setString(exit_screen, json.getString(exit_screen));
                    prf.setString(TAG_INTERSTITIALMAIN, json.getString(TAG_INTERSTITIALMAIN));
                    prf.setString(dummy_one_screen, json.getString(dummy_one_screen));
                    prf.setString(dummy_three_screen, json.getString(dummy_three_screen));
                    prf.setString(TAG_INTERSTITIALSPLASH, json.getString(TAG_INTERSTITIALSPLASH));

                    prf.setString(home_screen, json.getString(home_screen));
                    prf.setString(TAG_OPENAPPID, json.getString(TAG_OPENAPPID));
                    prf.setString("rd", json.getString("rd"));
                    prf.setString(TAG_NATIVEID, json.getString(TAG_NATIVEID));
                    prf.setString(inter_ad_type, json.getString(inter_ad_type));

                    prf.setString(dummy_four_screen, json.getString(dummy_four_screen));

                    prf.setString(enable_splash_auto_connect, json.getString(enable_splash_auto_connect));
                    prf.setString(enable_vpn_screen, json.getString(enable_vpn_screen));
                    prf.setString(selectedCountryTag, json.getString(selectedCountryTag));

                    prf.setString(CARRIER_ID_KEY, json.getString(CARRIER_ID_KEY));
                    prf.setString(BASE_HOST_URL, json.getString(BASE_HOST_URL));

                    prf.setString(interstitial_type, json.getString(interstitial_type));
                    prf.setString(proxy_username, json.getString(proxy_username));
                    prf.setString(proxy_password, json.getString(proxy_password));
                    prf.setInt(proxy_port, json.getInt(proxy_port));
                    prf.setString(proxy_host, json.getString(proxy_host));
                    prf.setString(webview_url, json.getString(webview_url));

                    prf.setString(TAG_OPENAPP_ADS_ENABLED, json.getString(TAG_OPENAPP_ADS_ENABLED));
                    prf.setInt(ADMOB_INTERSTITIAL_FREQUENCY, json.getInt(ADMOB_INTERSTITIAL_FREQUENCY));

                    prf.setString(video_1, json.getString(video_1));
                    prf.setString(video_2, json.getString(video_2));
                    prf.setString(video_3, json.getString(video_3));
                    prf.setString(video_4, json.getString(video_4));
                    prf.setString(video_5, json.getString(video_5));
                    prf.setString(video_6, json.getString(video_6));
                    prf.setString(video_7, json.getString(video_7));
                    prf.setString(video_8, json.getString(video_8));
                    prf.setString(video_9, json.getString(video_9));
                    prf.setString(video_10, json.getString(video_10));
                    prf.setString(video_11, json.getString(video_11));
                    prf.setString(video_12, json.getString(video_12));
                    prf.setString(video_13, json.getString(video_13));
                    prf.setString(video_14, json.getString(video_14));
                    prf.setString(video_15, json.getString(video_15));
                    prf.setString(video_16, json.getString(video_16));
                    prf.setString(video_17, json.getString(video_17));
                    prf.setString(video_18, json.getString(video_18));
                    prf.setString(video_19, json.getString(video_19));
                    prf.setString(video_20, json.getString(video_20));
                    prf.setString(video_22, json.getString(video_22));
                    prf.setString(video_23, json.getString(video_23));
                    prf.setString(video_24, json.getString(video_24));
                    prf.setString(video_25, json.getString(video_25));
                    prf.setString(video_26, json.getString(video_26));
                    prf.setString(video_27, json.getString(video_27));
                    prf.setString(video_28, json.getString(video_28));
                    prf.setString(video_29, json.getString(video_29));
                    prf.setString(video_30, json.getString(video_30));
                    prf.setString(video_21, json.getString(video_21));


                    prf.setInt(number_of_videos, Integer.valueOf(json.getString(number_of_videos)));

                } else {
                    Log.d(TAG, "doInBackground: FAILURE");
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("TAGPHP", "doInBackground: " + e.getLocalizedMessage());
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("TAGPHP", "doInBackground: " + e.getLocalizedMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Log.d(TAG, "onPostExecute: ");

            if (prf.getString(enable_splash_auto_connect).contains("true")) {
                logIntoVPN();
            } else {
                Log.d("TAGVPN", "startActivity: ");
                AdsManagerNayaDownloader.initializeAdMob(SplashActivityNayaDownloader.this);
                OpenAdManagerSplashNayaDownloader.fetchAd(SplashActivityNayaDownloader.this);
                startActivity();
            }
        }
    }

    private void logIntoVPN() {
        Log.d("TAGVPN", "logIntoVPN: ");
        AuthMethod authMethod = AuthMethod.anonymous();
        UnifiedSdk.getInstance().getBackend().login(authMethod, new Callback<User>() {
            @Override
            public void success(@NonNull User user) {
                Log.d("TAGVPN", "logIntoVPN: success");
                connectToVPN();
            }

            @Override
            public void failure(@NonNull VpnException e) {
                Log.d("TAGVPN", "logIntoVPN e : " + e);
                AdsManagerNayaDownloader.initializeAdMob(SplashActivityNayaDownloader.this);
                OpenAdManagerSplashNayaDownloader.fetchAd(SplashActivityNayaDownloader.this);
                startActivity();
            }
        });
    }

    private void connectToVPN() {
        Log.d("TAGVPN", "connectToVPN to country : " + selectedCountry);

        UnifiedSdk.getVpnState(new Callback<VpnState>() {
            @Override
            public void success(VpnState vpnState) {
                if (vpnState == VpnState.CONNECTED) {
                    Log.d("TAGVPN", "VPN is already connected or connecting. Skipping start.");

                    AdsManagerNayaDownloader.initializeAdMob(SplashActivityNayaDownloader.this);
                    OpenAdManagerSplashNayaDownloader.fetchAd(SplashActivityNayaDownloader.this);
                    startActivity();


                    return;
                }
                startVpn();
            }

            @Override
            public void failure(VpnException e) {
                Log.d("TAGVPN", "Failed to get VPN status: " + e);
                startVpn(); // Attempt to start VPN anyway
            }
        });
    }

    private void startVpn() {
        List<String> fallbackOrder = new ArrayList<>();
        fallbackOrder.add(TRANSPORT_ID_TCP);
        fallbackOrder.add(TRANSPORT_ID_UDP);

        List<String> bypassDomains = new LinkedList<>();
        bypassDomains.add("*facebook.com");
        bypassDomains.add("*wtfismyip.com");

        UnifiedSdk.getInstance().getVpn().start(new SessionConfig.Builder()
                .withReason(TrackingConstants.GprReasons.M_UI)
                .withTransportFallback(fallbackOrder)
                .withLocation(selectedCountry)
                .withTransport(HydraTransport.TRANSPORT_ID)
                .addDnsRule(TrafficRule.dns().bypass().fromDomains(bypassDomains))
                .build(), new CompletableCallback() {
            @Override
            public void complete() {
                Log.d("TAGVPN", "VPN Connection Successful");
                AdsManagerNayaDownloader.initializeAdMob(SplashActivityNayaDownloader.this);
                OpenAdManagerSplashNayaDownloader.fetchAd(SplashActivityNayaDownloader.this);
                startActivity();
            }

            @Override
            public void error(@NonNull VpnException e) {
                Log.d("TAGVPN", "VPN Connection Error: " + e);
                AdsManagerNayaDownloader.initializeAdMob(SplashActivityNayaDownloader.this);
                OpenAdManagerSplashNayaDownloader.fetchAd(SplashActivityNayaDownloader.this);
                startActivity();
            }
        });
    }

}