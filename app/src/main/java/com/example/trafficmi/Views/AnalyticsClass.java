package com.example.trafficmi.Views;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trafficmi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class AnalyticsClass extends AppCompatActivity {
    TextView offences, accident_id, vehicle_theft_id, sample_id;
    Button for_location;
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics_class);

        for_location = (Button) findViewById(R.id.for_location);
        //firebase offline capability



        offences = (TextView) findViewById(R.id.offences_id);
        accident_id = (TextView) findViewById(R.id.accident_id);
        vehicle_theft_id = (TextView) findViewById(R.id.vehicle_theft_id);
        sample_id = (TextView) findViewById(R.id.sample_id);
        pieChart = findViewById(R.id.piechart);

        FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    long dOffences = snapshot.child("DriverOffences").getChildrenCount();
                    long aR = snapshot.child("AccidentSceneRecords").getChildrenCount();
                    long vTheft = snapshot.child("VehicleTheftReport").getChildrenCount();

                    float total = dOffences + aR + vTheft;

                    float dPerc = (dOffences / total) * 100;
                    float arPerc = (aR / total) * 100;
                    float vTPerc = (vTheft / total) * 100;

                    int colorDOffences = Color.parseColor("#FFA726");
                    int colorForVTheft = Color.parseColor("#66BB6A");
                    int colorForAccidentSc = Color.parseColor("#EF5350");


                    pieChart.addPieSlice(
                            new PieModel(
                                    "Driver Offences",
                                    dPerc,
                                    colorDOffences
                            ));
                    pieChart.addPieSlice(
                            new PieModel(
                                    "Vehicle Theft",
                                    vTPerc,
                                    colorForVTheft
                            ));
                    pieChart.addPieSlice(
                            new PieModel(
                                    "Accident Scene",
                                    arPerc,
                                    colorForAccidentSc
                            ));

                    // To animate the pie chart
                    pieChart.startAnimation();
                    accident_id.setText(aR+"");
                    vehicle_theft_id.setText(vTheft+"");
                    offences.setText(dOffences+"");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}