package com.example.trafficmi.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.example.trafficmi.R;
import com.example.trafficmi.ReportVehicleTheft;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChoiceOfWorkMenu extends AppCompatActivity {
    private Button driverOffenceBtn2, accidentSceneBtn, viewVehicleTheftBtn, analytics;

    private FloatingActionButton reportVehicleTheftBtn, reportDriverOffenceBtn,reportAccidentScene, viewReportstBtn2;

    FloatingActionButton fab_control;
    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_of_work_menu);

        driverOffenceBtn2= (Button) findViewById(R.id.driverOffenceBtn);

        accidentSceneBtn= (Button) findViewById(R.id.accidentSceneBtn);

        viewVehicleTheftBtn = findViewById(R.id.reportVehicleTheftBtn);

        viewReportstBtn2 = findViewById(R.id.accident_scene_fab_control);
        reportAccidentScene = findViewById(R.id.fab_control_as);
        reportDriverOffenceBtn = findViewById(R.id.fab_control_do);
        reportVehicleTheftBtn = findViewById(R.id.fab_control_vt);
        analytics = findViewById(R.id.analytics);
        reportAccidentScene.setVisibility(View.INVISIBLE);
        reportVehicleTheftBtn.setVisibility(View.INVISIBLE);
        reportDriverOffenceBtn.setVisibility(View.INVISIBLE);

        viewReportstBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag){
                    reportAccidentScene.show();
                    reportVehicleTheftBtn.show();
                    reportDriverOffenceBtn.show();
                    flag = false;
                }else {
                    flag = true;
                    reportAccidentScene.hide();
                    reportVehicleTheftBtn.hide();
                    reportDriverOffenceBtn.hide();
                }

            }
        });

        reportVehicleTheftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startActivity(new Intent(ChoiceOfWorkMenu.this, ViewVehicleTheft.class));

            }
        });

        analytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ChoiceOfWorkMenu.this, AnalyticsClass.class));

            }
        });
        reportDriverOffenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startActivity(new Intent(ChoiceOfWorkMenu.this, AccidentView.class));

            }
        });

        reportAccidentScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startActivity(new Intent(ChoiceOfWorkMenu.this, DriverOffenseDetail.class));

            }
        });

        driverOffenceBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUpdateDriverRecords();
            }
        });



        accidentSceneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUpdateVehicleRecords();
            }
        });

        viewVehicleTheftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToReportVehicleTheft();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void goToUpdateDriverRecords() {
        startActivity(new Intent(this, DriverOffence.class));
    }

    public void goToUpdateVehicleRecords() {
        startActivity(new Intent(this, AccidentScene.class));
    }

    public void goToReportVehicleTheft() {
        startActivity(new Intent(this, ReportVehicleTheft.class));
    }
}


