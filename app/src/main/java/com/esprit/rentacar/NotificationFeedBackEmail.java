package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NotificationFeedBackEmail extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_feed_back_email);
        textView = findViewById(R.id.TextViewData);
        String data = getIntent().getStringExtra("data");
        textView.setText(data);
    }
}