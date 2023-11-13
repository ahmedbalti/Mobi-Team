package com.esprit.rentacar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.esprit.rentacar.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button btnLogin;

    ActivityMainBinding binding;
    DBHelper DB;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DB = new DBHelper(this);




            binding.btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String my_email = binding.email.getText().toString();
                    String my_password = binding.password.getText().toString();


                    if(my_email.equals("")||my_password.equals(""))
                        Toast.makeText(MainActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                    else{
                        Boolean checkCredentials = DB.checkEmailPassword(my_email, my_password);
                        if(checkCredentials == true){
                            Toast.makeText(MainActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent  = new Intent(getApplicationContext(), HomePage.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });


        }
    }
