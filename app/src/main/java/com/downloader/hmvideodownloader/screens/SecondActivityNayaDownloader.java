package com.downloader.hmvideodownloader.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import com.downloader.hmvideodownloader.MainActivityNayaDownloader;
import com.downloader.hmvideodownloader.R;
import com.downloader.hmvideodownloader.utils.AdsManagerNayaDownloader;
import com.downloader.hmvideodownloader.utils.PrefManagerVideoNayaDownloader;

public class SecondActivityNayaDownloader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_two);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rlCustomAd);

        if (new PrefManagerVideoNayaDownloader(this).getString(SplashActivityNayaDownloader.dummy_two_screen).contains("ad")) {
            rl.setVisibility(View.GONE);
            AdsManagerNayaDownloader.showAndLoadNativeAd(this, findViewById(R.id.nativeAd), 1);
        }else {
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = new PrefManagerVideoNayaDownloader(SecondActivityNayaDownloader.this).getString(SplashActivityNayaDownloader.webview_url);
                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                    CustomTabsIntent customTabsIntent = builder.build();
                    customTabsIntent.launchUrl(SecondActivityNayaDownloader.this, Uri.parse(url));
                }
            });
        }

        AdsManagerNayaDownloader.showAndLoadNativeAd(this, findViewById(R.id.nativeLayoutSmaller), 0);

        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity();
            }
        });

    }

    private void startActivity() {
        Intent intent;
        if (new PrefManagerVideoNayaDownloader(SecondActivityNayaDownloader.this).getString(SplashActivityNayaDownloader.status_dummy_three_enabled).contains("true")) {
            intent = new Intent(SecondActivityNayaDownloader.this, ThirdActivityNayaDownloader.class);
        } else if (new PrefManagerVideoNayaDownloader(SecondActivityNayaDownloader.this).getString(SplashActivityNayaDownloader.status_dummy_four_enabled).contains("true")) {
            intent = new Intent(SecondActivityNayaDownloader.this, FourthActivityNayaDownloader.class);
        } else if (new PrefManagerVideoNayaDownloader(this).getString(SplashActivityNayaDownloader.status_dummy_five_enabled).contains("true")) {
            intent = new Intent(this, FifthActivityNayaDownloader.class);
        } else {
            intent = new Intent(SecondActivityNayaDownloader.this, MainActivityNayaDownloader.class);
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
        if (new PrefManagerVideoNayaDownloader(this).getString(SplashActivityNayaDownloader.status_dummy_one_back_enabled).contains("true")) {
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