package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.Calendar;

public class SendEmailFeedBackActivity extends AppCompatActivity {

    Button CancelBtn,btnSend ;
    EditText EditTextFeedBack ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email_feed_back);

        CancelBtn = findViewById(R.id.Cancel) ;
        btnSend = findViewById(R.id.btnSend);
        EditTextFeedBack = findViewById(R.id.EditTextFeedBack);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(SendEmailFeedBackActivity.this,
                    android.Manifest.permission.POST_NOTIFICATIONS) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SendEmailFeedBackActivity.this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }

        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feedBack = new Intent(SendEmailFeedBackActivity.this, SendEmailFeedBackActivity.class);
                startActivity(feedBack);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendThankYouEmail("fattouchnermin2018@gmail.com",EditTextFeedBack.getText().toString(),Calendar.getInstance().getTime().toString()) ;
                Toast.makeText(SendEmailFeedBackActivity.this, "Thank you for your feedBack", Toast.LENGTH_SHORT).show();
                MakeNotification();
                Intent feedBack = new Intent(SendEmailFeedBackActivity.this, HomeFeedBackActivity.class);
                startActivity(feedBack);
            }
        });

    }

    private void sendThankYouEmail(String recipientEmail, String feedbackContent, String feedbackDate) {
        // to user
        String subjectUser = "Thank You for Your Feedback";
        String messageBodyUser = "Dear user,\n\nThank you for providing your feedback.\n\n" +
                "Your feedback:\n" + feedbackContent +
                "\nwas received on " + feedbackDate +
                ". We appreciate your input!\n\nBest regards,\nThe Rent-a-Car Team";

        // to admin
        String subjectAdmin = "New Feedback Received";
        String messageBodyAdmin = "Dear admin,\n\nA new feedback has been received.\n\n" +
                "Feedback content:\n" + feedbackContent +
                "\nReceived on: " + feedbackDate +
                ".\n\nBest regards,\nThe Rent-a-Car Team";

        // Send emails
        new SendMail("nermin.fattouch@esprit.tn", subjectAdmin, messageBodyAdmin).execute();
        new SendMail(recipientEmail, subjectUser, messageBodyUser).execute();
    }

    public void MakeNotification() {
        String chanelID = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext(), chanelID);
        builder.setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle("FeedBack")
                .setContentText("Email sent successfully")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(getApplicationContext(), NotificationFeedBackEmail.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("data", "your email has been sent successfully thank you for your feedback and support");
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    notificationManager.getNotificationChannel(chanelID);
            if (notificationChannel == null) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(chanelID, "Some description", importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        notificationManager.notify(0, builder.build());
    }

}