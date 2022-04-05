package com.example.adminproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StringRequest req = new StringRequest(Request.Method.POST, Utile.GET_CLIENTS_URL
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String resp) {
                try {
                    JSONObject response = new JSONObject(resp);
                    if (response.has("error")) {
                        Log.d(AccueilActivity.class.getSimpleName(),"id doesn't exist");
                    }else {
                            Client c ;
                            JSONArray Ja = response.getJSONArray("clients");
                            List<Client> clients = new ArrayList<Client>();
                            for (int i = 0; i < Ja.length(); i++) {
                                c =  new Client();
                                JSONObject Jo = Ja.getJSONObject(i);
                                c.setId(Integer.parseInt(Jo.getString("clientID")));
                                c.setfName(Jo.getString("fName"));
                                c.setlName(Jo.getString("lName"));
                                clients.add(c);
                            }
                            ListView l = findViewById(R.id.listClints);
                            l.setAdapter(new CliensAdapter(ClientsActivity.this, clients));

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

    public void AjouterClient(View view) {
        Intent iAddClient  = new Intent(getApplicationContext(),AddClientActivity.class);
        startActivity(iAddClient);
    }
}