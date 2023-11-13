package com.esprit.rentacar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CheckoutInfoActivity extends AppCompatActivity {

    Button proceed_button;
    final int permission_request = 212;
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
                if (android.os.Build.VERSION.SDK_INT >= 23) {
                    String permission = Manifest.permission.INTERNET;
                    if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(CheckoutInfoActivity.this, new String[]{android.Manifest.permission.INTERNET}, permission_request);
                    } else {
                        startActivity(paymentMethodIntent);
                    }
                }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case permission_request: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("DFFF","dd");

                } else {
                    boolean somePermissionsForeverDenied = false;
                    for(String permission: permissions){
                        if(ActivityCompat.shouldShowRequestPermissionRationale(this, permission)){
                            //denied
                            Log.e("denied", permission);
                        }else{
                            if(ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED){
                                //allowed
                                Log.e("allowed", permission);
                            } else{
                                //set to never ask again
                                Log.e("set to never ask again", permission);
                                somePermissionsForeverDenied = true;
                            }
                        }
                    }
                    if(somePermissionsForeverDenied){
                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                        alertDialogBuilder.setTitle("Permissions Required")
                                .setMessage("You have forcefully denied some of the required permissions " +
                                        "for this action. Please open settings, go to permissions and allow them.")
                                .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                                Uri.fromParts("package", getPackageName(), null));
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setCancelable(false)
                                .create()
                                .show();
                    }
                    Toast.makeText(getApplication(), "Permission required", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }


}