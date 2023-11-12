package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessfulCheckoutActivity extends AppCompatActivity {

    Button bill_button, close_btn,history_btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_checkout);

        bill_button = findViewById(R.id.bill_button);
        close_btn = findViewById(R.id.close_btn);

        bill_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent creditCardIntent = new Intent(SuccessfulCheckoutActivity.this, BillingActivity.class);
                startActivity(creditCardIntent);
            }
        });

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent creditCardIntent = new Intent(SuccessfulCheckoutActivity.this, MainActivity.class);
                startActivity(creditCardIntent);
            }
        });


    }

    public void onBackIconClick(View view) {
        finish();
    }
}