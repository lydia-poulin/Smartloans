package com.example.user.smartloans.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.smartloans.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

public class EdproActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etEmailid;
    private EditText etMobNo;
        private Button btngenerate;
    private Button btnUpdate;
    private ImageView ivQRcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edpro);

        etName = findViewById(R.id.etName);
        etEmailid = findViewById(R.id.etEmailid);
        etMobNo = findViewById(R.id.etMobNo);
        ivQRcode = findViewById(R.id.ivQRcode);
        btngenerate = findViewById(R.id.btngenerate);
        btnUpdate = findViewById(R.id.btnUpdate);

        btngenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String MobNo = etMobNo.getText().toString();

                JSONObject Params = new JSONObject();
                try {
                    Params.put("Id","116");
                    Params.put("Name","Cubes");
                    Params.put("Amount",MobNo);
                    Params.put("Logo","https://www.gstatic.com/webp/gallery3/3.png");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix  bitMatrix = multiFormatWriter.encode(Params.toString(), BarcodeFormat.QR_CODE,150,150);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    ivQRcode.setImageBitmap(bitmap);


                } catch (WriterException e){
                    e.printStackTrace();
                }

            }
        });



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etName.getText().toString().isEmpty() && !etEmailid.getText().toString().isEmpty()&& !etMobNo.getText().toString().isEmpty()){
                    Toast.makeText(EdproActivity.this,
                            "Changes Updated",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EdproActivity.this,SideActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(EdproActivity.this,
                            "Please fill any Empty Fields",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
