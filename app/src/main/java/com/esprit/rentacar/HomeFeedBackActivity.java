package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeFeedBackActivity extends AppCompatActivity {

    Button sendBtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_feed_back2);


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
        }

    }
