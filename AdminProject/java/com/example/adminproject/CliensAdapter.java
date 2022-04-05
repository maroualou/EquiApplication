package com.example.adminproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CliensAdapter extends ArrayAdapter<Client> {

    public static final String ID_CLIENT = "seance_id";

    public CliensAdapter(@NonNull Context context, @NonNull List<Client> objects) {
        super(context, 0, objects);
    }


    @NonNull
    @Override
    public View getView(int i, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newItem;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        newItem = li.inflate(R.layout.client_item, parent, false);
        TextView Fname = newItem.findViewById(R.id.ClientFname);
        TextView Lname = newItem.findViewById(R.id.ClientLname);
        Button edit = newItem.findViewById(R.id.editButton);
        Button delete = newItem.findViewById(R.id.deleteButton);
        //btn.setId(getItem(i).getId());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getContext(), EditClientActivity.class);
                it.putExtra(ID_CLIENT, getItem(i).getId());
                getContext().startActivity(it);
                Log.d(CliensAdapter.class.getSimpleName(), "id client :" + getItem(i).getId());
            }

        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getContext(), DeleteClientActivity.class);
                it.putExtra(ID_CLIENT, getItem(i).getId());
                getContext().startActivity(it);
                Log.d(CliensAdapter.class.getSimpleName(), "id client :" + getItem(i).getId());
            }

        });
        Fname.setText(getItem(i).getfName());
        Lname.setText(getItem(i).getlName());
        if (i % 2 == 0) {
            newItem.setBackgroundColor(Color.parseColor("#bab7ad"));
        } else {
            newItem.setBackgroundColor(Color.parseColor("#e3dfd1"));
        }
        return newItem;
    }
}
