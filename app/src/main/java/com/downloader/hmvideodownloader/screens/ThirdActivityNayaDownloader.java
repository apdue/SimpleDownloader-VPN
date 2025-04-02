package com.downloader.hmvideodownloader.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.downloader.hmvideodownloader.MainActivityNayaDownloader;
import com.downloader.hmvideodownloader.R;
import com.downloader.hmvideodownloader.utils.AdsManager;
import com.downloader.hmvideodownloader.utils.PrefManagerVideo;

public class ThirdActivityNayaDownloader extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_three);

        if (new PrefManagerVideo(this).getString(SplashActivityNayaDownloader.dummy_three_screen).contains("ad")) {
            AdsManager.showAndLoadNativeAd(this, findViewById(R.id.nativeAd), 1);
        }

        AdsManager.showAndLoadNativeAd(this, findViewById(R.id.nativeLayoutSmaller), 0);

        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity();
            }
        });
    }

    private void startActivity() {
        Intent intent;
        if (new PrefManagerVideo(ThirdActivityNayaDownloader.this).getString(SplashActivityNayaDownloader.status_dummy_four_enabled).contains("true")) {
            intent = new Intent(ThirdActivityNayaDownloader.this, FourthActivityNayaDownloader.class);
        } else if (new PrefManagerVideo(this).getString(SplashActivityNayaDownloader.status_dummy_five_enabled).contains("true")) {
            intent = new Intent(this, FifthActivityNayaDownloader.class);
        } else {
            intent = new Intent(ThirdActivityNayaDownloader.this, MainActivityNayaDownloader.class);
        }
        AdsManager.showInterstitialAd(this, new AdsManager.AdFinished() {

            @Override
            public void onAdFinished() {
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent;
        if (new PrefManagerVideo(this).getString(SplashActivityNayaDownloader.status_dummy_two_back_enabled).contains("true")) {
            intent = new Intent(this, SecondActivityNayaDownloader.class);
            AdsManager.showInterstitialAd(this, new AdsManager.AdFinished() {
                @Override
                public void onAdFinished() {
                    startActivity(intent);
                }
            });
        } else if (new PrefManagerVideo(this).getString(SplashActivityNayaDownloader.status_dummy_one_back_enabled).contains("true")) {
            intent = new Intent(this, FirstActivityNayaDownloader.class);
            AdsManager.showInterstitialAd(this, new AdsManager.AdFinished() {
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