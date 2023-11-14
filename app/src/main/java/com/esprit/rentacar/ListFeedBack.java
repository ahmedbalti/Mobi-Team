package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.esprit.rentacar.database.AppDataBase;
import com.esprit.rentacar.entity.FeedBackModel;

import java.util.List;

public class ListFeedBack extends AppCompatActivity {
    Button btnBack, btn_addOne;
    private RecyclerView recyclerView;
    private FeedBackListAdapter feedBackListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_feed_back);

        btnBack = findViewById(R.id.BackF);
        btn_addOne =findViewById(R.id.btn_addOne);


        recyclerView = findViewById(R.id.ListViewF);

        AppDataBase appDatabase = AppDataBase.getAppDatabase(this);
        feedBackListAdapter = new FeedBackListAdapter(appDatabase.feedBackDao());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(feedBackListAdapter);

        getAllFeedBacks();


        btn_addOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListFeedBack.this,FeedBackActivity.class);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListFeedBack.this, HomeFeedBackActivity.class));
            }
        });

        // Inside the bind method of TransactionViewHolder




    }



    private void getAllFeedBacks() {
        AppDataBase appDatabase = AppDataBase.getAppDatabase(this);
        List<FeedBackModel> feedBacks = appDatabase.feedBackDao().getAllFeedBacks();
        feedBackListAdapter.setFeedBacks(feedBacks);
    }
}