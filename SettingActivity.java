package com.example.user.smartloans.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.user.smartloans.R;

public class SettingActivity extends AppCompatActivity {
    private ImageButton ImgBack;
    private Button btnProfile;
    private Button btnLanguage;
    private Button btnChangepswd;
    private  Button btnSetcode;
    private Button btnterms;
    private Button btnPrivacy;
    private  Button btnLogout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ImgBack = findViewById(R.id.ImgBack);
        btnProfile = findViewById(R.id.btnProfile);
        btnChangepswd = findViewById(R.id.btnChangepswd);
        btnSetcode = findViewById(R.id.btnSetcode);
        btnterms = findViewById(R.id.btnterms);
        btnPrivacy = findViewById(R.id.btnPrivacy);
        btnLogout = findViewById(R.id.btnLogout);

        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SettingActivity.this,SideActivity.class);
                startActivity(intent);

            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SettingActivity.this,EdproActivity.class);
                startActivity(intent);

            }
        });

        btnChangepswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SettingActivity.this,ChpsdActivity.class);
                startActivity(intent);

            }
        });
        btnSetcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SettingActivity.this,SetcodeActivity.class);
                startActivity(intent);

            }
        });
        btnterms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SettingActivity.this,TermsActivity.class);
                startActivity(intent);

            }
        });
        btnPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SettingActivity.this,PrivacyActivity.class);
                startActivity(intent);

            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SettingActivity.this,ProfileActivity.class);
                startActivity(intent);

            }
        });
    }
}
