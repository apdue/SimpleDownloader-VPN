package com.downloader.hmvideodownloader.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.downloader.hmvideodownloader.MainActivityNayaDownloader;
import com.downloader.hmvideodownloader.R;
import com.downloader.hmvideodownloader.utils.AdsManager;
import com.downloader.hmvideodownloader.utils.PrefManagerVideo;
import com.downloader.hmvideodownloader.vpn.screen.MainActivity;

public class FifthActivityNayaDownloader extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_five);

        if (new PrefManagerVideo(this).getString(SplashActivityNayaDownloader.dummy_four_screen).contains("ad")) {
            AdsManager.showAndLoadNativeAd(this, findViewById(R.id.nativeAd), 1);
        }

        AdsManager.showAndLoadNativeAd(this, findViewById(R.id.nativeLayoutSmaller), 0);

        if (new PrefManagerVideo(this).getString(SplashActivityNayaDownloader.enable_vpn_screen).contains("true")){
            findViewById(R.id.btnVPN).setVisibility(View.VISIBLE);
        }

        if (new PrefManagerVideo(this).getString(SplashActivityNayaDownloader.enable_downloader_screen).contains("true")){
            findViewById(R.id.btnNext).setVisibility(View.VISIBLE);
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
        if (new PrefManagerVideo(this).getString(SplashActivityNayaDownloader.status_dummy_four_back_enabled).contains("true")) {
            intent = new Intent(this, FourthActivityNayaDownloader.class);
            AdsManager.showInterstitialAd(this, new AdsManager.AdFinished() {
                @Override
                public void onAdFinished() {
                    startActivity(intent);
                }
            });
        } else if (new PrefManagerVideo(this).getString(SplashActivityNayaDownloader.status_dummy_three_back_enabled).contains("true")) {
            intent = new Intent(this, ThirdActivityNayaDownloader.class);

            AdsManager.showInterstitialAd(this, new AdsManager.AdFinished() {
                @Override
                public void onAdFinished() {
                    startActivity(intent);
                }
            });
        } else if (new PrefManagerVideo(this).getString(SplashActivityNayaDownloader.status_dummy_two_back_enabled).contains("true")) {
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

    public void ClickPP(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://sites.google.com/view/vpnapppolicy/"));
        startActivity(intent);
    }
}