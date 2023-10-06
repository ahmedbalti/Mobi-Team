package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstPage extends AppCompatActivity {

    private Button button1;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {openSignUp();}
        });
        button1 = (Button) findViewById(R.id.signup);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {openSignIn();}
        });
    }

    private void openSignIn() {

        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openSignUp(){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);

    }
}