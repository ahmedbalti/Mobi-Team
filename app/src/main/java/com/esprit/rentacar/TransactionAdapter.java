package com.esprit.rentacar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esprit.rentacar.entity.PaymentDetails;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private List<PaymentDetails> transactions = new ArrayList<>();

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

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTransactionTitle = itemView.findViewById(R.id.tv_transaction_title);
            tvTransactionDate = itemView.findViewById(R.id.tv_transaction_date);
            transactionAmount = itemView.findViewById(R.id.transaction_amount);
        }

        public void bind(PaymentDetails transaction) {
            tvTransactionTitle.setText(transaction.getVehicleName()); // Modifier en fonction de vos données
            tvTransactionDate.setText(transaction.getDate());
            transactionAmount.setText("$" + transaction.getPrice());
        }
    }
}
