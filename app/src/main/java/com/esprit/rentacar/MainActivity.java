package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.room.Room;

import com.esprit.rentacar.database.AppDataBase;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private static AppDataBase appDatabase;

    Button v ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appDatabase = AppDataBase.getAppDatabase(this);

        setContentView(R.layout.activity_main);
        v = findViewById(R.id.b) ;

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the current system date
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);


                Intent checkoutInfoIntent = new Intent(MainActivity.this, CheckoutInfoActivity.class);

                checkoutInfoIntent.putExtra("vehicleName", "Toyota Camry");
                checkoutInfoIntent.putExtra("vehicleMatt", "MT-586540");
                checkoutInfoIntent.putExtra("date", day+"/"+month+1+"/"+year);
                checkoutInfoIntent.putExtra("time", "10:00 AM - 4:00 PM");
                checkoutInfoIntent.putExtra("price", 100.00);

                startActivity(checkoutInfoIntent);
            }
        });
    }
}