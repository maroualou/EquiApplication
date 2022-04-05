package com.example.equiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

public class Historique extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);
        setTitle("Vos historique");
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, "http://10.0.2.2/equi/historique.php?clientId=" + Utile.c.getId(),
                    null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.w(Historique.class.getSimpleName(), "on a recu le json :" + response.toString());
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        try {
                            JSONArray Ja = response.getJSONArray("historiques");
                            Utile.c.setHistoriques(null);
                            for (int i = 0; i < Ja.length(); i++) {
                                JSONObject Jo = Ja.getJSONObject(i);
                                int idSeance = Integer.parseInt(Jo.getString("seanceID"));
                                int durationMinut = Integer.parseInt(Jo.getString("durationMinut"));
                                String monitor = Jo.getString("userFName") + " " + Jo.getString("userLName");
                                Date parsedDate = dateFormat.parse(Jo.getString("startDate"));
                                Timestamp startDate = new Timestamp(parsedDate.getTime());
                                Utile.c.addHistorique(new Seance(idSeance, monitor, durationMinut, startDate));
                            }
                            Log.w(Historique.class.getSimpleName(), "seances affectés au client" + Utile.c.getHistoriques().get(0).toString());
                            ListView l = findViewById(R.id.listHistoriques);
                            l.setAdapter(new SeancesAdapter(Historique.this, Utile.c.getHistoriques()));
                        } catch (ParseException e) {
                            Log.w(Historique.class.getSimpleName(), "probleme de convertion de timestamp ");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.w(Historique.class.getSimpleName(), "on n'a recu pas le json :" + error.toString());
                        }
                    });
            VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
        }
    }
