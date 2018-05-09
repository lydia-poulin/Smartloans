package com.example.user.smartloans.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.smartloans.R;

public class SetcodeActivity extends AppCompatActivity {
    private EditText etCupin;
    private EditText etNepin;
    private EditText etCopin;
    private Button btnSave;
    private Button btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setcode);

        etCupin = findViewById(R.id.etCupswd);
        etNepin = findViewById(R.id.etNepswd);
        etCopin = findViewById(R.id.etCpswd);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etCupin.getText().toString().isEmpty() && !etNepin.getText().toString().isEmpty()&& !etCopin.getText().toString().isEmpty()){
                    Toast.makeText(SetcodeActivity.this,
                            "Changes Updated",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SetcodeActivity.this,SideActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(SetcodeActivity.this,
                            "Please fill any Empty Fields",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetcodeActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
