package com.example.user.smartloans.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class LoginActivity extends AppCompatActivity {
    private EditText mPhone;
    private EditText mPassword;
    private Button mLogin;
    private TextView mForget;
    private  Button mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


               mPhone = findViewById(R.id.etMobNo);
           mPassword = findViewById(R.id.etPassword);
                mLogin = findViewById(R.id.btnLogin);
                mForget = findViewById(R.id.txtForgot);
                mRegister = findViewById(R.id.btnRegister);


                mLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        User user = new User();

                        String mob_txt = mPhone.getText().toString().trim();
                        String pass_txt = mPassword.getText().toString().trim();
                        user.mob_no = mob_txt;
                        user.password_hash = pass_txt;
                        login(user);

//                        if (!mPhone.getText().toString().isEmpty() && !mPassword.getText().toString().isEmpty()){
//                            Toast.makeText(LoginActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(LoginActivity.this,SideActivity.class);
//                            startActivity(intent);
//
//                        }else {
//                            Toast.makeText(LoginActivity.this, "Please fill any Empty Fields", Toast.LENGTH_SHORT).show();
//
//                        }
                    }
                });

        mForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this,ForgotActivity.class);
                startActivity(intent);

            }
        });

       mRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
               startActivity(intent);

           }
       });

    }
    private void login(User model) {
        try {

            JSONObject requestJson = new JSONObject();

            requestJson.put("mob_no", model.mob_no);
            requestJson.put("password_hash", model.password_hash);
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


                                    new SharedPref_Values().setUserPreference(LoginActivity.this, model);
                                    startActivity(new Intent(LoginActivity.this, SideActivity.class)
                                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                    LoginActivity.this.finish();

                                }
//                                else
//                                {
//                                    Toast.makeText(Login.this,"", Toast.LENGTH_LONG).show();
//                                }


                            }


                        }else
                        if (response.has("message")&&response.getString("message")!=null)
                            Toast.makeText(LoginActivity.this,response.getString("message"),Toast.LENGTH_SHORT).show();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this,"Error..!!",Toast.LENGTH_SHORT).show();

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

            com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);

            jor.setShouldCache(false);
            requestQueue.add(jor);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

    