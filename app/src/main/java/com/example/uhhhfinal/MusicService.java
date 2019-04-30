package com.example.uhhhfinal;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
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
        geoffmusic = MediaPlayer.create(this, R.raw.weaselboi);
        if (MainActivity.GlobalVars.globalChallen < 1000L && MainActivity.GlobalVars.globalChallen >= 1) {
            geoffmusic = MediaPlayer.create(this, R.raw.weaselboi);
            geoffmusic.setVolume(0.2f,0.2f);
        }
        if (MainActivity.GlobalVars.globalChallen < 10000L && MainActivity.GlobalVars.globalChallen >= 1000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.monkey_1k);
            geoffmusic.setVolume(0.25f,0.25f);
        }
        if (MainActivity.GlobalVars.globalChallen < 100000L && MainActivity.GlobalVars.globalChallen >= 10000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.overcase_10k);
            geoffmusic.setVolume(0.15f,0.15f);
        }
        if (MainActivity.GlobalVars.globalChallen < 1000000L && MainActivity.GlobalVars.globalChallen >= 100000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.builder_100k);
            geoffmusic.setVolume(0.3f,0.3f);
        }
        if (MainActivity.GlobalVars.globalChallen < 10000000L && MainActivity.GlobalVars.globalChallen >= 1000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.thecannery_1m);
            geoffmusic.setVolume(0.05f,0.05f);
        }
        if (MainActivity.GlobalVars.globalChallen < 100000000L && MainActivity.GlobalVars.globalChallen >= 10000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.thedescent_10m);
            geoffmusic.setVolume(0.15f,0.15f);
        }
        if (MainActivity.GlobalVars.globalChallen < 1000000000L && MainActivity.GlobalVars.globalChallen >= 100000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.rynostheme_100m);
            geoffmusic.setVolume(0.3f,0.3f);
        }
        if (MainActivity.GlobalVars.globalChallen < 10000000000L && MainActivity.GlobalVars.globalChallen >= 1000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.killers_1b);
            geoffmusic.setVolume(0.1f,0.1f);
        }
        if (MainActivity.GlobalVars.globalChallen < 100000000000L && MainActivity.GlobalVars.globalChallen >= 10000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.universal_10b);
            geoffmusic.setVolume(0.3f,0.3f);
        }
        if (MainActivity.GlobalVars.globalChallen < 1000000000000L && MainActivity.GlobalVars.globalChallen >= 100000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.infiniteperspective_100b);
            geoffmusic.setVolume(0.3f,0.3f);
        }
        if (MainActivity.GlobalVars.globalChallen < 10000000000000L && MainActivity.GlobalVars.globalChallen >= 1000000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.blackvortex_1t);
            geoffmusic.setVolume(0.3f,0.3f);
        }
        if (MainActivity.GlobalVars.globalChallen < 100000000000000L && MainActivity.GlobalVars.globalChallen >= 10000000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.koolkats_10t);
            geoffmusic.setVolume(0.3f,0.3f);
        }
        if (MainActivity.GlobalVars.globalChallen < 1000000000000000L && MainActivity.GlobalVars.globalChallen >= 100000000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.mustbego_100t);
            geoffmusic.setVolume(0.3f,0.3f);
        }
        if (MainActivity.GlobalVars.globalChallen < 10000000000000000L && MainActivity.GlobalVars.globalChallen >= 1000000000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.notasitseems_1q);
            geoffmusic.setVolume(0.3f,0.3f);
        }
        if (MainActivity.GlobalVars.globalChallen < 100000000000000000L && MainActivity.GlobalVars.globalChallen >= 10000000000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.serpentine_10q);
            geoffmusic.setVolume(0.3f,0.3f);
        }
        if (MainActivity.GlobalVars.globalChallen < 1000000000000000000L && MainActivity.GlobalVars.globalChallen >= 100000000000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.happygame_1qq);
            geoffmusic.setVolume(0.3f,0.3f);
        }
        if (MainActivity.GlobalVars.globalChallen >= 1000000000000000000L) {
            geoffmusic = MediaPlayer.create(this, R.raw.mountainking_1s);
            geoffmusic.setVolume(0.3f,0.3f);
        }
        geoffmusic.start();
        geoffmusic.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                MusicService.this.stopSelf();
            }
        });
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        geoffmusic.stop();
    }
}