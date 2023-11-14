package com.esprit.rentacar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.PaymentAuthConfig;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.Stripe;
import com.stripe.android.StripeError;
import com.stripe.android.model.ConfirmSetupIntentParams;
import com.stripe.android.model.PaymentMethod;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.view.CardMultilineWidget;

public class PaimentStripeActivity extends AppCompatActivity {
    private Stripe stripe;
    private String paymentIntentClientSecret;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiment_stripe);


        PaymentConfiguration.init(getApplicationContext(), "pk_test_51OCBGqHTLrXa2H0rDUERMecBC87QxOHwFfVA8DBKnYjYpWPUfA0EfMb3wxkCl5KJi4J34jv0hvxOEZHG2WOdniVj00knxs66IG");
    }
//        // Set up the PaymentAuthConfig
//        PaymentAuthConfig.init(new PaymentAuthConfig.Builder().set3ds2Config(new PaymentAuthConfig.Stripe3ds2Config.Builder().build()).build());
//
//        // Get a reference to the CardMultilineWidget
//        CardMultilineWidget cardMultilineWidget = findViewById(R.id.card_multiline_widget);
//
//        // Get a reference to the pay button
//        Button payButton = findViewById(R.id.btn_pay);
//
//        // Initialize the Stripe object
//        stripe = new Stripe(this, PaymentConfiguration.getInstance(this).getPublishableKey());
//
//        // Call your backend to create a SetupIntent and get the client secret
//        // For demonstration purposes, you can replace this with a sample client secret
//        paymentIntentClientSecret = "your_sample_client_secret";
//
//        // Set up click listener for the "Pay" button
//        payButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Get the payment method ID from the card input
//                PaymentMethodCreateParams params = PaymentMethodCreateParams.create(
//                        cardMultilineWidget.getCard().toPaymentMethodParamsCard(),
//                        null
//                );
//
//                stripe.createPaymentMethod(
//                        params,
//                        new ApiResultCallback<PaymentMethod>() {
//                            @Override
//                            public void onSuccess(PaymentMethod paymentMethod) {
//                                ConfirmSetupIntentParams confirmParams = ConfirmSetupIntentParams.create(
//                                        paymentMethod.id,
//                                        paymentIntentClientSecret
//                                );
//
//                                stripe.confirmSetupIntent(
//                                        confirmParams,
//                                        new ApiResultCallback<SetupIntent>() {
//                                            @Override
//                                            public void onSuccess(SetupIntent setupIntent) {
//                                                // Handle successful confirmation
//                                                handlePaymentSuccess();
//                                            }
//
//                                            @Override
//                                            public void onError(Exception e) {
//                                                // Handle confirmation error
//                                                handlePaymentError(null);
//                                            }
//                                        }
//                                );
//                            }
//
//                            @Override
//                            public void onError(Exception e) {
//                                // Handle payment method creation failure
//                                handlePaymentError(null);
//                            }
//                        }
//                );
//            }
//        });
//    }
//
//    private void handlePaymentSuccess() {
//        // Handle successful payment, e.g., show a success message
//        Toast.makeText(this, "Payment succeeded!", Toast.LENGTH_SHORT).show();
//    }
//
//    private void handlePaymentError(@Nullable SetupIntent result) {
//        // Handle payment error, e.g., show an error message
//    }

}