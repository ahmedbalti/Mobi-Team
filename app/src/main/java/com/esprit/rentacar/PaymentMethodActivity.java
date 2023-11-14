package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.esprit.rentacar.database.AppDataBase;
import com.esprit.rentacar.entity.PaymentDetails;

public class PaymentMethodActivity extends AppCompatActivity {

    CheckBox checkbox_credit_card, checkbox_paypal,in_site_checkbox ;
    Button pay_meth_button ;
    private String vehicleName, vehicleMatt, date, time;
    private double price;
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

        Intent intent = getIntent();
        if (intent != null) {
            vehicleName = intent.getStringExtra("vehicleName");
            vehicleMatt = intent.getStringExtra("vehicleMatt");
            date = intent.getStringExtra("date");
            time = intent.getStringExtra("time");
            price = intent.getDoubleExtra("price", 0.0);
        }

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
                    PaymentDetails savedPaymentDetails = savePaymentInfoToDatabase();
                    Intent checkoutInfoIntent = new Intent(PaymentMethodActivity.this, SuccessfulCheckoutActivity.class);

                    checkoutInfoIntent.putExtra("transactionId", savedPaymentDetails.getId());
                    checkoutInfoIntent.putExtra("paymentMethod", savedPaymentDetails.getPaymentMethod());
                    checkoutInfoIntent.putExtra("price", savedPaymentDetails.getPrice());

                    startActivity(checkoutInfoIntent);

                } else {
                    Toast.makeText(PaymentMethodActivity.this, "Please select a payment method", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    private void sendThankYouEmail(String recipientEmail, int transactionId, String transactionDate) {
        String subject = "Thank You for Your Transaction";
        String messageBody = "Dear user,\n\nThank you for your transaction .\n\n" +
                "Your transaction with ID " + transactionId +
                " was successfully completed on " + transactionDate +
                ".\n\nBest regards,\nThe Rent-a-Car Team";


        // Execute the email sending task in the background
        new SendMailCheckout(recipientEmail, subject, messageBody).execute();
    }

    private PaymentDetails  savePaymentInfoToDatabase() {
        AppDataBase appDatabase = AppDataBase.getAppDatabase(this);
        PaymentDetails paymentDetails = new PaymentDetails(
                vehicleName,
                vehicleMatt,
                date,
                time,
                price,
                "In-site",
                false,
                1
        );
        appDatabase.paymentDao().insertPaymentDetails(paymentDetails);

        PaymentDetails savedPaymentDetails = appDatabase.paymentDao().getLatestPaymentDetails();

        sendThankYouEmail("yousra.abid@esprit.tn", savedPaymentDetails.getId(), savedPaymentDetails.getDate());

        return savedPaymentDetails ;
    }


    public void onBackIconClick(View view) {
        finish();
    }

}