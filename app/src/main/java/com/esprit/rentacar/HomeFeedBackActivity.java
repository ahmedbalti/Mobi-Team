package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
=======

import android.content.Intent;
>>>>>>> 27ec3c4948ea7d7159c37a03a0003395b4416bac
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeFeedBackActivity extends AppCompatActivity {

<<<<<<< HEAD
    Button send, feedback,other;
=======
    Button sendBtn ;
>>>>>>> 27ec3c4948ea7d7159c37a03a0003395b4416bac

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_feed_back2);
<<<<<<< HEAD
        feedback = (Button) findViewById(R.id.feedback);
        send = findViewById(R.id.send);
        other = findViewById(R.id.other);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feedbackIntent = new Intent(HomeFeedBackActivity.this, FeedBackActivity.class);
                startActivity(feedbackIntent);
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedbackIntent = new Intent(HomeFeedBackActivity.this, ListFeedBack.class);
                startActivity(feedbackIntent);
            }
        });


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MakeNotification();
                Intent feedbackIntent = new Intent(HomeFeedBackActivity.this, SendEmailFeedBackActivity.class);
                startActivity(feedbackIntent);
            }
        });


    }


    //getSupportActionBar().setTitle("send");

        public void openFeedBack(View view){
            startActivity(new Intent(HomeFeedBackActivity.this,FeedBackActivity.class));
=======


         //getSupportActionBar().setTitle("send");

            sendBtn = findViewById(R.id.send) ;

            sendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent creditCardIntent = new Intent(HomeFeedBackActivity.this, FeedBackActivity.class);
                    startActivity(creditCardIntent);
                }
            });
        }
        public void openFeedBack(View view){
            startActivity(new Intent(this,FeedBackActivity.class));
>>>>>>> 27ec3c4948ea7d7159c37a03a0003395b4416bac
        }

    }
