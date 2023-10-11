package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button v ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v = findViewById(R.id.b) ;

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent creditCardIntent = new Intent(MainActivity.this, CheckoutInfoActivity.class);
                startActivity(creditCardIntent);
            }
        });
    }
}