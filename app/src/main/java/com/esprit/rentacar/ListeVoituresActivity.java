package com.esprit.rentacar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.esprit.rentacar.Database.DBHelper;
import com.esprit.rentacar.Model.Voiture;

import java.util.List;

public class ListeVoituresActivity extends AppCompatActivity {

    private ListView listViewVoitures;
    private VoitureAdapter adapter;
    private List<Voiture> listeVoitures;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_voitures);

        dbHelper = new DBHelper(this);
        listViewVoitures = findViewById(R.id.listViewVoitures);

        // Chargez la liste des voitures depuis la base de données
        listeVoitures = dbHelper.getListeVoitures();

        // Initialisez l'adaptateur personnalisé pour la ListView
        adapter = new VoitureAdapter(this, listeVoitures);
        listViewVoitures.setAdapter(adapter);
    }

    // Adapter personnalisé pour gérer l'affichage des éléments de la liste
    private class VoitureAdapter extends ArrayAdapter<Voiture> {

        public VoitureAdapter(AppCompatActivity context, List<Voiture> voitures) {
            super(context, 0, voitures);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View listItemView = convertView;

            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_item_voiture, parent, false);
            }

            final Voiture voitureCourante = getItem(position);

            TextView textViewModelNom = listItemView.findViewById(R.id.textViewModelNom);
            textViewModelNom.setText("Modèle: " + voitureCourante.getModele() + " | Nom: " + voitureCourante.getNom());

            Button buttonModifier = listItemView.findViewById(R.id.buttonModifier);
            buttonModifier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Lancez l'activité de modification avec les détails de la voiture
                    Intent intent = new Intent(ListeVoituresActivity.this, ModifierVoitureActivity.class);
                    intent.putExtra("id", voitureCourante.getId());
                    intent.putExtra("nom", voitureCourante.getNom());
                    intent.putExtra("modele", voitureCourante.getModele());
                    startActivity(intent);
                }
            });

            Button buttonSupprimer = listItemView.findViewById(R.id.buttonSupprimer);
            buttonSupprimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Supprimez la voiture de la base de données et mettez à jour la liste
                    dbHelper.supprimerVoiture(voitureCourante.getId());
                    adapter.remove(voitureCourante);
                    notifyDataSetChanged();
                }
            });

            return listItemView;
        }
    }
}
