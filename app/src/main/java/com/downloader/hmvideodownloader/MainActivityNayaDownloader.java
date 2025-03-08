package com.downloader.hmvideodownloader;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.downloader.hmvideodownloader.screens.FifthActivityNayaDownloader;
import com.downloader.hmvideodownloader.screens.FirstActivityNayaDownloader;
import com.downloader.hmvideodownloader.screens.FourthActivityNayaDownloader;
import com.downloader.hmvideodownloader.screens.SecondActivityNayaDownloader;
import com.downloader.hmvideodownloader.screens.SplashActivityNayaDownloader;
import com.downloader.hmvideodownloader.screens.ThirdActivityNayaDownloader;
import com.downloader.hmvideodownloader.utils.AdsManagerNayaDownloader;
import com.downloader.hmvideodownloader.utils.PermissionsNayaDownloader;
import com.downloader.hmvideodownloader.utils.PrefManagerVideoNayaDownloader;
import com.downloader.hmvideodownloader.utils.VideosAdapterNayaDownloader;
import com.downloader.hmvideodownloader.utils.VideosAdapterOnline;
import com.downloader.hmvideodownloader.vpn.screen.MainActivity;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivityNayaDownloader extends AppCompatActivity {

    VideosAdapterNayaDownloader videosAdapterNayaDownloader;
    RecyclerView recyclerView;
    ArrayList<Integer> arrayList;
    private FirebaseAnalytics mFirebaseAnalytics;

    VideosAdapterOnline videosAdapterOnline;
    ArrayList<String> arrayListOnline;
    RecyclerView recyclerViewOnline;
    PrefManagerVideoNayaDownloader pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = new PrefManagerVideoNayaDownloader(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

//        if (!PermissionsNayaDownloader.isAllStoragePermissionsGranted(this)) {
//            PermissionsNayaDownloader.requestAllStoragePermissions(this, 101);
//        }

        arrayListOnline = new ArrayList<>();
        recyclerViewOnline = findViewById(R.id.videosRecyclerViewOnline);

        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_1));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_2));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_3));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_4));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_5));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_6));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_8));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_7));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_9));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_10));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_11));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_12));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_13));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_14));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_15));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_16));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_17));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_18));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_19));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_20));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_21));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_22));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_23));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_24));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_25));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_26));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_27));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_28));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_29));
        arrayListOnline.add(pref.getString(SplashActivityNayaDownloader.video_30));
        recyclerView = findViewById(R.id.videosRecyclerView);

        arrayList = new ArrayList<>();

        arrayList.add(R.raw.video_1);
        arrayList.add(R.raw.video_2);
//        arrayList.add(R.raw.video_3);
//        arrayList.add(R.raw.video_4);
//        arrayList.add(R.raw.video_6);
//        arrayList.add(R.raw.video_7);
//        arrayList.add(R.raw.video_8);
//        arrayList.add(R.raw.video_5);
//        arrayList.add(R.raw.video_11);
//        arrayList.add(R.raw.video_12);
//        arrayList.add(R.raw.video_13);
//        arrayList.add(R.raw.video_14);
//        arrayList.add(R.raw.video_20);
//        arrayList.add(R.raw.video_16);
//        arrayList.add(R.raw.video_17);
//        arrayList.add(R.raw.video_18);
//        arrayList.add(R.raw.video_19);
//        arrayList.add(R.raw.video_15);
//        arrayList.add(R.raw.video_21);
//        arrayList.add(R.raw.video_22);
//        arrayList.add(R.raw.video_23);
//        arrayList.add(R.raw.video_24);
//        arrayList.add(R.raw.video_10);
//        arrayList.add(R.raw.video_9);

        Collections.shuffle(arrayList);
        Collections.shuffle(arrayListOnline);

        videosAdapterNayaDownloader = new VideosAdapterNayaDownloader(this, arrayList);
        recyclerView.setAdapter(videosAdapterNayaDownloader);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivityNayaDownloader.this, 2));

        recyclerViewOnline.setLayoutManager(new GridLayoutManager(MainActivityNayaDownloader.this, 2));
        videosAdapterOnline = new VideosAdapterOnline(this, arrayListOnline);
        recyclerViewOnline.setAdapter(videosAdapterOnline);

    }


    @Override
    public void onBackPressed() {
        Intent intent;
        if (new PrefManagerVideoNayaDownloader(this).getString(SplashActivityNayaDownloader.status_dummy_five_back_enabled).contains("true")) {
            intent = new Intent(this, FifthActivityNayaDownloader.class);
            AdsManagerNayaDownloader.showInterstitialAd(this, new AdsManagerNayaDownloader.AdFinished() {
                @Override
                public void onAdFinished() {
                    startActivity(intent);
                }
            });
        } else if (new PrefManagerVideoNayaDownloader(this).getString(SplashActivityNayaDownloader.status_dummy_four_back_enabled).contains("true")) {
            intent = new Intent(this, FourthActivityNayaDownloader.class);
            AdsManagerNayaDownloader.showInterstitialAd(this, new AdsManagerNayaDownloader.AdFinished() {
                @Override
                public void onAdFinished() {
                    startActivity(intent);
                }
            });
        } else if (new PrefManagerVideoNayaDownloader(this).getString(SplashActivityNayaDownloader.status_dummy_three_back_enabled).contains("true")) {
            intent = new Intent(this, ThirdActivityNayaDownloader.class);
            AdsManagerNayaDownloader.showInterstitialAd(this, new AdsManagerNayaDownloader.AdFinished() {
                @Override
                public void onAdFinished() {
                    startActivity(intent);
                }
            });
        } else if (new PrefManagerVideoNayaDownloader(this).getString(SplashActivityNayaDownloader.status_dummy_two_back_enabled).contains("true")) {
            intent = new Intent(this, SecondActivityNayaDownloader.class);
            AdsManagerNayaDownloader.showInterstitialAd(this, new AdsManagerNayaDownloader.AdFinished() {
                @Override
                public void onAdFinished() {
                    startActivity(intent);
                }
            });
        } else if (new PrefManagerVideoNayaDownloader(this).getString(SplashActivityNayaDownloader.status_dummy_one_back_enabled).contains("true")) {
            intent = new Intent(this, FirstActivityNayaDownloader.class);
            AdsManagerNayaDownloader.showInterstitialAd(this, new AdsManagerNayaDownloader.AdFinished() {
                @Override
                public void onAdFinished() {
                    startActivity(intent);
                }
            });
        } else {
            finishAffinity();
        }

    }

}