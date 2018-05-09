package com.example.user.smartloans.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.smartloans.R;

public class ForgotActivity extends AppCompatActivity {
    private EditText etMobNo;
    private EditText etEmail;
    private Button btnReset;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        etMobNo = findViewById(R.id.etMobNo);
        etEmail = findViewById(R.id.etEmail);
        btnReset = findViewById(R.id.btnReset);
        btnCancel = findViewById(R.id.btnCancel);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etMobNo.getText().toString().isEmpty() && !etEmail.getText().toString().isEmpty()){
                    Toast.makeText(ForgotActivity.this,
                            "Please enter the OTP",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ForgotActivity.this,LoginActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(ForgotActivity.this,
                            "Please fill any Empty Fields",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ForgotActivity.this,ForgotActivity.class);
                startActivity(intent);

            }
        });
    }
}
