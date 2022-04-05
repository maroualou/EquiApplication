package com.example.equiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Profile extends AppCompatActivity {

    private static final int NEW_LOGIN = 101 ;
    public static final String USER_NAME = "userName";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Votre profil ");
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, "http://10.0.2.2/equi/getProfil.php?clientId=" + Utile.c.getId(),
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.has("error"))
                        Log.w(Profile.class.getSimpleName(), " Error can not load the data  " );
                    else {
                        //Log.d(Utile.class.getSimpleName(), "on a recu le json :\n" + response.getJSONObject("user").getString("userFName"))
                        //getting Clinet attrebuts
                        int id = Integer.parseInt(response.getJSONObject("user").getString("clientID"));
                        int priceRate = Integer.parseInt(response.getJSONObject("user").getString("priceRate"));
                        String fName = response.getJSONObject("user").getString("fName");
                        String lName = response.getJSONObject("user").getString("lName");
                        String iNumber = response.getJSONObject("user").getString("identityNumber");
                        String email = response.getJSONObject("user").getString("clientEmail");
                        String clientPhone = response.getJSONObject("user").getString("clientPhone");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        Date parsedDate;
                        try {
                            parsedDate = dateFormat.parse(response.getJSONObject("user").getString("birthDate"));
                            Timestamp birthDate = new Timestamp(parsedDate.getTime());
                            parsedDate = dateFormat.parse(response.getJSONObject("user").getString("inscriptionDate"));
                            Timestamp inscriptionDate = new Timestamp(parsedDate.getTime());
                            parsedDate = dateFormat.parse(response.getJSONObject("user").getString("ensurenceValidity"));
                            Timestamp ensurenceValidity = new Timestamp(parsedDate.getTime());
                            parsedDate = dateFormat.parse(response.getJSONObject("user").getString("licenceValidity"));
                            Timestamp licenceValidity = new Timestamp(parsedDate.getTime());
                            Utile.c = new Client(id, priceRate, fName, lName, iNumber, email, clientPhone, birthDate, inscriptionDate, ensurenceValidity, licenceValidity);
                            VolleySingleton.getInstance(getApplicationContext()).getImageLoader().get("http://10.0.2.2/equi/imageProfils/" + response.getJSONObject("user").getString("photo"), new ImageLoader.ImageListener() {
                                @Override
                                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                                    Log.w(Profile.class.getSimpleName(), "ON a recu la photo !!!! ");
                                    Utile.c.setPhoto(response.getBitmap());
                                    ImageView imageProfil = findViewById(R.id.imageProfile);
                                    imageProfil.setImageBitmap(Utile.c.getPhoto());
                                    Log.w(Profile.class.getSimpleName(), " voila la photo " + Utile.c.getPhoto());
                                }

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.w(Profile.class.getSimpleName(), "ON a pas recu la photo !!!! ");
                                }
                            });
                            Log.w(Profile.class.getSimpleName(), "voila notre objet : " + Utile.c.toString());
                            TextView Fname = findViewById(R.id.Fname);
                            TextView Lname = findViewById(R.id.Lname);
                            TextView Birthdate = findViewById(R.id.BirthDate);
                            TextView IDnumber = findViewById(R.id.identityNumber);
                            TextView InscrpDate = findViewById(R.id.InscriptionDate);
                            TextView EnsurValid = findViewById(R.id.EnsurenceValidity);
                            TextView Licence = findViewById(R.id.LicenceValidity);
                            TextView ClientPhone = findViewById(R.id.ClientPhone);
                            TextView ClientEmail = findViewById(R.id.ClientEmail);
                            TextView PriceRate = findViewById(R.id.PriceRate);

                            Fname.setText(Utile.c.getfName());
                            Lname.setText(Utile.c.getlName());
                            Birthdate.setText(Utile.c.getBirthDate());
                            IDnumber.setText(Utile.c.getiNumber());
                            InscrpDate.setText(Utile.c.getInscriptionDate());
                            EnsurValid.setText(Utile.c.getEnsurenceValidity());
                            Licence.setText(Utile.c.getLicenceValidity());
                            ClientPhone.setText(Utile.c.getClientPhone());
                            ClientEmail.setText(Utile.c.getEmail());
                            PriceRate.setText(Utile.c.getPriceRate()+"");


                        } catch (ParseException e) {

                            Log.d(Profile.class.getSimpleName(), "il y a un probleme au niveau de la conversion des timestamp");
                            e.printStackTrace();
                        }
                        //////////////////////////


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(Profile.class.getSimpleName(),"noooo recu !!"+error.getMessage());
            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
    }
    }

