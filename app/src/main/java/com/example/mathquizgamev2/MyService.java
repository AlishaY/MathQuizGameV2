package com.example.mathquizgamev2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private MediaPlayer mMediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.fairy);
        mMediaPlayer.setLooping(false);
    }

    public void onStart(Intent intent, int startid) {
        mMediaPlayer.start();
    }

    public void onDestroy(){
        mMediaPlayer.stop();
    }

}
