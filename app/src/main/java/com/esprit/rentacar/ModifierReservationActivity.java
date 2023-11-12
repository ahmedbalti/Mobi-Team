package com.esprit.rentacar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.esprit.rentacar.database.DatabaseHelper;
import com.esprit.rentacar.model.Reservation;

public class ModifierReservationActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText datePriseEditText, dateRemiseEditText, lieuPriseEditText, lieuRemiseEditText;
    private Button modifierReservationButton;

    private long reservationId; // L'ID de la réservation à modifier

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_reservation);

        // Initialisez le DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Initialisez les vues
        datePriseEditText = findViewById(R.id.editTextDatePrise);
        dateRemiseEditText = findViewById(R.id.editTextDateRemise);
        lieuPriseEditText = findViewById(R.id.editTextLieuPrise);
        lieuRemiseEditText = findViewById(R.id.editTextLieuRemise);
        modifierReservationButton = findViewById(R.id.buttonModifierReservation);

        // Obtenez l'ID de la réservation de l'intent
        reservationId = getIntent().getLongExtra("RESERVATION_ID", -1);

        // Chargez les détails de la réservation à modifier
        chargerDetailsReservation();

        // Ajouter un écouteur de clic au bouton de modification
        modifierReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifierReservation();
            }
        });
    }

    private void chargerDetailsReservation() {
        // Récupérez la réservation à partir de la base de données en fonction de l'ID
        Reservation reservation = databaseHelper.getReservation(reservationId);

        // Affichez les détails dans les champs d'édition
        datePriseEditText.setText(reservation.getDatePrise());
        dateRemiseEditText.setText(reservation.getDateRemise());
        lieuPriseEditText.setText(reservation.getLieuPrise());
        lieuRemiseEditText.setText(reservation.getLieuRemise());
    }

    private void modifierReservation() {
        String datePrise = datePriseEditText.getText().toString();
        String dateRemise = dateRemiseEditText.getText().toString();
        String lieuPrise = lieuPriseEditText.getText().toString();
        String lieuRemise = lieuRemiseEditText.getText().toString();

        // Vérifier si tous les champs sont remplis
        if (datePrise.isEmpty() || dateRemise.isEmpty() || lieuPrise.isEmpty() || lieuRemise.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // Créer une réservation mise à jour
        Reservation reservationMiseAJour = new Reservation(datePrise, dateRemise, lieuPrise, lieuRemise);
        reservationMiseAJour.setId(reservationId);

        // Mettez à jour la réservation dans la base de données
        if (databaseHelper.updateReservation(reservationMiseAJour)) {
            Toast.makeText(this, "Réservation modifiée avec succès", Toast.LENGTH_SHORT).show();

            // Envoyez un signal à l'activité parente pour indiquer qu'une modification a eu lieu
            setResult(RESULT_OK);
            finish(); // Fermer l'activité après la modification
        } else {
            Toast.makeText(this, "Erreur lors de la modification de la réservation", Toast.LENGTH_SHORT).show();
        }
    }
}
