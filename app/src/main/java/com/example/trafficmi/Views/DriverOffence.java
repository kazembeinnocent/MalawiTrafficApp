package com.example.trafficmi.Views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trafficmi.HelpCenter;
import com.example.trafficmi.LogIn;
import com.example.trafficmi.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DriverOffence extends AppCompatActivity {
    private TextInputEditText fullNameOfDriver;

    EditText driverOffenceDescription, edLoactionName;
    private Button updateDriverRecordsBtn;
    RadioGroup offenceRadioGroup;
    RadioButton radioSexButton;
    String longitude, latitude, address, licenceNumber;




    //toolBar
    Toolbar driverOffenceToolBar;

    //firebase database

    FirebaseDatabase root;
    DatabaseReference referenci;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private final int GET_BARCODE_rESULTS = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_offence);
        fullNameOfDriver = (TextInputEditText) findViewById(R.id.driverName);
        driverOffenceDescription = (EditText) findViewById(R.id.otherOffenceDetails);
        driverOffenceToolBar = (Toolbar) findViewById(R.id.driverOffenceToolBar);
        TextView getLicenceNumber = findViewById(R.id.licenseNum);
        edLoactionName = findViewById(R.id.ed_loaction_name);

//        textView = findViewById(R.id.textView1);
        offenceRadioGroup = findViewById(R.id.offenceRadioGroup);
        // fused location initialization
        fusedLocationProviderClient = new FusedLocationProviderClient(this);

        setSupportActionBar(driverOffenceToolBar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getLicenceNumber.setOnClickListener(v->{
            Intent i = new Intent(this, BarcodeScanner.class);
            startActivityForResult(i, GET_BARCODE_rESULTS);
        });

        updateDriverRecordsBtn = (Button) findViewById(R.id.goToNextOffenceBtn);

        updateDriverRecordsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(DriverOffence.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(DriverOffence.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 50);
                }
                driverOffenceRecords();
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
            Location location = task.getResult();

            if (location != null) {
                Geocoder geocoder = new Geocoder(DriverOffence.this, Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    latitude = String.valueOf(addresses.get(0).getLatitude());
                    longitude = String.valueOf(addresses.get(0).getLongitude());
                    address = String.valueOf(addresses.get(0).getAddressLine(0));

                    Toast.makeText(DriverOffence.this, "Lat : "+latitude+"\nLong: "+longitude+"\nAdd :"+address, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
   @Override
  public boolean onSupportNavigateUp(){
       onBackPressed();
      return true;
    }
   public void driverOffenceRecords(){
        //firebase Database
       //radio buttons
       // get selected radio button from radioGroup
       int selectedId = offenceRadioGroup.getCheckedRadioButtonId();

       Toast.makeText(this, selectedId+"", Toast.LENGTH_SHORT).show();
       // find the radiobutton by returned id
       radioSexButton = (RadioButton) findViewById(selectedId);


     //  String selectedSex = radioSexButton.getText().toString();

      // Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

//
//       if (selectedId == -1){
//            radioSexButton.setError("Choose sex");
//       }else{
//          selectedSex = radioSexButton.getText().toString();
//       }

       root = FirebaseDatabase.getInstance();
       referenci = root.getReference();
        referenci = root.getReference(  "DriverOffences");

        String fullNameDriver = fullNameOfDriver.getText().toString().trim();
        if(fullNameDriver.isEmpty()){
//           fullNameOfDriver.setError("Driver name cannot be empty");
        }else{
//            fullNameOfDriver.setError("");

        }

       String offenceDescription = driverOffenceDescription.getText().toString().trim();

       if (offenceDescription.isEmpty()) {
           // accidentDescription.setErrorEnabled(true);
          driverOffenceDescription.setError("Offence description cannot be empty");
       }


        else{
//           Toast.makeText(this, lat, Toast.LENGTH_SHORT).show();

            //Writing to database
           com.example.trafficmi.DriverOffenceRecords driverOffenceRecords;
           if(licenceNumber != null){
               driverOffenceRecords = new com.example.trafficmi.DriverOffenceRecords(fullNameDriver, licenceNumber, offenceDescription, radioSexButton.getText().toString(), latitude, longitude, address);

           }else {
               driverOffenceRecords = new com.example.trafficmi.DriverOffenceRecords(fullNameDriver, edLoactionName.getText().toString(), offenceDescription, radioSexButton.getText().toString(), latitude, longitude);

           }
           referenci.child(fullNameDriver).setValue(driverOffenceRecords);

           Toast.makeText(this,
                    "Records Successfully updated",
                    Toast.LENGTH_LONG)
                   .show();

           fullNameOfDriver.setText("");

           driverOffenceDescription.setText("");
           //startActivity( new Intent(this, DriverOffenseDetail.class));

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 50){
            getLocation();
        }

    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GET_BARCODE_rESULTS && resultCode == RESULT_OK){
            licenceNumber = data.getStringExtra("data");
            Toast.makeText(this, licenceNumber, Toast.LENGTH_SHORT).show();
        }

    }
}