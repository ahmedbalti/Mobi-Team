package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CheckoutInfoActivity extends AppCompatActivity {

    Button proceed_button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_info);

        proceed_button = findViewById(R.id.proceed_button);

        proceed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent creditCardIntent = new Intent(CheckoutInfoActivity.this, PaymentMethodActivity.class);
                startActivity(creditCardIntent);
            }
        });

    }
    public void onBackIconClick(View view) {
        finish();
    }


}