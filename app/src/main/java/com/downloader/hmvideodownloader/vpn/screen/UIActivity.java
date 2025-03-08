package com.downloader.hmvideodownloader.vpn.screen;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.downloader.hmvideodownloader.R;
import com.downloader.hmvideodownloader.databinding.ActivityNewUiBinding;
import com.downloader.hmvideodownloader.vpn.utils.Converter;
import com.downloader.hmvideodownloader.vpn.utils.Preference;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.navigation.NavigationView;

import java.util.Locale;
import java.util.Map;

import unified.vpn.sdk.Callback;
import unified.vpn.sdk.RemainingTraffic;
import unified.vpn.sdk.UnifiedSdk;
import unified.vpn.sdk.VpnException;
import unified.vpn.sdk.VpnState;


public abstract class UIActivity extends AppCompatActivity implements View.OnClickListener {

    protected static final String TAG = MainActivity.class.getSimpleName();

    private static InterstitialAd mInterstitialAd;

    Toolbar toolbar;
    Preference preference;
    boolean mSubscribedToDelaroy = false;
    boolean connected = false;
    String mDelaroySku = "";
    boolean mAutoRenewEnabled = false;

    DrawerLayout navDrawer;
    int clickPosition;
    ImageView btnshare;

    private Handler mUIHandler = new Handler(Looper.getMainLooper());

