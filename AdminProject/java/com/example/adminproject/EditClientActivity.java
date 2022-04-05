package com.example.adminproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EditClientActivity extends AppCompatActivity {
    public static Client c ;
    int idClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);
    }

    @Override
    protected void onResume() {
        super.onResume();
        c = new Client();
        idClient = this.getIntent().getIntExtra(CliensAdapter.ID_CLIENT,0);
        Log.w(EditClientActivity.class.getSimpleName(), "id client : "+idClient);

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, "http://10.0.2.2/equi/getProfil.php?clientId=" + idClient,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.has("error"))
                        Log.w(EditClientActivity.class.getSimpleName(), " Error can not load the data  " );
                    else {
                        //Log.d(Utile.class.getSimpleName(), "on a recu le json :\n" + response.getJSONObject("user").getString("userFName"))
                        //getting Clinet attrebuts
                        int id = Integer.parseInt(response.getJSONObject("user").getString("clientID"));
                        int priceRate = Integer.parseInt(response.getJSONObject("user").getString("priceRate"));
                        String notes = response.getJSONObject("user").getString("notes");
                        String passwd = response.getJSONObject("user").getString("passwd");
                        String identityNumber = response.getJSONObject("user").getString("identityNumber");
                        String identityDoc = response.getJSONObject("user").getString("identityDoc");
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
                            c = new Client(id,priceRate,fName,lName,iNumber,email,passwd,clientPhone,identityDoc,notes,birthDate,inscriptionDate,ensurenceValidity,licenceValidity);
                            VolleySingleton.getInstance(getApplicationContext()).getImageLoader().get("http://10.0.2.2/equi/imageProfils/" + response.getJSONObject("user").getString("photo"), new ImageLoader.ImageListener() {
                                @Override
                                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                                    Log.w(EditClientActivity.class.getSimpleName(), "ON a recu la photo !!!! ");
                                    c.setPhoto(response.getBitmap());
                                    ImageView imageProfil = findViewById(R.id.imageProfile);
                                    imageProfil.setImageBitmap(c.getPhoto());
                                    Log.w(EditClientActivity.class.getSimpleName(), " voila la photo " + c.getPhoto());
                                }

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.w(EditClientActivity.class.getSimpleName(), "ON a pas recu la photo !!!! ");
                                }
                            });
                            Log.w(EditClientActivity.class.getSimpleName(), "voila notre objet : " + c.toString()+ "passsword "+c.getPasword());
                            TextView Fnametitle = findViewById(R.id.Fname);
                            TextView Lnametitle = findViewById(R.id.Lname);
                            TextView Fname = findViewById(R.id.fnametxt);
                            TextView Lname = findViewById(R.id.lnametxt);
                            TextView Birthdate = findViewById(R.id.birthDatetxt);
                            TextView IDnumber = findViewById(R.id.inumbertxt);
                            TextView InscrpDate = findViewById(R.id.inscriptionDatetxt);
                            TextView EnsurValid = findViewById(R.id.ensurenceValiditytxt);
                            TextView Licence = findViewById(R.id.licenceValiditytxt);
                            TextView ClientPhone = findViewById(R.id.phonetxt);
                            TextView ClientEmail = findViewById(R.id.emailtxt);
                            TextView PriceRate = findViewById(R.id.priceRatetxt);
                            TextView password = findViewById(R.id.paswordtxt);
                            TextView note = findViewById(R.id.notestxt);
                            TextView icode = findViewById(R.id.identityDoctxt);

                            Fname.setText(c.getfName());
                            Lname.setText(c.getlName());
                            Birthdate.setText(c.getBirthDate());
                            IDnumber.setText(c.getiNumber());
                            InscrpDate.setText(c.getInscriptionDate());
                            EnsurValid.setText(c.getEnsurenceValidity());
                            Licence.setText(c.getLicenceValidity());
                            ClientPhone.setText(c.getClientPhone());
                            ClientEmail.setText(c.getEmail());
                            PriceRate.setText(c.getPriceRate()+"");
                            password.setText(c.getPasword()+"");
                            note.setText(c.getNotes());
                            icode.setText(c.getIdentityDoc());
                            Fnametitle.setText(c.getfName());
                            Lnametitle.setText(c.getlName());


                        } catch (ParseException e) {

                            Log.d(EditClientActivity.class.getSimpleName(), "il y a un probleme au niveau de la conversion des timestamp");
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

                Log.d(EditClientActivity.class.getSimpleName(),"noooo recu !!"+error.getMessage());
            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
    }

    public void update(View view) {
        Log.d(EditClientActivity.class.getSimpleName(),"button clicked");
        Toast.makeText(EditClientActivity.this,"button clique",Toast.LENGTH_LONG).show();
        StringRequest req = new StringRequest(Request.Method.POST, Utile.UPDATE_CLIENT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(EditClientActivity.class.getSimpleName(),response);
                        try {
                            JSONObject resp = new JSONObject(response);
                            if(resp.getString("status").toString().equals("true")){
                                Toast.makeText(EditClientActivity.this,"informations modifiées",Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(EditClientActivity.this,"aucune modification est fait !!",Toast.LENGTH_LONG).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(EditClientActivity.class.getSimpleName(),"errorrrrrr");
                Toast.makeText(getBaseContext(),"informatios ne sont pas modifiées",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {

                Map<String, String> params = new HashMap<String, String>();

                TextView Fname = findViewById(R.id.fnametxt);
                TextView Lname = findViewById(R.id.lnametxt);
                TextView Birthdate = findViewById(R.id.birthDatetxt);
                TextView IDnumber = findViewById(R.id.inumbertxt);
                TextView InscrpDate = findViewById(R.id.inscriptionDatetxt);
                TextView EnsurValid = findViewById(R.id.ensurenceValiditytxt);
                TextView Licence = findViewById(R.id.licenceValiditytxt);
                TextView ClientPhone = findViewById(R.id.phonetxt);
                TextView ClientEmail = findViewById(R.id.emailtxt);
                TextView PriceRate = findViewById(R.id.priceRatetxt);
                TextView password = findViewById(R.id.paswordtxt);
                TextView note = findViewById(R.id.notestxt);
                TextView icode = findViewById(R.id.identityDoctxt);
                params.put("clientID", idClient+"");
                params.put("fName", Fname.getText().toString());
                params.put("lName",Lname.getText().toString());
                params.put("identityNumber", IDnumber.getText().toString());
                params.put("identityDoc", icode.getText().toString());
                params.put("clientEmail", ClientEmail.getText().toString());
                params.put("passwd", password.getText().toString());
                params.put("clientPhone", ClientPhone.getText().toString());
                params.put("birthDate", Birthdate.getText().toString());
                params.put("inscriptionDate", InscrpDate.getText().toString());
                params.put("ensurenceValidity", EnsurValid.getText().toString());
                params.put("licenceValidity", Licence.getText().toString());
                params.put("priceRate", PriceRate.getText().toString());
                params.put("notes", note.getText().toString());
                return params;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
    }

    public void ShowSeances(View view) {
        Intent iSeances = new Intent(getApplicationContext(),SeanceClientActivity.class);
        startActivity(iSeances);
    }
}