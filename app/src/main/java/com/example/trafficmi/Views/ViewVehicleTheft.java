package com.example.trafficmi.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.example.trafficmi.AdapterPackage.VehicleTheftAdapter;
import com.example.trafficmi.Model.ModelClass;
import com.example.trafficmi.R;
import com.example.trafficmi.ReportVehicleTheft;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewVehicleTheft extends AppCompatActivity {

    //Floating button initialization
    private FloatingActionButton vehicle_theft_fab_control;

    TextView tvView;
    TextInputEditText search_id_2;
    RecyclerView recyclerView;

// Firebase database
    FirebaseDatabase root = FirebaseDatabase.getInstance();
    DatabaseReference reference = root.getReference().child("VehicleTheftReport");

    VehicleTheftAdapter vehicleTheftAdapter;
    ArrayList<ModelClass> dataValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vehicle_theft);
        dataValues = new ArrayList<ModelClass>();
        recyclerView = findViewById(R.id.recycler_view_id);
        vehicleTheftAdapter = new VehicleTheftAdapter(this, dataValues);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(vehicleTheftAdapter);
        reference.keepSynced(true);
        search_id_2 = (TextInputEditText) findViewById(R.id.search_field_id_2);


        //driver_offence_fab_control
        vehicle_theft_fab_control = findViewById(R.id.accident_scene_fab_control);

        //Add driver offences

        vehicle_theft_fab_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewVehicleTheft.this, ReportVehicleTheft.class));
            }
        });
        // Firebase initialisations
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                            HashMap<String, Object> dataMap = (HashMap<String, Object>) snapshot.getValue();

                            for (String key : dataMap.keySet()){

                                Object data = dataMap.get(key);

                                try{
                                    HashMap<String, Object> userData = (HashMap<String, Object>) data;

                                            dataValues.add(new ModelClass(userData.get("carName").toString(), userData.get("carColor").toString(), userData.get("carMake").toString(), userData.get("vehicleRegNumber").toString(),userData.get("vehicleTheftDescription").toString(),userData.get("selectedSex").toString(), userData.get("vehicle_blue_book").toString(),userData.get("location").toString()));

                                }catch (ClassCastException cce){


                                    try{


                                    }catch (ClassCastException cce2){

                                    }
                                }

                            }
                    vehicleTheftAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        search_id_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filteredVehicle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void filteredVehicle(String text) {
        ArrayList<ModelClass> modelArrayListFiltered = new ArrayList<>();
        for (ModelClass model: dataValues){
            if (model.getCarRegNumTheft().toLowerCase().contains(text.toString().toLowerCase())){
                modelArrayListFiltered.add(model);
            }
        }
        vehicleTheftAdapter.filteredList(modelArrayListFiltered);
    }
}
