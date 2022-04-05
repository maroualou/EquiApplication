package com.example.adminproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SeanceClientAdapter extends ArrayAdapter<Seance> {


    public SeanceClientAdapter(@NonNull Context context, @NonNull List<Seance> objects) {
        super(context, 0, objects);
    }


    @NonNull
    @Override
    public View getView(int i, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newItem;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        newItem = li.inflate(R.layout.seance_client_item,parent,false);
        TextView  startDate = newItem.findViewById(R.id.SeanceTimeLabel);
        TextView duration = newItem.findViewById(R.id.SeanceDuration);
        TextView monitor = newItem.findViewById(R.id.SeacneMonitor);

        startDate.setText(getItem(i).getStartDate());
        duration.setText(getItem(i).getDurationMinut());
        monitor.setText(getItem(i).getMonitor());
        if (i%2==0)
        {
            newItem.setBackgroundColor(Color.parseColor("#bab7ad"));
        }else {
            newItem.setBackgroundColor(Color.parseColor("#e3dfd1"));
        }
        return newItem;
    }
}
