package com.example.adminproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
    }

    public void AjouterClient(View view) {
        StringRequest req = new StringRequest(Request.Method.POST, "http://10.0.2.2/equi/addClient.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(AddClientActivity.class.getSimpleName(),response);
                       /* try {
                            JSONObject resp = new JSONObject(response);
                            if(resp.getString("status").toString().equals("true")){
                                Toast.makeText(AddClientActivity.this,"Client Ajouté",Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(AddClientActivity.this,"Client n'a pas ete ajouter",Toast.LENGTH_LONG).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(AddClientActivity.class.getSimpleName(),"errorrrrrr");
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
}