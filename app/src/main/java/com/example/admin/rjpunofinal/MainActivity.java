package com.example.admin.rjpunofinal;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.admin.rjpunofinal.AsyncTasks.AsyncResponse;
import com.example.admin.rjpunofinal.AsyncTasks.WebserviceCall;
import com.example.admin.rjpunofinal.Helper.Utils;
import com.example.admin.rjpunofinal.Models.PushModel;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button b;
    EditText e;
    String url="http://vnurture.in/ptest/admin/webservice.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e=(EditText)findViewById(R.id.editTextEmail);
        b=(Button)findViewById(R.id.buttonRegister);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edi=e.getText().toString();
                SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
                final String token = pref.getString("regId", null);
                Toast.makeText(MainActivity.this, ""+token, Toast.LENGTH_SHORT).show();
                /*StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<String,String>();
                        params.put("fcm_token",token);

                        return params;

                    }
                };*/

                //MySingleton.getmInstance(MainActivity.this).addToRequestque(stringRequest);

                String[] keys = new String[]{"mode","deviceToken"};
                String[] values = new String[]{"testde",token};
                String jsonRequest = Utils.createJsonRequest(keys, values);

                String URL = "http://vnurture.in/ptest/admin/webservice.php";

                new WebserviceCall(MainActivity.this, URL, jsonRequest, "Loading", true, new AsyncResponse() {
                    public void onCallback(String response) {
                        Log.d("myrespon", response);
                        PushModel model = new Gson().fromJson(response, PushModel.class);
                        if (model.getStatus()==1) {
                            Toast.makeText(MainActivity.this, ""+model.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        // store id to sharedpreference
                    }
                }).execute();
            }
        });
    }
}
