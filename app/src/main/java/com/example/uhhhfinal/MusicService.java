package com.example.uhhhfinal;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;
import android.support.annotation.Nullable;

public class MusicService extends Service {
    private MediaPlayer geoffmusic;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        geoffmusic = MediaPlayer.create(this, R.raw.track13_theverve);
        geoffmusic.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        geoffmusic.stop();
    }
}