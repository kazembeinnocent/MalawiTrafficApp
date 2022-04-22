package com.example.trafficmi.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.trafficmi.AdapterPackage.DriverOffinceAdapter;
import com.example.trafficmi.Model.AccidentSceneModel;
import com.example.trafficmi.Model.DriversOffenceModel;
import com.example.trafficmi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DriverOffenseDetail extends AppCompatActivity {

    //Floating button initialization
    private FloatingActionButton driver_offence_fab_control;

    TextInputEditText searchDriver_id;
    RecyclerView recyclerView;
    // Firebase database
    FirebaseDatabase root = FirebaseDatabase.getInstance();
    DatabaseReference reference = root.getReference().child("DriverOffences");

    DriverOffinceAdapter driverOffinceAdapter;
    ArrayList<DriversOffenceModel> dataValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_offense_detail);
        dataValues = new ArrayList<DriversOffenceModel>();
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        recyclerView = findViewById(R.id.recycler_view);
        driverOffinceAdapter = new DriverOffinceAdapter(this, dataValues);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(driverOffinceAdapter);
        reference.keepSynced(true);
        searchDriver_id = (TextInputEditText) findViewById(R.id.searchDriver_field_id);

        //driver_offence_fab_control
        driver_offence_fab_control = findViewById(R.id.accident_scene_fab_control);

        //Add driver offences

        driver_offence_fab_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DriverOffenseDetail.this, DriverOffence.class));
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
                            String vr=userData.get("driverName").toString();
                            Log.i("drivername",vr);

                            dataValues.add(new DriversOffenceModel(userData.get("driverName").toString(),userData.get("driverOffenceDescription").toString(), userData.get("licenseNumber").toString(), userData.get("selectedSex").toString(), userData.get("lat").toString(), userData.get("longt").toString(), userData.get("address").toString()));
//                            dataValues.add(new DriversOffenceModel(userData.get("driverName").toString(),userData.get("driverOffenceDescription").toString(), userData.get("driverOffenceLocation").toString(), userData.get("licenseNumber").toString(), userData.get("selectedSex").toString(),userData.get("latitude").toString(),userData.get("longitude").toString()));

                        }catch (ClassCastException cce){


                            try{



                            }catch (ClassCastException cce2){

                            }
                        }

                    }
                    driverOffinceAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        searchDriver_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterd(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void filterd(String text) {

        ArrayList<DriversOffenceModel> modelArrayListFiltered = new ArrayList<>();
        for (DriversOffenceModel model: dataValues){
            if (model.getLisenceNumber().toLowerCase().contains(text.toString().toLowerCase())){
                modelArrayListFiltered.add(model);
            }
        }
       driverOffinceAdapter.filterList(modelArrayListFiltered);
    }

}