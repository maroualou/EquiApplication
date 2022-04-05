package com.example.equiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SeancesActivity extends AppCompatActivity {

    public static final String EXTRA_ID_SEANCE = "id_seance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seances);
        setTitle("Vos Séances");
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, "http://10.0.2.2/equi/getSeances.php?clientId=" + Utile.c.getId(),
                    null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.w(SeancesActivity.class.getSimpleName(), "on a recu le json :" + response.toString());
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        try {
                            JSONArray Ja = response.getJSONArray("seances");
                            Utile.c.setSeances(null);
                            for (int i = 0; i < Ja.length(); i++) {
                                JSONObject Jo = Ja.getJSONObject(i);
                                int idSeance = Integer.parseInt(Jo.getString("seanceID"));
                                int durationMinut = Integer.parseInt(Jo.getString("durationMinut"));
                                String monitor = Jo.getString("userFName") + " " + Jo.getString("userLName");
                                Date parsedDate = dateFormat.parse(Jo.getString("startDate"));
                                Timestamp startDate = new Timestamp(parsedDate.getTime());
                                Utile.c.addSeances(new Seance(idSeance, monitor, durationMinut, startDate));
                            }
                            Log.w(SeancesActivity.class.getSimpleName(), "seances affectés au client" + Utile.c.getSeances().get(0).toString());
                            ListView l = findViewById(R.id.listSeances);
                            l.setAdapter(new SeancesAdapter(SeancesActivity.this, Utile.c.getSeances()));
                        } catch (ParseException e) {
                            Log.w(SeancesActivity.class.getSimpleName(), "probleme de convertion de timestamp ");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.w(SeancesActivity.class.getSimpleName(), "on n'a recu pas le json :" + error.toString());
                        }
                    });
            VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);

    }






}