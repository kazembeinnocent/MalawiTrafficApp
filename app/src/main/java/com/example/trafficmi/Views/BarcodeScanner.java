package com.example.trafficmi.Views;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class BarcodeScanner  extends Activity implements ZBarScannerView.ResultHandler {
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 100;
    private ZBarScannerView mScannerView;
    private static final String TAG = "BarcodeScanner";

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        if(checkAndRequestPermissions()){
            setupBarcode();
        }else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_ID_MULTIPLE_PERMISSIONS);

        }




        // Set the scanner view as the content view
    }

    private void setupBarcode() {
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);
        mScannerView.setAutoFocus(true);
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();
    }


    @Override
    public void onPause() {
        super.onPause();
        if(mScannerView != null){
            mScannerView.stopCamera();
        }
                   // Stop camera on pause
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mScannerView != null){
            setupBarcode();
        }
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here

        if(rawResult != null && rawResult.getContents() != null){
            Intent intent = new Intent();
            intent.putExtra("data", rawResult.getContents());
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public boolean checkAndRequestPermissions() {

        int cameraPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded
                            .toArray(new String[0]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_ID_MULTIPLE_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(),
                        "The app need access to camera", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }else {
                setupBarcode();
            }
        }

    }

}