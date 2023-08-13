package com.example.testdatabase2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AboutActivity extends AppCompatActivity {
    private CardView cardView;
    private LinearLayout servicesLayout;
    private TextView servicesLinkTextView;
    private TextView cartLinkTextView;
    private TextView aboutLinkTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView aboutLink = findViewById(R.id.about_link);
        TextView servicesLink = findViewById(R.id.services_link);
        TextView cartLink = findViewById(R.id.cart_link);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        servicesLink.setOnClickListener(v -> {
            // Start SecondActivity (refresh the activity)
            Intent intent = new Intent(AboutActivity.this, SecondActivity.class);
            startActivity(intent);
        });

        cartLink.setOnClickListener(v -> {
            // Start SecondActivity (refresh the activity)
            Intent intent = new Intent(AboutActivity.this, CartActivity.class);
            startActivity(intent);
        });
        aboutLink.setOnClickListener(v -> {
            Intent intent = new Intent(AboutActivity.this,AboutActivity.class);
            startActivity(intent);
        });
}}
