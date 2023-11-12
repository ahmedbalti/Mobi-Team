package com.esprit.rentacar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.esprit.rentacar.database.DatabaseHelper;
import com.esprit.rentacar.model.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ListeReservationsActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private ListView listViewReservations;
    private static final int REQUEST_CODE_MODIFIER_RESERVATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_reservations);

        // Initialisez le DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Initialisez la ListView
        listViewReservations = findViewById(R.id.listViewReservations);

        // Affichez la liste des réservations
        afficherListeReservations();

        listViewReservations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupérez l'ID de la réservation sélectionnée
                long reservationId = ((Reservation) listViewReservations.getItemAtPosition(position)).getId();

                // Redirigez vers ModifierReservationActivity avec l'ID de la réservation
                Intent intent = new Intent(ListeReservationsActivity.this, ModifierReservationActivity.class);
                intent.putExtra("RESERVATION_ID", reservationId);
                startActivityForResult(intent, REQUEST_CODE_MODIFIER_RESERVATION);
            }
        });
    }

    private void afficherListeReservations() {
        // Récupérez toutes les réservations de la base de données
        List<Reservation> listeReservations = databaseHelper.getAllReservations();

        // Utilisez un adaptateur personnalisé pour afficher la liste dans la ListView
        ArrayAdapter<Reservation> adapter = new ArrayAdapter<Reservation>(this, R.layout.list_item_reservation, R.id.textViewDetails, listeReservations) {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                // Récupérez la réservation pour cette position
                final Reservation reservation = getItem(position);

                // Affichez les détails dans le TextView
                TextView textViewDetails = view.findViewById(R.id.textViewDetails);
                textViewDetails.setText(reservation.toString());

                // Ajoutez un écouteur de clic au bouton "Modifier"
                Button buttonModifier = view.findViewById(R.id.buttonModifier);
                buttonModifier.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Redirigez vers ModifierReservationActivity avec l'ID de la réservation
                        Intent intent = new Intent(ListeReservationsActivity.this, ModifierReservationActivity.class);
                        intent.putExtra("RESERVATION_ID", reservation.getId());
                        startActivity(intent);
                    }
                });

                Button buttonSupprimer = view.findViewById(R.id.buttonSupprimer);
                buttonSupprimer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Supprimez la réservation de la base de données
                        databaseHelper.supprimerReservation(reservation.getId());

                        // Rafraîchissez la liste après la suppression
                        afficherListeReservations();
                    }
                });

                return view;
            }
        };

        listViewReservations.setAdapter(adapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_MODIFIER_RESERVATION && resultCode == RESULT_OK) {
            // Si la modification a eu lieu, rafraîchissez la liste
            afficherListeReservations();
        }
    }

}
