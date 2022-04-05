package com.example.adminproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AccueilActivity extends AppCompatActivity {

    public static final String LOG_OUT = "log_out";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        setTitle("accieul activity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        StringRequest req = new StringRequest(Request.Method.POST, Utile.GET_PROFIL_URL
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String resp) {
                try {
                    JSONObject response = new JSONObject(resp);
                    if (response.has("error")) {
                        Log.d(AccueilActivity.class.getSimpleName(),"id doesn't exist");
                    }else {
                        Utile.u.setfName(response.getJSONObject("user").getString("userFName"));
                        Utile.u.setlName(response.getJSONObject("user").getString("userLName"));
                        TextView title = findViewById(R.id.AccueilTitle);
                        title.setText("Welcome "+ Utile.u.getfName() +"  "+ Utile.u.getlName());
                        Log.w(AccueilActivity.class.getSimpleName(), "voila notre objet : " + Utile.u.toString());
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
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("adminId", Utile.u.getId()+"");
                return params;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);

    }

    public void logout(View view) {
        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
        i.putExtra(LOG_OUT,true);
        startActivity(i);
        finish();
    }

    public void goPofil(View view) {
    }
    @Override
    public void onBackPressed() {

    }

    public void showClients(View view) {
        Intent i = new Intent(getApplicationContext(),ClientsActivity.class);
        startActivity(i);
    }

    public void showUsers(View view) {
    }
}