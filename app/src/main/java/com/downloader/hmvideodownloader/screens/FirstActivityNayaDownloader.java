package com.downloader.hmvideodownloader.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import com.downloader.hmvideodownloader.MainActivityNayaDownloader;
import com.downloader.hmvideodownloader.R;
import com.downloader.hmvideodownloader.utils.AdsManager;
import com.downloader.hmvideodownloader.utils.PrefManagerVideo;

public class FirstActivityNayaDownloader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_one);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rlCustomAd);

        if (new PrefManagerVideo(this).getString(SplashActivityNayaDownloader.dummy_one_screen).contains("ad")){
            rl.setVisibility(View.GONE);

            AdsManager.showAndLoadNativeAd(this, findViewById(R.id.nativeAd), 1);
        } else {
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = new PrefManagerVideo(FirstActivityNayaDownloader.this).getString(SplashActivityNayaDownloader.webview_url);
                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                    CustomTabsIntent customTabsIntent = builder.build();
                    customTabsIntent.launchUrl(FirstActivityNayaDownloader.this, Uri.parse(url));
                }
            });
        }
        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity();
            }
        });

    }

    private void startActivity(){
        Intent intent;
        if (new PrefManagerVideo(FirstActivityNayaDownloader.this).getString(SplashActivityNayaDownloader.status_dummy_two_enabled).contains("true")) {
            intent = new Intent(FirstActivityNayaDownloader.this, SecondActivityNayaDownloader.class);
        } else if (new PrefManagerVideo(FirstActivityNayaDownloader.this).getString(SplashActivityNayaDownloader.status_dummy_three_enabled).contains("true")) {
            intent = new Intent(FirstActivityNayaDownloader.this, ThirdActivityNayaDownloader.class);
        } else if (new PrefManagerVideo(FirstActivityNayaDownloader.this).getString(SplashActivityNayaDownloader.status_dummy_four_enabled).contains("true")) {
            intent = new Intent(FirstActivityNayaDownloader.this, FourthActivityNayaDownloader.class);
        }  else if (new PrefManagerVideo(this).getString(SplashActivityNayaDownloader.status_dummy_five_enabled).contains("true")) {
             intent = new Intent(this, FifthActivityNayaDownloader.class);
         } else {
            intent = new Intent(FirstActivityNayaDownloader.this, MainActivityNayaDownloader.class);
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
        finishAffinity();
    }
}