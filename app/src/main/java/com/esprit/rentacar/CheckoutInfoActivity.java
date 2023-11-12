package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheckoutInfoActivity extends AppCompatActivity {

    Button proceed_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_info);

        proceed_button = findViewById(R.id.proceed_button);

        Intent intent = getIntent();
        if (intent != null) {
            String vehicleName = intent.getStringExtra("vehicleName");
            String vehicleMatt = intent.getStringExtra("vehicleMatt");
            String date = intent.getStringExtra("date");
            String time = intent.getStringExtra("time");
            double price = intent.getDoubleExtra("price", 0.0);

            setVehicleName(vehicleName);
            setVehicleMatt(vehicleMatt);
            setDate(date);
            setTime(time);
            setPrice(price);
        }

        proceed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Création de l'intent pour démarrer PaymentMethodActivity
                Intent paymentMethodIntent = new Intent(CheckoutInfoActivity.this, PaymentMethodActivity.class);

                // Ajout des informations à l'intent
                paymentMethodIntent.putExtra("vehicleName", getIntent().getStringExtra("vehicleName"));
                paymentMethodIntent.putExtra("vehicleMatt", getIntent().getStringExtra("vehicleMatt"));
                paymentMethodIntent.putExtra("date", getIntent().getStringExtra("date"));
                paymentMethodIntent.putExtra("time", getIntent().getStringExtra("time"));
                paymentMethodIntent.putExtra("price", getIntent().getDoubleExtra("price", 0.0));

                // Démarrage de l'activité suivante avec l'intent
                startActivity(paymentMethodIntent);
            }
        });
    }

    public void setVehicleName(String vehicleName) {
        TextView textViewVehicleName = findViewById(R.id.textViewVehicleName);
        textViewVehicleName.setText("Vehicle: " + vehicleName);
    }

    public void setVehicleMatt(String vehicleMatt) {
        TextView textViewVehicleName = findViewById(R.id.textViewVehicleName);
        textViewVehicleName.setText("Vehicle Matricule: " + vehicleMatt);
    }

    public void setDate(String date) {
        TextView textViewDate = findViewById(R.id.textViewDate);
        textViewDate.setText("Date: " + date);
    }

    public void setTime(String time) {
        TextView textViewTime = findViewById(R.id.textViewTime);
        textViewTime.setText("Time: " + time);
    }

    public void setPrice(double price) {
        TextView textViewPrice = findViewById(R.id.textViewPrice);
        textViewPrice.setText("Price: " + price + "DT");
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}