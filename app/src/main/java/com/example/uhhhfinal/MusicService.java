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
        geoffmusic = MediaPlayer.create(this, R.raw.figleaf_1);
        if (MainActivity.GlobalVars.globalChallen < 1000L && MainActivity.GlobalVars.globalChallen >= 1) {
            geoffmusic = MediaPlayer.create(this, R.raw.figleaf_1);
        }
        if (MainActivity.GlobalVars.globalChallen < 10000L && MainActivity.GlobalVars.globalChallen >= 1000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.monkey_1k);
        }
        if (MainActivity.GlobalVars.globalChallen < 100000L && MainActivity.GlobalVars.globalChallen >= 10000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.overcase_10k);
        }
        if (MainActivity.GlobalVars.globalChallen < 1000000L && MainActivity.GlobalVars.globalChallen >= 100000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.builder_100k);
        }
        if (MainActivity.GlobalVars.globalChallen < 10000000L && MainActivity.GlobalVars.globalChallen >= 1000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.thecannery_1m);
        }
        if (MainActivity.GlobalVars.globalChallen < 100000000L && MainActivity.GlobalVars.globalChallen >= 10000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.thedescent_10m);
        }
        if (MainActivity.GlobalVars.globalChallen < 1000000000L && MainActivity.GlobalVars.globalChallen >= 100000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.rynostheme_100m);
        }
        if (MainActivity.GlobalVars.globalChallen < 10000000000L && MainActivity.GlobalVars.globalChallen >= 1000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.killers_1b);
        }
        if (MainActivity.GlobalVars.globalChallen < 100000000000L && MainActivity.GlobalVars.globalChallen >= 10000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.universal_10b);
        }
        if (MainActivity.GlobalVars.globalChallen < 1000000000000L && MainActivity.GlobalVars.globalChallen >= 100000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.infiniteperspective_100b);
        }
        if (MainActivity.GlobalVars.globalChallen < 10000000000000L && MainActivity.GlobalVars.globalChallen >= 1000000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.blackvortex_1t);
        }
        if (MainActivity.GlobalVars.globalChallen < 100000000000000L && MainActivity.GlobalVars.globalChallen >= 10000000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.koolkats_10t);
        }
        if (MainActivity.GlobalVars.globalChallen < 1000000000000000L && MainActivity.GlobalVars.globalChallen >= 100000000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.mustbego_100t);
        }
        if (MainActivity.GlobalVars.globalChallen < 10000000000000000L && MainActivity.GlobalVars.globalChallen >= 1000000000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.notasitseems_1q);
        }
        if (MainActivity.GlobalVars.globalChallen < 100000000000000000L && MainActivity.GlobalVars.globalChallen >= 10000000000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.serpentine_10q);
        }
        if (MainActivity.GlobalVars.globalChallen < 1000000000000000000L && MainActivity.GlobalVars.globalChallen >= 100000000000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.happygame_1qq);
        }
        if (MainActivity.GlobalVars.globalChallen >= 1000000000000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.mountainking_1s);
        }
        geoffmusic.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        geoffmusic.stop();
    }
}