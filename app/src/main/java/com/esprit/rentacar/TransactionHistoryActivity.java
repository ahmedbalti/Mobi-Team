package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.esprit.rentacar.database.AppDataBase;
import com.esprit.rentacar.entity.PaymentDetails;

import java.util.List;

public class TransactionHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TransactionAdapter transactionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        recyclerView = findViewById(R.id.recycler_view);
        transactionAdapter = new TransactionAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(transactionAdapter);

        loadUserTransactions();

    }

    private void loadUserTransactions() {

        AppDataBase appDatabase = AppDataBase.getAppDatabase(this);
        List<PaymentDetails> userTransactions = appDatabase.paymentDao().getUserTransactions(1);
        transactionAdapter.setTransactions(userTransactions);
    }

    public void onBackIconClick(View view) {
        finish();
    }

}