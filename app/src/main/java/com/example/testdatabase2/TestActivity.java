package com.example.testdatabase2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    portected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstanceState);

        test = findViewById(R.layout.activity_about);

        Button buttontest = findViewById(R.id.buttonLogin);

        Spinner spinner = findViewById(R.id.spinner);

        // Create a list of items to be shown in the Spinner
        List<String> items = new ArrayList<>();
        items.add("Skills");
        items.add("Food");
        items.add("Mechanical");
        items.add("Food2");

    })



}
