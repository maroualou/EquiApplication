package com.example.adminproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final String PREFS_AUTOLOGIN = "prefAutoLog";
    private static final String Pref_Login = "PrefsLogin";
    private  static final String Pref_password = "prefsPasswd";
    private static final int NEW_LOGIN = 101 ;
    private static final int DESTROY_LOGIN = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

        Log.w(LoginActivity.class.getSimpleName(),"username :"+ userName+" and password :"+password);

        StringRequest req = new StringRequest(Request.Method.POST, Utile.LOGIN_URL
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String resp) {
                try {
                    JSONObject response = new JSONObject(resp);
                    if (response.has("error")) {
                        Log.w(LoginActivity.class.getSimpleName(), resp);
                        Toast.makeText(LoginActivity.this,response.getString("error"),Toast.LENGTH_LONG).show();
                    }else {
                        Log.w(LoginActivity.class.getSimpleName(), resp);
                        //getting Clinet attrebutss
                        int id  = Integer.parseInt(response.getJSONObject("user").getString("userID"));
                        Utile.u = new User();
                        Utile.u.setId(id);
                        Log.w(LoginActivity.class.getSimpleName(), "voila notre objet : " + Utile.u.toString());
                        //Log.d(LoginActivitytivity.class.getSimpleName(), "on a recu le json :\n" + response.getJSONObject("user").getString("userFName"));
                        if(autoLogin){
                            SharedPreferences prefs = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
                            SharedPreferences.Editor ed = prefs.edit();
                            ed.putString(Pref_Login,userName);
                            ed.putString(Pref_password,password);
                            ed.putBoolean(PREFS_AUTOLOGIN,autoLogin);
                            ed.apply();
                        }
                        Intent iAccueil = new Intent(getApplicationContext(), AccueilActivity.class);
                        startActivity(iAccueil);
                        finishActivity(DESTROY_LOGIN);
                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this,"Web Service failed to load data",Toast.LENGTH_LONG).show();
                Log.d(LoginActivity.class.getSimpleName(),"noooo recu !!"+error.getMessage());
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