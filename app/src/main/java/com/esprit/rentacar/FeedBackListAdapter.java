package com.esprit.rentacar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esprit.rentacar.dao.FeedBackDao;
import com.esprit.rentacar.entity.FeedBackModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FeedBackListAdapter extends RecyclerView.Adapter<FeedBackListAdapter.TransactionViewHolder> {

    private List<FeedBackModel> feedBacks = new ArrayList<>();
    private FeedBackDao feedBackDao;

    public FeedBackListAdapter(FeedBackDao feedBackDao) {
        this.feedBackDao = feedBackDao;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_item, parent, false);
        return new TransactionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        FeedBackModel feedBack = feedBacks.get(position);
        holder.bind(feedBack);

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    FeedBackModel transactionToDelete = feedBacks.get(position);
                    feedBackDao.deleteTransaction(transactionToDelete);
                    feedBacks.remove(position);
                    notifyItemRemoved(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedBacks.size();
    }

    public void setFeedBacks(List<FeedBackModel> transactions) {
        this.feedBacks = transactions;
        notifyDataSetChanged();
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder {

        private TextView fb_contenue,fb_date,fb_rating;
        private ImageView btn_delete,item_update;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            fb_contenue = itemView.findViewById(R.id.fb_contenue);
            fb_date = itemView.findViewById(R.id.fb_date);
            fb_rating = itemView.findViewById(R.id.fb_rating);
            btn_delete = itemView.findViewById(R.id.item_delete);
            item_update = itemView.findViewById(R.id.item_update);

        }

        public void bind(FeedBackModel feedBack) {
            fb_contenue.setText(feedBack.getContenue());

            // Check if the date string is not null or empty before formatting
            if (feedBack.getDate() != null && !feedBack.getDate().isEmpty()) {
                // Parse the date string
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

                try {
                    Date date = inputFormat.parse(feedBack.getDate());
                    fb_date.setText(outputFormat.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                    // Handle parsing exception if needed
                }
            } else {
                fb_date.setText("N/A");
            }

            fb_rating.setText(String.valueOf(feedBack.getRating()));
            int connectedUserId = 2;
            if (feedBack.getUserId() == connectedUserId) {
                btn_delete.setVisibility(View.VISIBLE);
                item_update.setVisibility(View.VISIBLE);

            } else {
                btn_delete.setVisibility(View.GONE);
                item_update.setVisibility(View.GONE);
            }

            item_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), UpdateFeedBackActivity.class);
                    intent.putExtra("FEEDBACK_ID", feedBack.getId());
                    itemView.getContext().startActivity(intent);
                }
            });
        }


    }
}
