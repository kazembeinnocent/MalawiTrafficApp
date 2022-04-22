package com.example.trafficmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RoadTrafficRepo extends AppCompatActivity {
    private TextInputLayout vehicleRegNumber, carColor, carMake, carName;
    private Button updateVehicleRecordsBtn;
    private Toolbar accidentSceneToolBar;

    //firebase database

    FirebaseDatabase root;
    DatabaseReference referenci;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);



    }
}

