package com.esprit.rentacar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.esprit.rentacar.dao.FeedBackDao;
import com.esprit.rentacar.database.AppDataBase;
import com.esprit.rentacar.entity.FeedBackModel;

public class UpdateFeedBackActivity extends AppCompatActivity {

    private int feedbackId;
    private FeedBackDao feedBackDao;

    private ImageView imageView;
    private TextView tvFeedback;
    private RatingBar rbStars;
    private EditText editTextFeedBack;
    private Button btnSend, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_feed_back);

        // Initialize views and DAO
        feedBackDao = AppDataBase.getAppDatabase(this).feedBackDao();
        imageView = findViewById(R.id.imageView);
        tvFeedback = findViewById(R.id.tvFeedback);
        rbStars = findViewById(R.id.rbStars);
        editTextFeedBack = findViewById(R.id.EditTextFeedBack);
        btnSend = findViewById(R.id.btnSend);
        btnCancel = findViewById(R.id.Cancel);

        feedbackId = getIntent().getIntExtra("FEEDBACK_ID", -1);

        FeedBackModel feedback = feedBackDao.getFeedBackDetailsById(feedbackId);

        if (feedback != null) {
            rbStars.setRating(feedback.getRating());
            editTextFeedBack.setText(feedback.getContenue());
        }

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFeedbackInDatabase();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateFeedbackInDatabase() {
        float updatedRating = rbStars.getRating();
        String updatedContent = editTextFeedBack.getText().toString();
        feedBackDao.updateRatingAndContent(feedbackId, updatedRating, updatedContent);
        finish();
    }

}