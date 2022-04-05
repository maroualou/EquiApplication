package com.example.adminproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SeanceClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seance_client);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, "http://10.0.2.2/equi/getSeances.php?clientId=" + EditClientActivity.c.getId(),
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.w(SeanceClientActivity.class.getSimpleName(), "on a recu le json :" + response.toString());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    try {
                        JSONArray Ja = response.getJSONArray("seances");
                        EditClientActivity.c.setSeances(null);
                        for (int i = 0; i < Ja.length(); i++) {
                            JSONObject Jo = Ja.getJSONObject(i);
                            int idSeance = Integer.parseInt(Jo.getString("seanceID"));
                            int durationMinut = Integer.parseInt(Jo.getString("durationMinut"));
                            String monitor = Jo.getString("userFName") + " " + Jo.getString("userLName");
                            Date parsedDate = dateFormat.parse(Jo.getString("startDate"));
                            Timestamp startDate = new Timestamp(parsedDate.getTime());
                            EditClientActivity.c.addSeances(new Seance(idSeance, monitor, durationMinut, startDate));
                        }
                        Log.w(SeanceClientActivity.class.getSimpleName(), "seances affectés au client" + EditClientActivity.c.getSeances().get(0).toString());
                        ListView l = findViewById(R.id.listSeances);
                        l.setAdapter(new SeanceClientAdapter(SeanceClientActivity.this, EditClientActivity.c.getSeances()));
                    } catch (ParseException e) {
                        Log.w(SeanceClientActivity.class.getSimpleName(), "probleme de convertion de timestamp ");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.w(SeanceClientActivity.class.getSimpleName(), "on n'a recu pas le json :" + error.toString());
                    }
                });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
    }
}