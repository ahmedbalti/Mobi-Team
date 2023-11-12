

package com.esprit.rentacar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.esprit.rentacar.Model.Voiture;
import java.util.List;

public class VoitureAdapter extends RecyclerView.Adapter<VoitureAdapter.ViewHolder> {

    private List<Voiture> listeVoitures;

    public VoitureAdapter(List<Voiture> listeVoitures) {
        this.listeVoitures = listeVoitures;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voiture, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Voiture voiture = listeVoitures.get(position);
        holder.textViewNom.setText(voiture.getNom());
        holder.textViewModele.setText(voiture.getModele());
    }

    @Override
    public int getItemCount() {
        return listeVoitures.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNom;
        TextView textViewModele;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNom = itemView.findViewById(R.id.textViewNom);
            textViewModele = itemView.findViewById(R.id.textViewModele);
        }
    }
}
