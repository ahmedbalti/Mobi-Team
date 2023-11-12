package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esprit.rentacar.database.DatabaseHelper;
import com.esprit.rentacar.model.Reservation;

public class AjouterReservation extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText datePriseEditText, dateRemiseEditText, lieuPriseEditText, lieuRemiseEditText;
    private Button ajouterReservationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_reservation);

        // Initialisez le DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Initialisez les vues
        datePriseEditText = findViewById(R.id.editTextDatePrise);
        dateRemiseEditText = findViewById(R.id.editTextDateRemise);
        lieuPriseEditText = findViewById(R.id.editTextLieuPrise);
        lieuRemiseEditText = findViewById(R.id.editTextLieuRemise);
        ajouterReservationButton = findViewById(R.id.buttonAjouterReservation);

        // Ajouter un écouteur de clic au bouton
        ajouterReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterReservation();
            }
        });
    }

    private void ajouterReservation() {
        String datePrise = datePriseEditText.getText().toString();
        String dateRemise = dateRemiseEditText.getText().toString();
        String lieuPrise = lieuPriseEditText.getText().toString();
        String lieuRemise = lieuRemiseEditText.getText().toString();

        // Vérifier si tous les champs sont remplis
        if (datePrise.isEmpty() || dateRemise.isEmpty() || lieuPrise.isEmpty() || lieuRemise.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // Créer une nouvelle réservation
        Reservation reservation = new Reservation(datePrise, dateRemise, lieuPrise, lieuRemise);

        // Ajouter la réservation à la base de données
        long reservationId = databaseHelper.addReservation(reservation);

        // Vérifier si l'ajout a réussi
        if (reservationId != -1) {
            Toast.makeText(this, "Réservation ajoutée avec succès, ID: " + reservationId, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erreur lors de l'ajout de la réservation", Toast.LENGTH_SHORT).show();
        }
    }
}