package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.esprit.rentacar.FirstPage;
import com.esprit.rentacar.LogoAnimation2;

import java.util.Timer;
import java.util.TimerTask;

public class LogoAnimation extends AppCompatActivity {

    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LogoAnimation.this, LogoAnimation2.class));
                finish();
            }
        }, 3000);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(LogoAnimation.this, FirstPage.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}