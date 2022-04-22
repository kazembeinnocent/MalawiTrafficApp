package com.example.trafficmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class DriverOffenceData extends AppCompatActivity {
    TextView driverName,sex,licenseNumber,location,otherDetails2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_offence_data);
        driverName= findViewById(R.id.driversN);
        sex= findViewById(R.id.sexId);
        licenseNumber= findViewById(R.id.license);
        otherDetails2 = findViewById(R.id.otherDetail);
        Intent intent = getIntent();
        driverName.setText("Driver's Name :" + " " + " " + intent.getStringExtra("driverName"));
        sex.setText("Sex :" + " " + " " + intent.getStringExtra("driverSex"));
        licenseNumber.setText("License Number :" + " " + " " + intent.getStringExtra("offenceDescription"));
        otherDetails2.setText("Other Details :" + " " + " " + intent.getStringExtra("offenceLocation"));

    }
}