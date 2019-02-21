package com.nl.bt_service_broadcast;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

public class MusicService extends Service {
    public MusicService() {
        Log.i("MusicService","AAAAAAAAAAAAAAAAAAAAAAAAAAa");
    }

    MediaPlayer mediaPlayer;
    BroadcastReceiver broadcastReceiver= new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            switch (action){
                case "com.nl.bt_service_broadcast.ACTION_PLAY":
                    Log.i("BroadcastReceiver","da nhan duoc tin hieu");

                    try {
                        String songPath=intent.getStringExtra("song_path");
                        Log.i("MusicService",songPath);
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(songPath);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                    break;
            }
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Music Service","onCreate");
        mediaPlayer=new MediaPlayer();
        IntentFilter intentFilter =new IntentFilter();
        intentFilter.addAction("com.nl.bt_service_broadcast.ACTION_PLAY");
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
