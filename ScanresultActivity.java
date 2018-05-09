package com.example.user.smartloans.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.smartloans.R;
import com.squareup.picasso.Picasso;

public class ScanresultActivity extends AppCompatActivity {

    private ImageView logo;
    private TextView txtCompany;
    private EditText etAmt;
    private EditText etPin;
    private Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanresult);


        logo = findViewById(R.id.logo);
        txtCompany = findViewById(R.id.txtCompany);
        etAmt = findViewById(R.id.etAmt);

        etPin = findViewById(R.id.etPin);
        btnPay = findViewById(R.id.btnPay);

        Intent intent = getIntent();
        String Logo = intent.getStringExtra("Logo");
        Picasso.get().load(Logo).into(logo);
        String name= intent.getStringExtra("Name");
     txtCompany.setText(getIntent().getStringExtra("Name"));
        String amount = intent.getStringExtra("Amount");
etAmt.setText(getIntent().getStringExtra("Amount"));
       // String Description= intent.getStringExtra("Description");
        String Id = intent.getStringExtra("Id");
        etPin.setText(getIntent().getStringExtra("Id"));





        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ScanresultActivity.this,ForgotActivity.class);
                startActivity(intent);

            }
        });


    }
}
