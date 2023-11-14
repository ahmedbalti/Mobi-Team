package com.esprit.rentacar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.esprit.rentacar.Database.DBHelper;
import com.esprit.rentacar.Model.Voiture;

import java.util.ArrayList;
import java.util.List;

public class ListeVoituresActivity extends AppCompatActivity {

    private ListView listViewVoitures;
    private VoitureAdapter adapter;
    private List<Voiture> listeVoitures;
    private DBHelper dbHelper;
    private SearchView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_voitures);

        dbHelper = new DBHelper(this);
        listViewVoitures = findViewById(R.id.listViewVoitures);
        searchView = findViewById(R.id.searchView);

        // Chargez la liste des voitures depuis la base de données
        listeVoitures = dbHelper.getListeVoitures();

        // Initialisez l'adaptateur personnalisé pour la ListView
        adapter = new VoitureAdapter(this, listeVoitures);
        listViewVoitures.setAdapter(adapter);

        // Ajoutez un écouteur de recherche pour filtrer la liste en fonction de la recherche
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filtrez la liste en fonction du texte de recherche
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    // Adapter personnalisé pour gérer l'affichage des éléments de la liste
    private class VoitureAdapter extends ArrayAdapter<Voiture> {

        private List<Voiture> voitureListFull;

        public VoitureAdapter(AppCompatActivity context, List<Voiture> voitures) {
            super(context, 0, voitures);
            voitureListFull = new ArrayList<>(voitures);
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
                    remove(voitureCourante);
                    notifyDataSetChanged();
                }
            });

            return listItemView;
        }

        @Override
        public android.widget.Filter getFilter() {
            return voitureFilter;
        }

        private android.widget.Filter voitureFilter = new android.widget.Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Voiture> filteredList = new ArrayList<>();

                if (TextUtils.isEmpty(constraint)) {
                    filteredList.addAll(voitureListFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (Voiture voiture : voitureListFull) {
                        if (voiture.getNom().toLowerCase().contains(filterPattern)) {
                            filteredList.add(voiture);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clear();
                addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
