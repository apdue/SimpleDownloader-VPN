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
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivityNayaDownloader extends AppCompatActivity {

    VideosAdapterNayaDownloader videosAdapterNayaDownloader;
    RecyclerView recyclerView;
    ArrayList<Integer> arrayList;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

//        if (!PermissionsNayaDownloader.isAllStoragePermissionsGranted(this)) {
//            PermissionsNayaDownloader.requestAllStoragePermissions(this, 101);
//        }

        recyclerView = findViewById(R.id.videosRecyclerView);

        arrayList = new ArrayList<>();

        arrayList.add(R.raw.video_1);
        arrayList.add(R.raw.video_2);
        arrayList.add(R.raw.video_3);
        arrayList.add(R.raw.video_4);
        arrayList.add(R.raw.video_10);
        arrayList.add(R.raw.video_6);
        arrayList.add(R.raw.video_7);
        arrayList.add(R.raw.video_8);
        arrayList.add(R.raw.video_9);
        arrayList.add(R.raw.video_5);
        arrayList.add(R.raw.video_11);
        arrayList.add(R.raw.video_12);
        arrayList.add(R.raw.video_13);
        arrayList.add(R.raw.video_14);
        arrayList.add(R.raw.video_20);
        arrayList.add(R.raw.video_16);
        arrayList.add(R.raw.video_17);
        arrayList.add(R.raw.video_18);
        arrayList.add(R.raw.video_19);
        arrayList.add(R.raw.video_15);
        arrayList.add(R.raw.video_21);
        arrayList.add(R.raw.video_22);
        arrayList.add(R.raw.video_23);
        arrayList.add(R.raw.video_24);

        Collections.shuffle(arrayList);

        videosAdapterNayaDownloader = new VideosAdapterNayaDownloader(this, arrayList);
        recyclerView.setAdapter(videosAdapterNayaDownloader);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivityNayaDownloader.this, 2));

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