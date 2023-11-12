// Dans ModifierVoitureActivity.java
package com.esprit.rentacar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.esprit.rentacar.Database.DBHelper;
import com.esprit.rentacar.Model.Voiture;

public class ModifierVoitureActivity extends AppCompatActivity {




        private EditText editTextNom;
        private EditText editTextModele;
        private Button buttonModifier;

        private DBHelper dbHelper;
        private int voitureId;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_modifier_voiture);

            dbHelper = new DBHelper(this);

            editTextNom = findViewById(R.id.editTextNom);
            editTextModele = findViewById(R.id.editTextModele);
            buttonModifier = findViewById(R.id.buttonModifier);

            // Récupérez les données passées depuis l'intent
            Intent intent = getIntent();
            voitureId = intent.getIntExtra("id", 0);
            String nom = intent.getStringExtra("nom");
            String modele = intent.getStringExtra("modele");

            // Pré-remplissez les champs avec les données existantes
            editTextNom.setText(nom);
            editTextModele.setText(modele);

            buttonModifier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifierVoiture();
                }
            });
        }

        private void modifierVoiture() {
            String nom = editTextNom.getText().toString().trim();
            String modele = editTextModele.getText().toString().trim();

            if (!TextUtils.isEmpty(nom) && !TextUtils.isEmpty(modele)) {
                Voiture voitureModifiee = new Voiture();
                voitureModifiee.setId(voitureId);
                voitureModifiee.setNom(nom);
                voitureModifiee.setModele(modele);

                dbHelper.modifierVoiture(voitureModifiee);

                Toast.makeText(this, "Voiture modifiée avec succès", Toast.LENGTH_SHORT).show();
                finish(); // Fermez l'activité après la modification
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            }
        }
    }
