package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Voitures extends AppCompatActivity {

    String[] mv1={"Renault Clio","Dacia Logan","Ford fiesta","Kia Picanto","Suzuki Swift","Ford Focus"};
    String[] mv2={"Mercedes sprinter","Ford Transit","Fiat Ducato","Hyndai H1","Mitsubishi","Renault Master"};
    String[] mv3={"RangeRover Evo","Toyota Prado","Touerg","Jeep","Hyndai Santafe","Mitsubishi Pajero"};
    String[] mv4={"Mercedes Classe s","Maserati","Buggati","Porshe Cayenne","Lamberguini","Lexus"};

    String[] descr1={"Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul"};
    String[] descr2={"Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul"};
    String[] descr3={"Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul"};
    String[] descr4={"Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul","Climatisé, ABS, Radar de recul"};

    int[]  imgv1={R.drawable.clio,R.drawable.dacia,R.drawable.fiesta,R.drawable.picanto,R.drawable.swift,R.drawable.focus};
    int[]  imgv2={R.drawable.printer,R.drawable.transit,R.drawable.ducato,R.drawable.hyndai,R.drawable.mitsubishi,R.drawable.master};
    int[]  imgv3={R.drawable.range_rover,R.drawable.prado,R.drawable.touerg,R.drawable.jeep,R.drawable.hyundai,R.drawable.pajero};
    int[]  imgv4={R.drawable.range_rover,R.drawable.prado,R.drawable.touerg,R.drawable.jeep,R.drawable.hyundai,R.drawable.pajero};


    TextView categ;
    CardView car1,car2,car3,car4,car5,car6;
    ImageView imv1,imv2,imv3,imv4,imv5,imv6;
    TextView mar1,mar2,mar3,mar4,mar5,mar6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voitures);

        car1=findViewById(R.id.card1);
        car2=findViewById(R.id.card2);
        car3=findViewById(R.id.card3);
        car4=findViewById(R.id.card4);
        car5=findViewById(R.id.card5);
        car6=findViewById(R.id.card6);


        imv1=findViewById(R.id.voit1);
        imv2=findViewById(R.id.voit2);
        imv3=findViewById(R.id.voit3);
        imv4=findViewById(R.id.voit4);
        imv5=findViewById(R.id.voit5);
        imv6=findViewById(R.id.voit6);

        mar1=findViewById(R.id.marq1);
        mar2=findViewById(R.id.marq2);
        mar3=findViewById(R.id.marq3);
        mar4=findViewById(R.id.marq4);
        mar5=findViewById(R.id.marq5);
        mar6=findViewById(R.id.marq6);

        categ=findViewById(R.id.categorie);

        Intent intent=getIntent();

        String c=intent.getStringExtra("categorie");

        categ.setText(c);

        if(c.equals("Voiture")==true)
        {

            afficher(imgv1,mv1);
        }
        if(c.equals("Minibus")==true)
        {

            afficher(imgv2,mv2);
        }
        if(c.equals("4x4")==true)
        {

            afficher(imgv3,mv3);
        }

        if(c.equals("Luxury")==true)
        {
            afficher(imgv4,mv4);
        }

        car1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (c){
                    case "Voiture": afficherdetail(mv1, descr1, imgv1, 0);break;
                    case "Minibus" : afficherdetail(mv2, descr2, imgv2, 0);break;
                    case "4x4": afficherdetail(mv3, descr3, imgv3, 0);break;
                    case "Luxury" : afficherdetail(mv4, descr4, imgv4, 0);break;
                }

            }
        });
        car2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (c){
                    case "Voiture": afficherdetail(mv1, descr1, imgv1, 1);break;
                    case "Minibus" : afficherdetail(mv2, descr2, imgv2, 1);break;
                    case "4x4": afficherdetail(mv3, descr3, imgv3, 1);break;
                    case "Luxury" : afficherdetail(mv4, descr4, imgv4, 1);break;
                }
            }
        });


        car3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (c){
                    case "Voiture": afficherdetail(mv1, descr1, imgv1, 2);break;
                    case "Minibus" : afficherdetail(mv2, descr2, imgv2, 2);break;
                    case "4x4": afficherdetail(mv3, descr3, imgv3, 2);break;
                    case "Luxury" : afficherdetail(mv4, descr4, imgv4, 2);break;
                }
            }
        });
        car4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (c){
                    case "Voiture": afficherdetail(mv1, descr1, imgv1, 3);break;
                    case "Minibus" : afficherdetail(mv2, descr2, imgv2, 3);break;
                    case "4x4": afficherdetail(mv3, descr3, imgv3, 3);break;
                    case "Luxury" : afficherdetail(mv4, descr4, imgv4, 3);break;
                }
            }
        });
        car5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (c){
                    case "Voiture": afficherdetail(mv1, descr1, imgv1, 4);break;
                    case "Minibus" : afficherdetail(mv2, descr2, imgv2, 4);break;
                    case "4x4": afficherdetail(mv3, descr3, imgv3, 4);break;
                    case "Luxury" : afficherdetail(mv4, descr4, imgv4, 4);break;
                }
            }
        });
        car6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (c){
                    case "Voiture": afficherdetail(mv1, descr1, imgv1, 5);break;
                    case "Minibus" : afficherdetail(mv2, descr2, imgv2, 5);break;
                    case "4x4": afficherdetail(mv3, descr3, imgv3, 5);break;
                    case "Luxury" : afficherdetail(mv4, descr4, imgv4, 5);break;
                }
            }
        });


    }

    public void afficher(int img[],String marquevoitures[]) {
        int i = 0;
        imv1.setImageResource(img[i]);
        imv2.setImageResource(img[i+1]);
        imv3.setImageResource(img[i+2]);
        imv4.setImageResource(img[i+3]);
        imv5.setImageResource(img[i+4]);
        imv6.setImageResource(img[i+5]);

        mar1.setText(marquevoitures[i]);
        mar2.setText(marquevoitures[i+1]);
        mar3.setText(marquevoitures[i+2]);
        mar4.setText(marquevoitures[i+3]);
        mar5.setText(marquevoitures[i+4]);
        mar6.setText(marquevoitures[i+5]);
    }

    public void afficherdetail(String[] mv, String[] desc, int[] img,int i)
    {

        Intent intent=new Intent(Voitures.this,Detail.class);
        intent.putExtra("marque",mv[i]);
        intent.putExtra("descr",desc[i]);
        intent.putExtra("image",img[i]);
        startActivity(intent);
    }


}