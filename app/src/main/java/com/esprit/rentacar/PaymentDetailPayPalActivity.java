package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaymentDetailPayPalActivity extends AppCompatActivity {

    Button paypal_submit_button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail_pay_pal);

        paypal_submit_button = findViewById(R.id.paypal_submit_button);


        paypal_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent creditCardIntent = new Intent(PaymentDetailPayPalActivity.this, PaymentConfirmationActivity.class);
                startActivity(creditCardIntent);
            }
        });
    }
}