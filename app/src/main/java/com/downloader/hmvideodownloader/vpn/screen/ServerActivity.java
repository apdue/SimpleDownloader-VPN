package com.downloader.hmvideodownloader.vpn.screen;


import static com.downloader.hmvideodownloader.vpn.utils.BillConfig.BUNDLE;
import static com.downloader.hmvideodownloader.vpn.utils.BillConfig.COUNTRY_DATA;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.downloader.hmvideodownloader.R;
import com.downloader.hmvideodownloader.vpn.adapter.LocationListAdapter;
import com.downloader.hmvideodownloader.vpn.utils.CountryData;
import com.downloader.hmvideodownloader.vpn.utils.Preference;
import com.google.gson.Gson;

import unified.vpn.sdk.AvailableLocations;
import unified.vpn.sdk.Callback;
import unified.vpn.sdk.ConnectionType;
import unified.vpn.sdk.UnifiedSdk;
import unified.vpn.sdk.VpnException;

public class ServerActivity extends AppCompatActivity {


    RecyclerView regionsRecyclerView;


    ProgressBar regionsProgressBar;

    private LocationListAdapter regionAdapter;
    private RegionChooserInterface regionChooserInterface;
    ImageView backToActivity;

    TextView activity_name;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        regionsProgressBar = findViewById(R.id.regions_progress);
        regionsRecyclerView = findViewById(R.id.regions_recycler_view);

        Preference preference = new Preference(this);

        activity_name = findViewById(R.id.activity_names);
        backToActivity = findViewById(R.id.back);
        activity_name.setText("Servers");
        backToActivity.setOnClickListener(view -> finish());
        regionChooserInterface = item -> {
                Intent intent = new Intent();
                Bundle args = new Bundle();
                Gson gson = new Gson();
                String json = gson.toJson(item);

                args.putString(COUNTRY_DATA, json);
                intent.putExtra(BUNDLE, args);
                setResult(RESULT_OK, intent);
                finish();
              /*  AdsUtility.showInterAds(ServerActivity.this, new AdsUtility.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        finish();
                    }
                });*/
        };

        regionsRecyclerView.setHasFixedSize(true);
        regionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        regionAdapter = new LocationListAdapter(item -> regionChooserInterface.onRegionSelected(item), ServerActivity.this);
        regionsRecyclerView.setAdapter(regionAdapter);
        loadServers();

    }

    private void loadServers() {
        showProgress();

        UnifiedSdk.getInstance().getBackend().locations(ConnectionType.HYDRA_TCP, new Callback<AvailableLocations>() {
            @Override
            public void success(@NonNull final AvailableLocations countries) {
                hideProress();

                regionAdapter.setRegions(countries.getLocations());
            }

            @Override
            public void failure(VpnException e) {
                hideProress();
            }
        });


    }

    private void showProgress() {
        regionsProgressBar.setVisibility(View.VISIBLE);
        regionsRecyclerView.setVisibility(View.INVISIBLE);
    }

    private void hideProress() {
        regionsProgressBar.setVisibility(View.GONE);
        regionsRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public interface RegionChooserInterface {
        void onRegionSelected(CountryData item);
    }
}
