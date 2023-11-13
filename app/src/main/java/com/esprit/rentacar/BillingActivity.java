package com.esprit.rentacar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.esprit.rentacar.database.AppDataBase;
import com.esprit.rentacar.entity.PaymentDetails;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import android.Manifest.* ;
import android.Manifest.permission.*;

public class BillingActivity extends AppCompatActivity {


    Button close_btn,history_btn,printButton ;
    TextView transaction_id,payment_type,amount_paid,
            fullName,address,city, country,province_territory,code_postal,mobile,email,
            car_matt, car_name;
    private AppDataBase appDataBase;
    private int EXTERNAL_STORAGE_PERMISSION_CODE = 33;
    final int permission_request = 211;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);


        close_btn = findViewById(R.id.close_btn) ;
        history_btn = findViewById(R.id.history_btn);

        //transaction
        transaction_id =findViewById(R.id.transaction_id) ;
        payment_type =findViewById(R.id.payment_type) ;
        amount_paid =findViewById(R.id.amount_paid) ;
        car_matt=findViewById(R.id.car_matt) ;
        car_name=findViewById(R.id.car_name) ;

        //user
        fullName =findViewById(R.id.fullName) ;
        address =findViewById(R.id.fullName) ;
        city =findViewById(R.id.city) ;
        country=findViewById(R.id.country) ;
        province_territory =findViewById(R.id.province_territory) ;
        code_postal =findViewById(R.id.code_postal) ;
        mobile =findViewById(R.id.mobile) ;
        email =findViewById(R.id.email) ;

        appDataBase = AppDataBase.getAppDatabase(this);


        Intent intent = getIntent();
        if (intent != null) {
            int transactionId = intent.getIntExtra("transactionId", 0);

            // Utilisez le DAO pour obtenir les détails de paiement à partir de la base de données
            PaymentDetails paymentDetails = appDataBase.paymentDao().getPaymentDetailsById(transactionId);

            // Payment Detail
            if (paymentDetails != null) {
                transaction_id.setText(String.valueOf(paymentDetails.getId()));
                payment_type.setText(paymentDetails.getPaymentMethod());
                amount_paid.setText(String.valueOf(paymentDetails.getPrice()));
                car_matt.setText(paymentDetails.getVehicleMatt());
                car_name.setText(paymentDetails.getVehicleName());

                // User detail
//                fullName.setText("Full Name: " + paymentDetails.getFullName());
//                address.setText("Address: " + paymentDetails.getAddress());
//                city.setText("City: " + paymentDetails.getCity());
//                country.setText("Country: " + paymentDetails.getCountry());
//                province_territory.setText("Province/Territory: " + paymentDetails.getProvinceTerritory());
//                code_postal.setText("Postal Code: " + paymentDetails.getCodePostal());
//                mobile.setText("Mobile: " + paymentDetails.getMobile());
//                email.setText("Email: " + paymentDetails.getEmail());
            }
        }


        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent creditCardIntent = new Intent(BillingActivity.this, MainActivity.class);
                startActivity(creditCardIntent);
            }
        });

        history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent creditCardIntent = new Intent(BillingActivity.this, TransactionHistoryActivity.class);
                startActivity(creditCardIntent);
            }
        });

        printButton = findViewById(R.id.print_btn);
        printButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= 23) {
                    // only for gingerbread and newer versions
                    String permission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
                    if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(BillingActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, permission_request);
                    } else {
                        generatePDF();
                    }
                }
            }

        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case permission_request: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Add your function here which open camera
                } else {
                    boolean somePermissionsForeverDenied = false;
                    for(String permission: permissions){
                        if(ActivityCompat.shouldShowRequestPermissionRationale(this, permission)){
                            //denied
                            Log.e("denied", permission);
                        }else{
                            if(ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED){
                                //allowed
                                Log.e("allowed", permission);
                            } else{
                                //set to never ask again
                                Log.e("set to never ask again", permission);
                                somePermissionsForeverDenied = true;
                            }
                        }
                    }
                    if(somePermissionsForeverDenied){
                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                        alertDialogBuilder.setTitle("Permissions Required")
                                .setMessage("You have forcefully denied some of the required permissions " +
                                        "for this action. Please open settings, go to permissions and allow them.")
                                .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                                Uri.fromParts("package", getPackageName(), null));
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setCancelable(false)
                                .create()
                                .show();
                    }
                    Toast.makeText(getApplication(), "Permission required", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    private void generatePDF() {
        Document document = new Document();

        try {
            String filePath = Environment.getExternalStorageDirectory() + "/BillingReceipt.pdf";

            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();

            Paragraph title = new Paragraph("Rent Car Billing Information");
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));

            Paragraph subtitle = new Paragraph("Car Informations");
            subtitle.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(subtitle);

            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Transaction ID: " + transaction_id.getText()));
            document.add(new Paragraph("Car Brand ID: " + car_name.getText()));
            document.add(new Paragraph("Licence plate : " + car_matt.getText()));
            document.add(new Paragraph("Payment Type: " + payment_type.getText()));
            document.add(new Paragraph("Amount Paid: " + amount_paid.getText()));

            document.add(new Paragraph("\n"));

            Paragraph subtitle2 = new Paragraph("Car Informations");
            subtitle2.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(subtitle2);

            document.add(new Paragraph("Full Name: " + fullName.getText()));
            document.add(new Paragraph("Address: " + address.getText()));
            document.add(new Paragraph("City: " + city.getText()));
            document.add(new Paragraph("Country: " + country.getText()));
            document.add(new Paragraph("Province/Territory: " + province_territory.getText()));
            document.add(new Paragraph("Postal Code: " + code_postal.getText()));
            document.add(new Paragraph("Mobile: " + mobile.getText()));
            document.add(new Paragraph("Email: " + email.getText()));

            document.close();

            Toast.makeText(this, "Bill saved as PDF", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackIconClick(View view) {
        finish();
    }



}