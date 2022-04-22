package com.example.trafficmi.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.trafficmi.R;

public class ViewVehicleTheftDetails extends AppCompatActivity {
    TextView carRegnum,nameOfCar,colorOfCar,yearOfMake,otherDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vehicle_theft_details);
        carRegnum= findViewById(R.id.carNameTheft);
        nameOfCar= findViewById(R.id.carOwnerTheft);
        colorOfCar= findViewById(R.id.carRegNumTheft);
        yearOfMake= findViewById(R.id.sexTheft);
        otherDetails = findViewById(R.id.carYearOfMakeTheft);

        Intent intent = getIntent();
        carRegnum.setText("Vehicle Registration Number:" + " " + " " + intent.getStringExtra("carRegnum"));
        nameOfCar.setText("Name of Car:" + " " + " " + intent.getStringExtra("nameOfCar"));
        colorOfCar.setText("color of Car:" + " " + " " + intent.getStringExtra("colorOfCar"));
        yearOfMake.setText("Car Year of Make:" + " " + " " + intent.getStringExtra("yearOfMake"));
        otherDetails.setText("Other Details:" + " " + " " + intent.getStringExtra("otherDetails"));

    }
}