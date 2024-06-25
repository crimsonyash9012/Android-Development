package com.example.musicplayerapp_backservices;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

public class MyCustomService extends Service {

    private MediaPlayer player;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // for playing default ringtone audio in the device
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);

        // play audio on loop
        player.setLooping(true);
        player.start();

        // indicates service should be restarted if it's terminated by the system after it's start
        // service will be recreated with a null intent
        return START_STICKY;

//        return START_NOT_STICKY; - service will not be restarted after termination
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }

    // used in context of bound services
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
        // indicates service doesn't support binding
    }
}
