package com.example.user.smartloans.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.user.smartloans.R;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private static final int REQUEST_CAMERA = 1 ;
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M)
        {
            if (checkPermission())
            {
                Toast.makeText(ScanActivity.this,"Permission is granted!",Toast.LENGTH_LONG).show();
            }
            else
            {
                requestPermission();
            }
        }


    }

        private boolean checkPermission()
        {
        return (ContextCompat.checkSelfPermission(ScanActivity.this, CAMERA) == PackageManager.PERMISSION_GRANTED);
        }

        private void requestPermission()
        {
            ActivityCompat.requestPermissions(this,new String[]{CAMERA},REQUEST_CAMERA);
        }

public void onRequestPermissionResult(int requestCode,String permission[] ,int grantResults[])
{
    switch (requestCode){
        case REQUEST_CAMERA:
            if (grantResults.length > 0)
            {
                boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (cameraAccepted)
                {
                    Toast.makeText(ScanActivity.this,"Permission granted",Toast.LENGTH_LONG).show();
                }
                else
                {
                   Toast.makeText(ScanActivity.this,"Permission denied",Toast.LENGTH_LONG).show();
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                   {
                       if (shouldShowRequestPermissionRationale(CAMERA))
                       {
                        displayAlertMessage("You need to allow access for both permissions",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            requestPermissions(new String[]{CAMERA} ,REQUEST_CAMERA);
                                        }

                                    }
                                });
                        return;
                       }
                   }
                }
            }
        break;
    }
}

@Override
public  void  onResume()
{
    super.onResume();

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
    {
        if (checkPermission())
        {
            if (scannerView == null)
            {
                scannerView = new ZXingScannerView(this);
                setContentView(scannerView);
            }
            scannerView.setResultHandler(this);
            scannerView.startCamera();
        }
        else
        {
            requestPermission();
        }
    }
}

@Override
public  void onDestroy(){
        super.onDestroy();
        scannerView.stopCamera();
}
public void displayAlertMessage(String message, DialogInterface.OnClickListener listener)
{
    new AlertDialog.Builder(ScanActivity.this)
            .setMessage(message)
            .setPositiveButton("OK" , listener)
            .setNegativeButton("CANCEL" , null)
            .create()
            .show();
}
    @Override
    public void handleResult(Result result) {
    final String scanresult = result.getText();
    String id ,name,amount ,logo ;
        try {
            JSONObject QRresult = new JSONObject(scanresult);
            id = QRresult.getString("Id");
            name = QRresult.getString("Name");
            amount =QRresult.getString("Amount");
            logo = QRresult.getString("Logo");


            Intent intent = new Intent(ScanActivity.this,ScanresultActivity.class);
            intent.putExtra("Logo",logo);
            intent.putExtra("Name",name);
            intent.putExtra("Amount",amount);

            intent.putExtra("Id",id);

            startActivity(intent);
            return;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Scan Result");
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            scannerView.resumeCameraPreview(ScanActivity.this);
        }
    });
        builder.setNeutralButton("Visit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse(scanresult));
                startActivity(intent);
            }
        });
        builder.setMessage(scanresult);
        AlertDialog alert =builder.create();
        alert.show();


    }
}
