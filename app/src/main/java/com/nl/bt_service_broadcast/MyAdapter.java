package com.nl.bt_service_broadcast;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ModelTrack> modelTracks;
    LayoutInflater inflater;
    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ModelTrack> modelTracks) {
        super(context, resource, modelTracks);
        this.context=context;
        this.resource=resource;
        this.modelTracks=modelTracks;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView=inflater.inflate(resource,parent,false);
        }
        TextView tvSongName=convertView.findViewById(R.id.tvSongName);
        tvSongName.setText(modelTracks.get(position).getName());
        return convertView;
    }
}
