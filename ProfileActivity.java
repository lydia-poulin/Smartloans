package com.example.user.smartloans.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.smartloans.R;

public class ProfileActivity extends AppCompatActivity {
    private Button btnRegister2;
    private Button btnLogin2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        btnRegister2 = findViewById(R.id.btnRegister2);
        btnLogin2 = findViewById(R.id.btnLogin2);


        btnRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(ProfileActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


        btnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}
