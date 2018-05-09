package com.example.user.smartloans.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.smartloans.R;

public class ChpsdActivity extends AppCompatActivity {
    private EditText etCupswd;
    private EditText etNepswd;
    private EditText etCpswd;
    private Button btnSave;
    private  Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chpsd);


        etCupswd = findViewById(R.id.etCupswd);
        etNepswd = findViewById(R.id.etNepswd);
        etCpswd = findViewById(R.id.etCpswd);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etCupswd.getText().toString().isEmpty() && !etNepswd.getText().toString().isEmpty()&& !etCpswd.getText().toString().isEmpty()){
                    Toast.makeText(ChpsdActivity.this,
                            "Changes Updated",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChpsdActivity.this,SideActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(ChpsdActivity.this,
                            "Please fill any Empty Fields",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChpsdActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
