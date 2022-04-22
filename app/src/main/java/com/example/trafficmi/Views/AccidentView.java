package com.example.trafficmi.Views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trafficmi.AdapterPackage.AccidentAdapter;
import com.example.trafficmi.Model.AccidentSceneModel;
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

public class AccidentView extends AppCompatActivity {

    //Floating button initialization
    private FloatingActionButton accident_scene_fab_control;

    TextInputEditText search_id;
    RecyclerView recyclerView;

    // Firebase database
    FirebaseDatabase root = FirebaseDatabase.getInstance();
    DatabaseReference reference = root.getReference().child("AccidentSceneRecords");

    AccidentAdapter accidentAdapter;
    ArrayList<AccidentSceneModel> dataValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident_view);

        //firebase offline capability
        reference.keepSynced(true);
        dataValues = new ArrayList<AccidentSceneModel>();
        recyclerView = findViewById(R.id.rec_accident);
        accidentAdapter = new AccidentAdapter(this, dataValues);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(accidentAdapter);

        search_id = (TextInputEditText) findViewById(R.id.search_field_id);

        //driver_offence_fab_control
        accident_scene_fab_control= findViewById(R.id.accident_scene_fab_control);

        //Add driver offences

        accident_scene_fab_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccidentView.this,  AccidentScene.class));
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

                            dataValues.add(new AccidentSceneModel(userData.get("vehicleRegNumber").toString(), userData.get("vehicleColor").toString(), userData.get("nameOfVehicle").toString(), userData.get("make").toString(), userData.get("otherDetails").toString()));

                        }catch (ClassCastException cce){


                            try{

                            }catch (ClassCastException cce2){

                            }
                        }

                    }
                    accidentAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        search_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filtered(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void filtered(String text) {
        ArrayList<AccidentSceneModel> modelArrayListFiltered = new ArrayList<>();
        for (AccidentSceneModel model: dataValues){
            if (model.getRegNum().toLowerCase().contains(text.toString().toLowerCase())){
                modelArrayListFiltered.add(model);
            }
        }
        accidentAdapter.filterList(modelArrayListFiltered);
    }


}