    final Runnable mUIUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            updateUI();
            checkRemainingTraffic();
            mUIHandler.postDelayed(mUIUpdateRunnable, 10000);
        }
    };


    protected abstract void isLoggedIn(Callback<Boolean> callback);

    protected abstract void loginToVpn();

    protected abstract void isConnected(Callback<Boolean> callback);

    protected abstract void connectToVpn();

    protected abstract void disconnectFromVnp();

    protected abstract void chooseServer();

    protected abstract void getCurrentServer(Callback<String> callback);

    protected abstract void checkRemainingTraffic();

    void complain(String message) {
        alert("Error: " + message);
    }

    void alert(String message) {
        android.app.AlertDialog.Builder bld = new android.app.AlertDialog.Builder(this);
        bld.setMessage(message);
        bld.setNeutralButton("OK", null);
        bld.create().show();
    }


    ActivityNewUiBinding binding;

    ImageView img_connect;
    TextView connectionStateTextView;


    TextView selectedServerTextView;

    ImageView country_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewUiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginToVpn();
        img_connect = findViewById(R.id.img_connect);
        connectionStateTextView = findViewById(R.id.connection_state);
        selectedServerTextView = findViewById(R.id.selected_server);
        country_flag = findViewById(R.id.country_flag);

        btnshare = findViewById(R.id.share_us_tv);
        btnshare.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                // If the navigation drawer is not open then open it, if its already open then close it.
                if (!navDrawer.isDrawerOpen(GravityCompat.START))
                    navDrawer.openDrawer(Gravity.START);
                else navDrawer.closeDrawer(Gravity.END);
            }
        });

        navDrawer = findViewById(R.id.activity_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(UIActivity.this, navDrawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        navDrawer.addDrawerListener(toggle);
        toggle.syncState();

        preference = new Preference(this);

        binding.premium.setVisibility(View.GONE);


        binding.imgConnect.setOnClickListener(v -> {
            onConnectBtnClick();
        });

        binding.optimalServerBtn.setOnClickListener(v -> {
            onServerChooserClick();
        });


    }


    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


    @Override
    protected void onResume() {
        super.onResume();
        isConnected(new Callback<Boolean>() {
            @Override
            public void success(@NonNull Boolean aBoolean) {
                if (aBoolean) {
                    startUIUpdateTask();
                }
            }

            @Override
            public void failure(@NonNull VpnException e) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopUIUpdateTask();
    }


    public void onConnectBtnClick() {
        isConnected(new Callback<Boolean>() {
            @Override
            public void success(@NonNull Boolean aBoolean) {
                if (aBoolean) {
                    disconnectFromVnp();
                } else {
                    connectToVpn();
                }
            }

            @Override
            public void failure(@NonNull VpnException e) {
            }
        });
    }


    public void onServerChooserClick() {
        chooseServer();
    }


    protected void startUIUpdateTask() {
        stopUIUpdateTask();
        mUIHandler.post(mUIUpdateRunnable);
    }

    protected void stopUIUpdateTask() {
        mUIHandler.removeCallbacks(mUIUpdateRunnable);
        updateUI();
    }


    protected void updateUI() {
        UnifiedSdk.getVpnState(new Callback<VpnState>() {
            @Override
            public void success(@NonNull VpnState vpnState) {
                switch (vpnState) {
                    case IDLE: {

                        binding.connectionState.setText("Disconnected");
                        binding.imgConnect.setVisibility(View.VISIBLE);
                        if (connected) {
                            connected = false;
                        }

                        binding.imgConnect.setImageResource(R.drawable.disconnectedbtn);
                        binding.countryFlag.setImageDrawable(getResources().getDrawable(R.drawable.ic_earth));
                        binding.selectedServer.setText(R.string.select_country);
                        ChangeBlockVisibility();
                        binding.uploadingSpeed.setText("0.0 Kb");
                        binding.downloadingSpeed.setText("0.0 Kb");
                        binding.animationView.pauseAnimation();
                        binding.animationView.setVisibility(View.INVISIBLE);


                        hideConnectProgress();
                        break;
                    }
                    case CONNECTED: {
                        binding.imgConnect.setVisibility(View.VISIBLE);
                        binding.imgConnect.setImageResource(R.drawable.connected_btn);
                        if (!connected) {
                            connected = true;
                        }
                        binding.animationView.pauseAnimation();
                        binding.animationView.setVisibility(View.INVISIBLE);

                        binding.connectionState.setText("Connected");
                        hideConnectProgress();
                        break;
                    }
                    case CONNECTING_VPN:
                    case CONNECTING_CREDENTIALS:
                    case CONNECTING_PERMISSIONS: {
                        binding.connectionState.setText("Connecting");
                        ChangeBlockVisibility();
                        binding.animationView.playAnimation();
                        binding.animationView.setVisibility(View.VISIBLE);
                        binding.imgConnect.setVisibility(View.INVISIBLE);
                        binding.countryFlag.setImageDrawable(getResources().getDrawable(R.drawable.ic_earth));
                        binding.selectedServer.setText(R.string.select_country);
                        showConnectProgress();
                        break;
                    }
                    case PAUSED: {
                        ChangeBlockVisibility();
                        binding.countryFlag.setImageDrawable(getResources().getDrawable(R.drawable.ic_earth));
                        binding.selectedServer.setText(R.string.select_country);
                        break;
                    }
                }
            }

            @Override
            public void failure(@NonNull VpnException e) {
                binding.countryFlag.setImageDrawable(getResources().getDrawable(R.drawable.ic_earth));
                binding.selectedServer.setText(R.string.select_country);
            }
        });
        getCurrentServer(new Callback<String>() {
            @Override
            public void success(@NonNull final String currentServer) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.countryFlag.setImageDrawable(getResources().getDrawable(R.drawable.ic_earth));
                        binding.selectedServer.setText(R.string.select_country);
                        if (!currentServer.equals("")) {
                            Locale locale = new Locale("", currentServer);
                            Resources resources = getResources();
                            String sb = "drawable/" + currentServer.toLowerCase();
                            binding.countryFlag.setImageResource(resources.getIdentifier(sb, null, getPackageName()));
                            binding.selectedServer.setText(locale.getDisplayCountry());
                        } else {
                            binding.countryFlag.setImageDrawable(getResources().getDrawable(R.drawable.ic_earth));
                            binding.selectedServer.setText(R.string.select_country);
                        }
                    }
                });
            }

            @Override
            public void failure(@NonNull VpnException e) {
                binding.countryFlag.setImageDrawable(getResources().getDrawable(R.drawable.ic_earth));
                binding.selectedServer.setText(R.string.select_country);
            }
        });
    }

    private void ChangeBlockVisibility() {
        binding.premium.setVisibility(View.GONE);
    }

    private void animate(final ImageView imageView, final int images[], final int imageIndex, final boolean forever) {


        int fadeInDuration = 500;
        int timeBetween = 3000;
        int fadeOutDuration = 1000;

        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(images[imageIndex]);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(fadeInDuration);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(fadeInDuration + timeBetween);
        fadeOut.setDuration(fadeOutDuration);

        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(fadeIn);

        animation.setRepeatCount(1);
        imageView.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                if (images.length - 1 > imageIndex) {
                    animate(imageView, images, imageIndex + 1, forever); //Calls itself until it gets to the end of the array
                } else {
                    if (forever) {
                        animate(imageView, images, 0, forever);  //Calls itself to start the animation all over again in a loop if forever = true
                    }
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
    }


    protected void updateTrafficStats(long outBytes, long inBytes) {
        String outString = Converter.humanReadableByteCountOld(outBytes, false);
        String inString = Converter.humanReadableByteCountOld(inBytes, false);

        binding.uploadingSpeed.setText(inString);
        binding.downloadingSpeed.setText(outString);

    }

    protected void updateRemainingTraffic(RemainingTraffic remainingTrafficResponse) {
        if (remainingTrafficResponse.isUnlimited()) {

        } else {
            String trafficUsed = Converter.megabyteCount(remainingTrafficResponse.getTrafficUsed()) + "Mb";
            String trafficLimit = Converter.megabyteCount(remainingTrafficResponse.getTrafficLimit()) + "Mb";

        }
    }

    //protected void ShowIPaddera(String ipaddress) {
    //    server_ip.setText(ipaddress);
    //  }


    protected void showConnectProgress() {

    }

    protected void hideConnectProgress() {

    }

    protected void showMessage(String msg) {
        Toast.makeText(UIActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


/*
    public void LoadBannerAd() {
        RelativeLayout adContainer = findViewById(R.id.adView);
        if (BuildConfig.GOOGlE_AD) {
            AdMod.buildAdBanner(getApplicationContext(), adContainer, 0, new AdMod.MyAdListener() {
                @Override
                public void onAdClicked() {
                }

                @Override
                public void onAdClosed() {
                }

                @Override
                public void onAdLoaded() {
                }

                @Override
                public void onAdOpened() {
                }

                @Override
                public void onFaildToLoad(int i) {
                }
            });
        }
    }
*/


    @Override
    public void onClick(View view) {}


}