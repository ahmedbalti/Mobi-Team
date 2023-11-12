package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esprit.rentacar.Database.DBHelper;
import com.esprit.rentacar.Model.Voiture;

public class AjoutVoitureActivity extends AppCompatActivity {

    private EditText editTextNom;
    private EditText editTextModele;
    private Button buttonEnregistrer;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_voiture);

        dbHelper = new DBHelper(this);

        editTextNom = findViewById(R.id.editTextNom);
        editTextModele = findViewById(R.id.editTextModele);
        buttonEnregistrer = findViewById(R.id.buttonEnregistrer);

        buttonEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enregistrerVoiture();
            }
        });
    }

    private void enregistrerVoiture() {
        String nom = editTextNom.getText().toString().trim();
        String modele = editTextModele.getText().toString().trim();

        if (!TextUtils.isEmpty(nom) && !TextUtils.isEmpty(modele)) {
            Voiture nouvelleVoiture = new Voiture();
            nouvelleVoiture.setNom(nom);
            nouvelleVoiture.setModele(modele);

            dbHelper.ajouterVoiture(nouvelleVoiture);

            // Réinitialisez les champs après l'ajout
            editTextNom.setText("");
            editTextModele.setText("");

            Toast.makeText(this, "Voiture ajoutée avec succès", Toast.LENGTH_SHORT).show();
            // Rediriger vers ListeReservationsActivity
            startActivity(new Intent(AjoutVoitureActivity.this, ListeVoituresActivity.class));
            finish(); // Optionnel: fermer l'activité actuelle si nécessaire
        } else {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
        }
    }
}
