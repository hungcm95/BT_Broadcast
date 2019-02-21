package com.nl.bt_service_broadcast;

import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.File;
import java.util.ArrayList;

public class Helper {
    public static ArrayList<ModelTrack> loadFiles(String dirPath){
        ArrayList<ModelTrack> modelTracks=new ArrayList<>();
        File dir=new File(dirPath);
        File[] files=dir.listFiles();
        Log.i("AAAA",""+files.length);
        for(File file:files){
            if(file.isFile()){
                if(file.getAbsolutePath().endsWith("mp3")){
                    String name=file.getName();
                    String path=file.getAbsolutePath();
                    ModelTrack modelTrack=new ModelTrack();
                    modelTrack.setName(name);
                    modelTrack.setPath(path);
                    modelTracks.add(modelTrack);
                }
            }
        }
        return modelTracks;
    }
}
