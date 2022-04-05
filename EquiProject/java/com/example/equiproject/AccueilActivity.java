package com.example.equiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class AccueilActivity extends AppCompatActivity {

    private static final int VOS_SEANCES = 101;
    private static final int VOS_HISTORIQUES = 102;
    private static final int VOS_PROFILE = 103;
    public static final String LOG_OUT = "logingout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_accueil);

    }

    @Override
    protected void onResume() {
        super.onResume();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, "http://10.0.2.2/equi/getProfil.php?clientId=" + Utile.c.getId()
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.has("error")) {
                        Log.d(AccueilActivity.class.getSimpleName(),"id doesn't exist");
                    }else {
                        Utile.c.setfName(response.getJSONObject("user").getString("fName"));
                        Utile.c.setlName(response.getJSONObject("user").getString("lName"));
                        TextView title = findViewById(R.id.AccueilTitle);
                        title.setText("Welcome "+ Utile.c.getfName() +"  "+ Utile.c.getlName());
                        Log.w(AccueilActivity.class.getSimpleName(), "voila notre objet : " + Utile.c.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(AccueilActivity.class.getSimpleName(),"noooo recu !!"+error.getMessage());
            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);

    }

    public void showSeances(View view) {
        Intent iAccueil = new Intent(getApplicationContext(), SeancesActivity.class);
        startActivityForResult(iAccueil, VOS_SEANCES);
    }

    public void goPofil(View view) {
        Intent iAccueil = new Intent(getApplicationContext(), Profile.class);
        startActivityForResult(iAccueil, VOS_PROFILE);
    }

    public void showHistorique(View view) {
        Intent iAccueil = new Intent(getApplicationContext(), Historique.class);
        startActivityForResult(iAccueil, VOS_HISTORIQUES);
    }
    @Override
    public void onBackPressed() {

    }

    public void logout(View view) {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        i.putExtra(LOG_OUT,true);
        startActivity(i);
        finish();
    }
}