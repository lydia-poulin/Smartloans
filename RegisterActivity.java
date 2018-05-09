package com.example.user.smartloans.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.smartloans.Connections.HttpRequestHelper;
import com.example.user.smartloans.R;
import com.example.user.smartloans.models.User;
import com.example.user.smartloans.utils.AppConstants;
import com.example.user.smartloans.utils.SharedPref_Values;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText mMob;
    private EditText mPswd;
        private EditText mEmail;
    private Button mLogin;
    private Button mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

                mMob = findViewById(R.id.etMobNo1);
                mPswd = findViewById(R.id.etPassword1);
                      mEmail = findViewById(R.id.etEmail);
                 mLogin = findViewById(R.id.btnLogin1);
                mRegister = findViewById(R.id.btnRegister1);

                mRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        String mob_txt = mMob.getText().toString().trim();
                        String pass_txt = mPswd.getText().toString().trim();
                        String email_txt = mEmail.getText().toString().trim();

                        User user = new User();
                        user.mob_no = mob_txt;
                        user.password_hash = pass_txt;
                        user.email = email_txt;
                        registration(user);

                        if (!mMob.getText().toString().isEmpty() && !mPswd.getText().toString().isEmpty() && !mEmail.getText().toString().isEmpty() ){
                            Toast.makeText(RegisterActivity.this,
                                    "Registration  Successful",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);

                        }else {
                            Toast.makeText(RegisterActivity.this,
                                    "Please fill any Empty Fields",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                mLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);




                        }
                });


    }
    private void registration(User model) {
        try {

            JSONObject requestJson = new JSONObject();

            requestJson.put("mob_no", model.mob_no);
            requestJson.put("password_hash", model.password_hash);
            requestJson.put("email", model.email);
            requestJson.put("device_token","cccc");
            requestJson.put("device_type", "android");


            JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, HttpRequestHelper.App_BaseUrl +
                    HttpRequestHelper.app_Login_URL, requestJson, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.d(">>>>>>>>>>>>>>>",""+response);
                    try {
                        if (response.getBoolean("status")) {
                            if (response.has("data") && response.get("data") != null) {
                                //for profile
                                User model = new Gson().fromJson(String.valueOf(response.get("data")), User.class);

                                if (model != null && model.id != null) {


                                    new SharedPref_Values().setUserPreference(RegisterActivity.this, model);
                                    startActivity(new Intent(RegisterActivity.this, SideActivity.class)
                                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                    RegisterActivity.this.finish();

                                }
//                                else
//                                {
//                                    Toast.makeText(Login.this,"", Toast.LENGTH_LONG).show();
//                                }


                            }


                        }else
                        if (response.has("message")&&response.getString("message")!=null)
                            Toast.makeText(RegisterActivity.this,response.getString("message"),Toast.LENGTH_SHORT).show();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegisterActivity.this,"Error..!!",Toast.LENGTH_SHORT).show();

                }


            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", String.format("Basic %s", Base64.encodeToString(String.format("%s:%s",
                            HttpRequestHelper.app_Authentication_Username, HttpRequestHelper.app_Authentication_Password).getBytes(), Base64.DEFAULT)));
                    return params;
                }
            };

            com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);

            jor.setShouldCache(false);
            requestQueue.add(jor);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

