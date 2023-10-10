package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaymentConfirmationActivity extends AppCompatActivity {


    Button bill_button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirmation);

        bill_button = findViewById(R.id.bill_button);


        bill_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent creditCardIntent = new Intent(PaymentConfirmationActivity.this, FactureActivity.class);
                startActivity(creditCardIntent);
            }
        });
    }
}