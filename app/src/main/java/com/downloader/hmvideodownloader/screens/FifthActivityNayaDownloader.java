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
import com.downloader.hmvideodownloader.vpn.screen.MainActivity;

public class FifthActivityNayaDownloader extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_five);

        if (new PrefManagerVideoNayaDownloader(this).getString(SplashActivityNayaDownloader.dummy_four_screen).contains("ad")) {
            AdsManagerNayaDownloader.showAndLoadNativeAd(this, findViewById(R.id.nativeAd), 1);
        }

        AdsManagerNayaDownloader.showAndLoadNativeAd(this, findViewById(R.id.nativeLayoutSmaller), 0);

        if (new PrefManagerVideoNayaDownloader(this).getString(SplashActivityNayaDownloader.enable_vpn_screen).contains("true")){
            findViewById(R.id.btnVPN).setVisibility(View.VISIBLE);
        }

        findViewById(R.id.btnVPN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FifthActivityNayaDownloader.this, MainActivity.class));
            }
        });

    }

    public void CLICK(View view) {
        startActivity();
    }

    private void startActivity() {
        Intent intent;
        intent = new Intent(this, MainActivityNayaDownloader.class);
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
        if (new PrefManagerVideoNayaDownloader(this).getString(SplashActivityNayaDownloader.status_dummy_four_back_enabled).contains("true")) {
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