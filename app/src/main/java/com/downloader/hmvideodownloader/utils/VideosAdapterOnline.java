package com.downloader.hmvideodownloader.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.downloader.hmvideodownloader.R;
import com.downloader.hmvideodownloader.VideoPlayerActivityNayaDownloader;
import com.downloader.hmvideodownloader.screens.SplashActivityNayaDownloader;

import java.util.ArrayList;

public class VideosAdapterOnline extends RecyclerView.Adapter<VideosAdapterOnline.ViewHolder> {

    ArrayList<String> videos;
    Activity activity;

    public VideosAdapterOnline(Activity activity, ArrayList<String> videos) {
        this.videos = videos;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(activity)
                .load(videos.get(position)).addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                        holder.rlProgress.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                        holder.rlProgress.setVisibility(View.GONE);
                        return false;
                    }
                })
                .thumbnail(0.1f)
                .into(holder.ivThumbnail);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdsManagerNayaDownloader.showInterstitialAd(activity, new AdsManagerNayaDownloader.AdFinished() {
                    @Override
                    public void onAdFinished() {
                        Intent intent = new Intent(activity, VideoPlayerActivityNayaDownloader.class);
                        intent.putExtra("video", videos.get(position));
                        intent.putExtra("isOnline", true);
                        activity.startActivity(intent);
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        if (new PrefManagerVideoNayaDownloader(activity).getInt(SplashActivityNayaDownloader.number_of_videos)>videos.size()){
            return videos.size();
        } else {
            return new PrefManagerVideoNayaDownloader(activity).getInt(SplashActivityNayaDownloader.number_of_videos);
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rlProgress;
        ImageView ivThumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rlProgress = itemView.findViewById(R.id.rlProgress);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
        }
    }


}
