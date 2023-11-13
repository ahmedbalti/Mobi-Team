package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SuccessfulCheckoutActivity extends AppCompatActivity {

    Button bill_button, close_btn ;
    TextView paymentTypeTextView,amount_paid,transaction_id;
    String trasid ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_checkout);

        bill_button = findViewById(R.id.bill_button);
        close_btn = findViewById(R.id.close_btn);

        paymentTypeTextView= findViewById(R.id.paymentTypeTextView);
        amount_paid= findViewById(R.id.amount_paid);
        transaction_id= findViewById(R.id.transaction_id);


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("transactionId")) {
            String paymentMethod = intent.getStringExtra("paymentMethod");
            String price = intent.getDoubleExtra("price",0) + " DT";
            String transactionId = String.valueOf(intent.getIntExtra("transactionId",0));

            paymentTypeTextView.setText(paymentMethod);
            amount_paid.setText(price);
            transaction_id.setText(transactionId);
        } else {
            Toast.makeText(this, "Transaction details not available", Toast.LENGTH_SHORT).show();
        }


        bill_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent billIntent = new Intent(SuccessfulCheckoutActivity.this, BillingActivity.class);
                billIntent.putExtra("transactionId",intent.getIntExtra("transactionId",0));
                startActivity(billIntent);
            }
        });

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent billIntent = new Intent(SuccessfulCheckoutActivity.this, MainActivity.class);
                billIntent.putExtra("transactionId", intent.getStringExtra("transactionId"));
                startActivity(billIntent);
            }
        });


    }

    public void onBackIconClick(View view) {
        finish();
    }
}