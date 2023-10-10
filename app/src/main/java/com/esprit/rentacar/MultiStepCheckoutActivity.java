package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class MultiStepCheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_step_checkout);

        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        MultiStepPagerAdapter pagerAdapter = new MultiStepPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addStep(new BlankFragment1(), "Step 1");
        pagerAdapter.addStep(new BlankFragment1(), "Step 2");
        pagerAdapter.addStep(new BlankFragment1(), "Step 3");

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        Button pay_meth_button = findViewById(R.id.pay_meth_button) ;
        pay_meth_button.setOnClickListener(e -> {
            Intent intent = new Intent(this, PaymentMethodActivity.class);
            startActivity(intent);
        });

    }
}