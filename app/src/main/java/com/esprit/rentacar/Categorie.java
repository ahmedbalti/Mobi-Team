package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Categorie extends AppCompatActivity {

    String[] categories={"Voiture","Minibus","4x4","Luxury"};

    int i;
    CardView voiture,minbus,catcat,lux;
    Button AjouterVoiture,  afficherVehiculesButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);

        voiture=findViewById(R.id.voiture);
        minbus=findViewById(R.id.minibus);
        catcat=findViewById(R.id.catcat);
        lux=findViewById(R.id.luxury);

        AjouterVoiture = findViewById(R.id.home);

        voiture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { afficher(0);
            }
        });

        AjouterVoiture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Categorie.this, AjoutVoitureActivity.class);
                startActivity(intent);
            }
        });


        afficherVehiculesButton = findViewById(R.id.buttonAfficherVehicules);

        afficherVehiculesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Rediriger vers ListeVehiculesActivity
                    startActivity(new Intent(Categorie.this, ListeVoituresActivity.class));
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Categorie.this, "Erreur lors du démarrage de l'activité", Toast.LENGTH_SHORT).show();
                }
            }
        });


        minbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afficher(1);
            }
        });

        catcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afficher(2);
            }
        });


        lux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afficher(3);
            }
        });

    }

    public void afficher(int i)
    {
        Intent intent=new Intent(Categorie.this,Voitures.class);
        intent.putExtra("categorie",categories[i]);
        startActivity(intent);

    }



}