package com.downloader.hmvideodownloader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.downloader.hmvideodownloader.utils.PermissionsNayaDownloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class VideoPlayerActivityNayaDownloader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        findViewById(R.id.ivBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        VideoView videoView = (VideoView) findViewById(R.id.videoView);

        Integer video = getIntent().getIntExtra("video", 0);

        findViewById(R.id.downloadBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (PermissionsNayaDownloader.isAllStoragePermissionsGranted(VideoPlayerActivityNayaDownloader.this)) {
                    copyRawVideoToDownloads(VideoPlayerActivityNayaDownloader.this, video, "Video_" + System.currentTimeMillis() + ".mp4");
//                } else {
//                    PermissionsNayaDownloader.requestAllStoragePermissions(VideoPlayerActivityNayaDownloader.this, 101);
//                }
            }
        });

        String path = "android.resource://" + getPackageName() + "/" + video;

        videoView.setVideoURI(Uri.parse(path));

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.start();
    }

    public void copyRawVideoToDownloads(Context context, int rawId, String fileName) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                return;
            }
        }

        try {
            Log.d("TAGSS", "copyRawVideoToDownloads: trying");
            InputStream inputStream = context.getResources().openRawResource(rawId);
            String downloadsPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            String destinationPath = downloadsPath + File.separator + fileName;
            File file = new File(destinationPath);
            file.getParentFile().mkdirs(); // Create parent directories if needed

            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }

            inputStream.close();
            outputStream.flush();
            outputStream.close();

            Toast.makeText(context, "Video Saved In : DOWNLOADS", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("TAGSS", "copyRawVideoToDownloads: eeee : "+e.getMessage());
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted! Try again.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied! Cannot save video.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            Integer video = getIntent().getIntExtra("video", 0);

            findViewById(R.id.downloadBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (PermissionsNayaDownloader.isAllStoragePermissionsGranted(VideoPlayerActivityNayaDownloader.this)) {
                        copyRawVideoToDownloads(VideoPlayerActivityNayaDownloader.this, video, "Video_" + System.currentTimeMillis() + ".mp4");
                    } else {
                        PermissionsNayaDownloader.requestAllStoragePermissions(VideoPlayerActivityNayaDownloader.this, 101);
                    }
                }
            });

        }
    }
}