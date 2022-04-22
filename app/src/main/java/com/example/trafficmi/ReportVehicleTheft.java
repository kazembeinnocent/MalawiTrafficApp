package com.example.trafficmi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.trafficmi.Model.VehicleTheftReport;
import com.example.trafficmi.Views.BarcodeScanner;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReportVehicleTheft extends AppCompatActivity {

    //toolBar
    Toolbar vehicleTheftToolBar;
    FusedLocationProviderClient fusedLocationProviderClient;

    TextInputEditText carName;
    TextInputEditText carMake;
    TextInputEditText carColor;
    TextInputEditText vehicleRegNumber;
    TextInputEditText vehicle_blue_book;
    EditText locationTheft;
    EditText vehicleTheftDescription;
    Button reportTheftBtn;
    Button searchBtn;
    RadioGroup radioGroupSex;
    RadioButton radioSexButton;
    RecyclerView recyclerView;


    //FirebaseDatabase
    FirebaseDatabase root;
    DatabaseReference referenci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_vehicle_theft);

//        //Initializations
        carName = (TextInputEditText) findViewById(R.id.car_name);
        carMake = (TextInputEditText) findViewById(R.id.car_make);
        carColor = (TextInputEditText) findViewById(R.id.car_color);
        vehicleRegNumber = (TextInputEditText) findViewById(R.id.reg_num);
        vehicle_blue_book = (TextInputEditText) findViewById(R.id.blue_book);
        vehicleTheftDescription = (EditText) findViewById(R.id.otherOffenceDetails);
        reportTheftBtn = (Button) findViewById(R.id.reportTheftBtn);
        locationTheft = findViewById(R.id.locationTheft);
        radioGroupSex = findViewById(R.id.radioGroupSex);




//        searchBtn = findViewById(R.id.action_search);
        recyclerView = findViewById(R.id.recycler_view_id);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.ACCESS_FINE_LOCATION,  Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        }else {
              new FusedLocationProviderClient(this).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

            }
        });
        }




        //Tool bar

        vehicleTheftToolBar = (Toolbar) findViewById(R.id.vehicleTheftToolBar);

       //vehicleTheftToolBar.setTitle("VEHICLE THEFT");
        setSupportActionBar(vehicleTheftToolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        reportTheftBtn.setOnClickListener(v -> goToViewVehicleTheft());



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.vehicle_theft_menu, menu);
        return true;
    }

    // Handling menu items events

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.log_out:
                startActivity(new Intent(this, LogIn.class));
                return true;
            case R.id.help:
                startActivity(new Intent(this, HelpCenter.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // if yes,Exit
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if No, cancel and continue
                dialog.cancel();

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
    private void goToViewVehicleTheft() {

        //radio buttons
        // get selected radio button from radioGroup
        int selectedId = radioGroupSex.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioSexButton = (RadioButton) findViewById(selectedId);


        String selectedSex = radioSexButton.getText().toString();


        //firebase Database

        root = FirebaseDatabase.getInstance();
        referenci = root.getReference();
        referenci = root.getReference(  "VehicleTheftReport");


        String fullNameCar = carName.getText().toString().trim();
        if(fullNameCar.isEmpty()){

//            carName.setError("Driver name cannot be empty");
        }else{
//            carName.setError("");

        }
        String makeOfCar = carMake.getText().toString().trim();
        if(makeOfCar.isEmpty()){

//            carMake.setError("make cannot be empty");
        }else{
//            carMake.setError("");

        }

        String colorOfVehicle = carColor.getText().toString().trim();
        if(colorOfVehicle.isEmpty()){

//            carColor.setError("color cannot be empty");
        }else{
//            carColor.setError("");

        }

        String regNumberOfVehicle = vehicleRegNumber.getText().toString().trim();
        if(regNumberOfVehicle.isEmpty()){

//            vehicleRegNumber.setError("vehicle reg number cannot be empty");
        }else{
//            vehicleRegNumber.setError("");

        }
        String CarOwner =  vehicle_blue_book.getText().toString().trim();
        if(CarOwner.isEmpty()){

//            vehicle_blue_book .setError("blue book cannot be empty");
        }

        String accidentDescription = vehicleTheftDescription.getText().toString().trim();

        if (accidentDescription.isEmpty()) {
            // accidentDescription.setErrorEnabled(true);
            vehicleTheftDescription.setError("Name of a car cannot be empty");
        }

        String location =  locationTheft.getText().toString().trim();

        if (location.isEmpty()) {
            // accidentDescription.setErrorEnabled(true);
//            locationTheft.setError("Name of a car cannot be empty");
        }

        else{


            //Writing to database

//            carNameTheft,carOwnerTheft,sexTheft,carRegNumTheft,carYearOfMakeTheft,colorOfCarTheft,locationTheft,detailsOfTheft;

            VehicleTheftReport vehicleTheftReport = new VehicleTheftReport( fullNameCar, makeOfCar, colorOfVehicle, regNumberOfVehicle, CarOwner, accidentDescription, selectedSex,location);
            referenci.child(regNumberOfVehicle).setValue(vehicleTheftReport);
            Toast.makeText(getApplicationContext(), "Reported Successfully...", Toast.LENGTH_LONG).show();

            carName.setText("");
            carMake.setText("");
            carColor.setText("");
            vehicleRegNumber.setText("");
            vehicle_blue_book.setText("");
            vehicleTheftDescription.setText("");
//            startActivity( new Intent(this, ViewVehicleTheft.class));


//            startActivity( new Intent(this,DriverOffenseDetail.class));

        }
    }


}
