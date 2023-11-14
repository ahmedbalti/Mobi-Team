package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.esprit.rentacar.database.AppDataBase;
import com.esprit.rentacar.entity.FeedBackModel;

import java.time.LocalDateTime;
import java.util.Calendar;

=======
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

>>>>>>> 27ec3c4948ea7d7159c37a03a0003395b4416bac
public class FeedBackActivity extends AppCompatActivity {

    TextView tvFeedback;
    RatingBar rbStars;
<<<<<<< HEAD
    Button CancelBtn,btnSend ;
    EditText EditTextFeedBack ;

=======
>>>>>>> 27ec3c4948ea7d7159c37a03a0003395b4416bac

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back2);
<<<<<<< HEAD
        CancelBtn = findViewById(R.id.Cancel) ;
        btnSend = findViewById(R.id.btnSend);
        tvFeedback = findViewById(R.id.tvFeedback);
        rbStars = findViewById(R.id.rbStars);
        EditTextFeedBack = findViewById(R.id.EditTextFeedBack);


=======
        tvFeedback = findViewById(R.id.tvFeedback);
        rbStars = findViewById(R.id.rbStars);
        //getSupportActionBar().setTitle("FeddBack");
>>>>>>> 27ec3c4948ea7d7159c37a03a0003395b4416bac
        rbStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating == 0) {
                    tvFeedback.setText("Very Dissatisfied");
                } else if (rating == 1) {
                    tvFeedback.setText("Dissatisfied");
                } else if (rating == 2 || rating == 3) {
                    tvFeedback.setText("OK");
                } else if (rating == 4) {
                    tvFeedback.setText("Satisfied");
                } else if (rating == 5) {
                    tvFeedback.setText(" Very Satisfied");
                }
            }
        });
<<<<<<< HEAD
        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feedBack = new Intent(FeedBackActivity.this, FeedBackActivity.class);
                startActivity(feedBack);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFeedBack() ;
                Intent feedBack = new Intent(FeedBackActivity.this, ListFeedBack.class);
                startActivity(feedBack);
            }
        });
    }



    private void saveFeedBack() {
        AppDataBase appDatabase = AppDataBase.getAppDatabase(this);
        FeedBackModel feedBackModel = new FeedBackModel(
                rbStars.getRating(),
                EditTextFeedBack.getText().toString(),
                Calendar.getInstance().getTime().toString(),
                2
        );
        appDatabase.feedBackDao().insertFeedBack(feedBackModel);
    }


=======
    }
>>>>>>> 27ec3c4948ea7d7159c37a03a0003395b4416bac
}