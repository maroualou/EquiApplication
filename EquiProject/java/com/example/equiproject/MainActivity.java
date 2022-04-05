package com.example.equiproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_LOGIN = 101 ;
    public static final String USER_NAME = "userName";

    private String url ="http://10.0.2.2/equi/clientLogin.php";
    private static final String PREFS_AUTOLOGIN = "prefAutoLog";
    private static final String Pref_Login = "PrefsLogin";
    private  static final String Pref_password = "prefsPasswd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean logout = this.getIntent().getBooleanExtra(AccueilActivity.LOG_OUT,false);
        if (logout){
            SharedPreferences.Editor ed = prefs.edit();
            ed.putString(Pref_Login,"");
            ed.putString(Pref_password,"");
            ed.putBoolean(PREFS_AUTOLOGIN,false);
            ed.apply();
        }

        boolean bool = prefs.getBoolean(PREFS_AUTOLOGIN,false);
        if(bool){
            String login = prefs.getString(Pref_Login,"");
            String password = prefs.getString(Pref_password,"");
            authenticate(login,password,bool);
        }
    }

    public void Connexion(View view) {
        Boolean autoLogin= false;
        EditText username = findViewById(R.id.usernameTxt);
        EditText passwd = findViewById(R.id.passwordTxt);
        String userName = username.getText().toString() ;
        String password = passwd.getText().toString();
        autoLogin = ((Switch)findViewById(R.id.autoL)).isChecked();
        authenticate(userName,password,autoLogin);
        }

    private void authenticate(String userName,String  password , Boolean autoLogin) {

        Log.w(MainActivity.class.getSimpleName(),"username :"+ userName+" and password :"+password);

        StringRequest req = new StringRequest(Request.Method.POST, url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String resp) {
                try {
                    JSONObject response = new JSONObject(resp);
                    if (response.has("error")) {
                        Log.w(MainActivity.class.getSimpleName(), resp);
                        Toast.makeText(MainActivity.this,response.getString("error"),Toast.LENGTH_LONG).show();
                    }else {
                        Log.w(MainActivity.class.getSimpleName(), resp);
                        //getting Clinet attrebutss
                        int id  = Integer.parseInt(response.getJSONObject("user").getString("clientID"));
                        Utile.c = new Client();
                        Utile.c.setId(id);
                        Log.w(MainActivity.class.getSimpleName(), "voila notre objet : " + Utile.c.toString());
                        //Log.d(MainActivity.class.getSimpleName(), "on a recu le json :\n" + response.getJSONObject("user").getString("userFName"));
                        if(autoLogin){
                            SharedPreferences prefs = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
                            SharedPreferences.Editor ed = prefs.edit();
                            ed.putString(Pref_Login,userName);
                            ed.putString(Pref_password,password);
                            ed.putBoolean(PREFS_AUTOLOGIN,autoLogin);
                            ed.apply();
                        }
                        Intent iAccueil = new Intent(getApplicationContext(), AccueilActivity.class);
                        startActivityForResult(iAccueil, NEW_LOGIN);
                        finishActivity(MainActivity.CONTEXT_INCLUDE_CODE);
                        //finishActivity(this);
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Web Service failed to load data",Toast.LENGTH_LONG).show();
                Log.d(MainActivity.class.getSimpleName(),"noooo recu !!"+error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", userName);
                params.put("password", password);
                return params;
            }
        };

        //queue.add(req);
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
    }
    @Override
    public void onBackPressed() {

    }
}