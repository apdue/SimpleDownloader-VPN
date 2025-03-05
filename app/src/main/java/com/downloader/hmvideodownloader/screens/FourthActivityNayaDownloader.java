package com.downloader.hmvideodownloader.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.downloader.hmvideodownloader.MainActivityNayaDownloader;
import com.downloader.hmvideodownloader.R;
import com.downloader.hmvideodownloader.utils.AdsManagerNayaDownloader;
import com.downloader.hmvideodownloader.utils.PrefManagerVideoNayaDownloader;

public class FourthActivityNayaDownloader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_four);

        if (new PrefManagerVideoNayaDownloader(this).getString(SplashActivityNayaDownloader.dummy_four_screen).contains("ad")) {
            AdsManagerNayaDownloader.showAndLoadNativeAd(this, findViewById(R.id.nativeAd), 1);
        }

        AdsManagerNayaDownloader.showAndLoadNativeAd(this, findViewById(R.id.nativeLayoutSmaller), 0);

    }

    public void CLICK(View view) {
        startActivity();
    }

    private void startActivity() {
        Intent intent;

        if (new PrefManagerVideoNayaDownloader(this).getString(SplashActivityNayaDownloader.status_dummy_five_enabled).contains("true")) {
            intent = new Intent(this, FifthActivityNayaDownloader.class);
        } else {
            intent = new Intent(this, MainActivityNayaDownloader.class);
        }

        AdsManagerNayaDownloader.showInterstitialAd(this, new AdsManagerNayaDownloader.AdFinished() {

            @Override
            public void onAdFinished() {
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent;
        if (new PrefManagerVideoNayaDownloader(this).getString(SplashActivityNayaDownloader.status_dummy_three_back_enabled).contains("true")) {
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