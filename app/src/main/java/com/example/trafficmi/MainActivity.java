package com.example.trafficmi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trafficmi.Views.ChoiceOfWorkMenu;
import com.example.trafficmi.Views.DriverOffence;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button chiefTrafficOfficerLogInBtn;
    Animation top_animation, bottom_animation;
    ImageView ic_car;
    TextView appLabelTxt;
    private static int SPLASH_SCREEN = 2000;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        // Animation
        top_animation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_animation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        // Views

        ic_car = (ImageView)findViewById(R.id.ic_car);
        appLabelTxt = (TextView) findViewById(R.id.appLabelTxt);

        // Setting animation
        ic_car.setAnimation(bottom_animation);
        appLabelTxt.setAnimation(top_animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LogIn.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);


    }



}