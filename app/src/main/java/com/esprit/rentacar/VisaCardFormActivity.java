package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VisaCardFormActivity extends AppCompatActivity {

    Button visa_submit_button  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visa_card_form);

        visa_submit_button = findViewById(R.id.visa_submit_button);

        visa_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent creditCardIntent = new Intent(VisaCardFormActivity.this, SuccessfulCheckoutActivity.class);
                startActivity(creditCardIntent);
            }
        });

    }

    public void onBackIconClick(View view) {
        finish();
    }
}