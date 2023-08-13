package com.example.testdatabase2;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        TextView textViewUsername = findViewById(R.id.textViewUsername);
        // Retrieve the username from the previous activity or database query result
        TextView cityToursButton = findViewById(R.id.citytours);
        TextView servicesLink = findViewById(R.id.services_link);
        TextView cartLink = findViewById(R.id.cart_link);
        TextView aboutLink = findViewById(R.id.about_link);
        // Display the username in the TextView
        String username = getIntent().getStringExtra("username");
        textViewUsername.setText(username);


        // Set onClick listener for the city tours button
        cityToursButton.setOnClickListener(v -> {
            // Start the TourActivity
            Intent intent = new Intent(SecondActivity.this, TourActivity1.class);
            startActivity(intent);
        });
        servicesLink.setOnClickListener(v -> {
            // Start SecondActivity (refresh the activity)
            Intent intent = new Intent(SecondActivity.this, SecondActivity.class);
            startActivity(intent);
        });

        cartLink.setOnClickListener(v -> {
            // Start SecondActivity (refresh the activity)
            Intent intent = new Intent(SecondActivity.this, CartActivity.class);
            startActivity(intent);
        });
        aboutLink.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this,AboutActivity.class);
            startActivity(intent);
        });



    }









}

//        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
////        bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
//        bottomNavigationView.setSelectedItemId(R.id.homeFragment);
//
//
//
////
//




//        Button buttonAction1 = findViewById(R.id.Services);
//        Button buttonAction2 = findViewById(R.id.Cart);
//        Button buttonAction3 = findViewById(R.id.About);

//        buttonAction1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle button action 1
//            }
//        });
//
//        buttonAction2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle button action 2
//            }
//        });
//
//        buttonAction3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle button action 3
//            }
//        });
//    }
//}




//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.activity_second, container, false);
//
//        Button buttonAction1 = view.findViewById(R.id.Services);
//        Button buttonAction2 = view.findViewById(R.id.Cart);
//        Button buttonAction3 = view.findViewById(R.id.About);
//        // Set click listeners for the buttons
//        buttonAction1.setOnClickListener((View.OnClickListener) this);
//        buttonAction2.setOnClickListener((View.OnClickListener) this);
//        buttonAction3.setOnClickListener((View.OnClickListener) this);
//
//        return view;
//    }


//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.Services:
//                // Handle button action 1
//                break;
//            case R.id.Cart:
//                // Handle button action 2
//                break;
//            case R.id.About:
//                // Handle button action 2
//                break;
//        }
//    }
//}





