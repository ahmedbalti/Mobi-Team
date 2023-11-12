package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PaypalFormActivity extends AppCompatActivity {

    Button paypal_submit_button ;
    String paymentAmount = "10" ;
    //Paypal intent request code to track onActivityResult method
    public static final int PAYPAL_REQUEST_CODE = 123;


    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);
    // .se(PayPalConfig.PAYPAL_CLIENT_SECRET);


            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_paypal_form);

                Intent intent = new Intent(this, PayPalService.class);
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
                startService(intent);

                paypal_submit_button = findViewById(R.id.paypal_submit_button);
                paypal_submit_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPayment();
                    }
                });
            }

            private void getPayment() {
                paymentAmount = "10";
                PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(paymentAmount)), "USD", "Simplified Coding Fee",
                        PayPalPayment.PAYMENT_INTENT_SALE);

                Intent intent = new Intent(this, PaymentActivity.class);
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

                startActivityForResult(intent, PAYPAL_REQUEST_CODE);
            }

            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == PAYPAL_REQUEST_CODE) {
                    if (resultCode == Activity.RESULT_OK) {
                        PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                        if (confirm != null) {
                            try {
                                String paymentDetails = confirm.toJSONObject().toString(4);
                                Log.i("paymentExample", paymentDetails);

                                // Traitement supplémentaire après le paiement réussi

                                startActivity(new Intent(this, SuccessfulCheckoutActivity.class)
                                        .putExtra("PaymentDetails", paymentDetails)
                                        .putExtra("PaymentAmount", paymentAmount));
                            } catch (JSONException e) {
                                Log.e("myTag", "Une erreur est survenue lors de la conversion en JSON : ", e);
                                Toast.makeText(this, "Erreur lors du traitement du paiement.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else if (resultCode == Activity.RESULT_CANCELED) {
                        Log.i("paymentExample", "L'utilisateur a annulé le paiement.");
                    } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                        Log.i("paymentExample", "Un paiement invalide ou une configuration PayPal a été soumis. Veuillez consulter la documentation.");
                    }
                }
            }
    @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }
    public void onBackIconClick(View view) {
        finish();
    }
}