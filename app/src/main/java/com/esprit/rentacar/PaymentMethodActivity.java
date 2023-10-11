package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class PaymentMethodActivity extends AppCompatActivity {

    CheckBox checkbox_credit_card, checkbox_paypal,in_site_checkbox ;
    Button pay_meth_button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        checkbox_credit_card = findViewById(R.id.checkbox_credit_card) ;
        checkbox_paypal = findViewById(R.id.checkbox_paypal) ;
        in_site_checkbox = findViewById(R.id.in_site_checkbox) ;

        pay_meth_button = findViewById(R.id.pay_meth_button);

        final CheckBox[] currentlySelectedCheckbox = {null};

        pay_meth_button.setEnabled(false);

        pay_meth_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentlySelectedCheckbox[0] == checkbox_credit_card) {
                    Intent creditCardIntent = new Intent(PaymentMethodActivity.this, VisaCardFormActivity.class);
                    startActivity(creditCardIntent);
                } else if (currentlySelectedCheckbox[0] == checkbox_paypal) {
                    Intent creditCardIntent = new Intent(PaymentMethodActivity.this, PaypalFormActivity.class);
                    startActivity(creditCardIntent);
                } else if (currentlySelectedCheckbox[0] == in_site_checkbox) {

                    Intent creditCardIntent = new Intent(PaymentMethodActivity.this, BillingActivity.class);
                    startActivity(creditCardIntent);

                } else {
                    Toast.makeText(PaymentMethodActivity.this, "Please select a payment method", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Add Click Listeners to the checkboxes to handle their selections
        checkbox_credit_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentlySelectedCheckbox[0] = checkbox_credit_card;
                checkbox_paypal.setChecked(false);
                in_site_checkbox.setChecked(false);
                pay_meth_button.setEnabled(true);

            }
        });

        checkbox_paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentlySelectedCheckbox[0] = checkbox_paypal;
                checkbox_credit_card.setChecked(false);
                in_site_checkbox.setChecked(false);
                pay_meth_button.setEnabled(true);
            }
        });

        in_site_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentlySelectedCheckbox[0] = in_site_checkbox;
                checkbox_credit_card.setChecked(false);
                checkbox_paypal.setChecked(false);
                pay_meth_button.setEnabled(true);
            }
        });
    }

    public void onBackIconClick(View view) {
        finish();
    }

}