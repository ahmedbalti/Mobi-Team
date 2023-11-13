package com.esprit.rentacar;
// Importez les bibliothèques nécessaires
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.esprit.rentacar.dao.PaymentDao;
import com.esprit.rentacar.entity.PaymentDetails;
import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private List<PaymentDetails> transactions = new ArrayList<>();
    private PaymentDao paymentDao;  // Assurez-vous d'injecter le DAO

    // Constructeur avec injection du DAO
    public TransactionAdapter(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
        return new TransactionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        PaymentDetails transaction = transactions.get(position);
        holder.bind(transaction);

        // Gestionnaire d'événements pour le bouton de suppression
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    PaymentDetails transactionToDelete = transactions.get(position);
                    // Appeler la méthode de suppression dans le DAO
                    paymentDao.deleteTransaction(transactionToDelete);
                    // Mettre à jour la liste dans l'adaptateur après la suppression
                    transactions.remove(position);
                    notifyItemRemoved(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void setTransactions(List<PaymentDetails> transactions) {
        this.transactions = transactions;
        notifyDataSetChanged();
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTransactionTitle;
        private TextView tvTransactionDate;
        private TextView transactionAmount;
        private ImageView btn_delete;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTransactionTitle = itemView.findViewById(R.id.tv_transaction_title);
            tvTransactionDate = itemView.findViewById(R.id.tv_transaction_date);
            transactionAmount = itemView.findViewById(R.id.transaction_amount);
            btn_delete = itemView.findViewById(R.id.item_delete);
        }

        public void bind(PaymentDetails transaction) {
            tvTransactionTitle.setText(transaction.getVehicleName());
            tvTransactionDate.setText(transaction.getDate());
            transactionAmount.setText("$" + transaction.getPrice());
        }
    }
}
