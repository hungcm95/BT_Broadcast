package com.nl.bt_service_broadcast;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="MainActivity";
    ArrayList<ModelTrack> modelTracks;
    static View oldView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //start service
        Intent intent2=new Intent(MainActivity.this,MusicService.class);
        startService(intent2);
        //Get song list
        String path=Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+ Environment.DIRECTORY_MUSIC;
//        String path="/sdcard/Music";
        Log.i(TAG,path);
        Helper helper=new Helper();
        modelTracks=helper.loadFiles(path);
        MyAdapter myAdapter=new MyAdapter(MainActivity.this,R.layout.song_item,modelTracks);
        ListView lstSong=findViewById(R.id.lstMusic);
        lstSong.setAdapter(myAdapter);
        lstSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(oldView!=null){
                    TextView oldTvSong=oldView.findViewById(R.id.tvSongName);
                    oldTvSong.setTextColor(getResources().getColor(R.color.colorNotPlaying));
                }
                oldView=view;
                Log.i(TAG,modelTracks.get(position).getPath());
                TextView tvSong=view.findViewById(R.id.tvSongName);
                tvSong.setTextColor(getResources().getColor(R.color.colorIsPlaying));
                //send broadcast
                Intent intent=new Intent();
                intent.setAction("com.nl.bt_service_broadcast.ACTION_PLAY");
                intent.putExtra("song_path",modelTracks.get(position).getPath());
                sendBroadcast(intent);
            }
        });
    }
}
