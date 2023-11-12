package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BillingActivity extends AppCompatActivity {


    Button close_btn,history_btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);


        close_btn = findViewById(R.id.close_btn) ;
        history_btn = findViewById(R.id.history_btn);

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent creditCardIntent = new Intent(BillingActivity.this, MainActivity.class);
                startActivity(creditCardIntent);
            }
        });

        history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent creditCardIntent = new Intent(BillingActivity.this, TransactionHistoryActivity.class);
                startActivity(creditCardIntent);
            }
        });
    }
}