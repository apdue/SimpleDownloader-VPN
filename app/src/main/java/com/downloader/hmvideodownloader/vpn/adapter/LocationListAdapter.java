package com.downloader.hmvideodownloader.vpn.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.downloader.hmvideodownloader.R;
import com.downloader.hmvideodownloader.vpn.utils.CountryData;
import com.downloader.hmvideodownloader.vpn.utils.Preference;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import unified.vpn.sdk.Location;

public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.ViewHolder> {

    public static class Region {
        final String name;

        public Region(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public Context context;
    private Preference preference;
    private List<CountryData> regions;
    private RegionListAdapterInterface listAdapterInterface;

    public LocationListAdapter(RegionListAdapterInterface listAdapterInterface, Activity cntec) {
        this.listAdapterInterface = listAdapterInterface;
        this.context = cntec;
        preference = new Preference(this.context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.server_list_free, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final CountryData datanew = this.regions.get(holder.getAdapterPosition());
        final Region data = datanew.getCountryvalue();
        Locale locale = new Locale("", data.getName());

        if (position == 0) {
            holder.flag.setImageResource(context.getResources().getIdentifier("drawable/ic_earth", null, context.getPackageName()));
            holder.app_name.setText(R.string.best_performance_server);
            holder.limit.setVisibility(View.GONE);
        } else {
            ImageView imageView = holder.flag;
            Resources resources = context.getResources();
            String sb = "drawable/" + data.getName().toLowerCase();
            imageView.setImageResource(resources.getIdentifier(sb, null, context.getPackageName()));
            holder.app_name.setText(locale.getDisplayCountry());
            holder.limit.setVisibility(View.VISIBLE);
        }
        if (datanew.isPro()) {
            holder.pro.setVisibility(View.VISIBLE);
        } else {
            holder.pro.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAdapterInterface.onCountrySelected(regions.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return regions != null ? regions.size() : 0;
    }

    public void setRegions(List<Location> list) {
        regions = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CountryData newData = new CountryData();
            newData.setCountryvalue(new Region(list.get(i).getName()));
            regions.add(newData);
            Log.d("TAGCOUNTRIES", ""+newData.getCountryvalue().getName());
        }
        notifyDataSetChanged();
    }

    public interface RegionListAdapterInterface {
        void onCountrySelected(CountryData item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView app_name;
        ImageView flag, pro;
        ImageView limit;

        ViewHolder(View v) {
            super(v);
            this.app_name = itemView.findViewById(R.id.region_title);
            this.limit = itemView.findViewById(R.id.region_limit);
            this.flag = itemView.findViewById(R.id.country_flag);
            this.pro = itemView.findViewById(R.id.pro);
        }
    }
}
