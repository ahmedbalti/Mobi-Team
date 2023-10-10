package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail extends AppCompatActivity {

    ImageView image;
    TextView mar,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Button reserver=findViewById(R.id.reserver);

        image=findViewById(R.id.image);

        mar=findViewById(R.id.marq);
        desc=findViewById(R.id.descr);

        Intent intent=getIntent();
        String marque=intent.getStringExtra("marque");

        String descr=intent.getStringExtra("descr");
        int img=intent.getIntExtra("image",0);

        mar.setText(marque);

        desc.setText(descr);

        image.setImageResource(img);

// bouton reservation
        reserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Detail.this, Webactvity.class);
                startActivity(intent);
            }
        });

    }
}