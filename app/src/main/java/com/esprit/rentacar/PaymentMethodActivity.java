package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class PaymentMethodActivity extends AppCompatActivity {

    RadioButton creditCardRadioButton;
    RadioButton paypalRadioButton;
    Button proceedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        creditCardRadioButton = findViewById(R.id.radio_credit_card);
        paypalRadioButton = findViewById(R.id.radio_paypal);
        proceedButton = findViewById(R.id.pay_meth_button);

        proceedButton.setEnabled(false);

        creditCardRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceedButton.setEnabled(true);
            }
        });

        paypalRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceedButton.setEnabled(true);
            }
        });

        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (creditCardRadioButton.isChecked()) {
                    Intent creditCardIntent = new Intent(PaymentMethodActivity.this, PaymentDetailVisaActivity.class);
                    startActivity(creditCardIntent);
                } else if (paypalRadioButton.isChecked()) {
                    Intent paypalIntent = new Intent(PaymentMethodActivity.this, PaymentDetailPayPalActivity.class);
                    startActivity(paypalIntent);
                } else {
                    Toast.makeText(PaymentMethodActivity.this, "Please select a payment method", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
