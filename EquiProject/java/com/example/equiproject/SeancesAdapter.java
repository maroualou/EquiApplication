package com.example.equiproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SeancesAdapter extends ArrayAdapter<Seance> {

    public static final String ID_SEANCE = "seance_id";
    public static final String EXTRA_START_DATE = "start_date";

    public SeancesAdapter(@NonNull Context context, @NonNull List<Seance> objects) {
        super(context, 0, objects);
    }


    @NonNull
    @Override
    public View getView(int i, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newItem;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        newItem = li.inflate(R.layout.seance_item,parent,false);
        TextView  startDate = newItem.findViewById(R.id.SeanceTimeLabel);
        TextView duration = newItem.findViewById(R.id.SeanceDuration);
        TextView monitor = newItem.findViewById(R.id.SeacneMonitor);
        ImageButton btn = newItem.findViewById(R.id.defaultBtnId);
        //btn.setId(getItem(i).getId());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getContext(),RemarqueActivity.class);
                it.putExtra(ID_SEANCE,getItem(i).getId());
                it.putExtra(EXTRA_START_DATE,getItem(i).getStartDate());
                getContext().startActivity(it);
                Log.d(SeancesActivity.class.getSimpleName(),"id seance :"+getItem(i).getId());
            }
            
        });
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
