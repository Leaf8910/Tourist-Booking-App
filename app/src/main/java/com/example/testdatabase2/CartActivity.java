package com.example.testdatabase2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class CartActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private LinearLayout linearLayout;
    private TextView enterCouponCodeTextView;
    private EditText couponCodeEditText;
    private Button submitButton;
    private TextView totalAmountTextView;
    private Button checkoutButton;
    private TextView textView;

    private CardView cardView;
    private LinearLayout servicesLayout;
    private TextView servicesLinkTextView;
    private TextView cartLinkTextView;
    private TextView aboutLinkTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        TextView aboutLink = findViewById(R.id.about_link);
        TextView servicesLink = findViewById(R.id.services_link);
        TextView cartLink = findViewById(R.id.cart_link);
        ScrollView destinationScrollView = findViewById(R.id.destinationScrollView);
        TextView testAmount = findViewById(R.id.testAmount);
//        TextView destinationTextView = findViewById(R.id.destinationTextView);
        LinearLayout containerLayout = findViewById(R.id.containerLayout);
        containerLayout.removeAllViews();
//        TextView childTextView = new TextView(this);
//        childTextView.setText("New Child TextView");
//        containerLayout.addView(childTextView);


        Intent intent = getIntent();
        String selectedPlace = intent.getStringExtra("selectedPlace");
        double selectedPrice = intent.getDoubleExtra("selectedPrice", 0.0);
        String selectedDate = intent.getStringExtra(TourActivity1.EXTRA_SELECTED_DATE);

        testAmount.setText(String.valueOf(selectedPrice));
        TextView item1 = new TextView(this);
        TextView item2= new TextView(this);
        item1.setText(selectedPlace + " - $" + selectedPrice);
        containerLayout.addView(item1);


//        use the selectedDate to output in a textview called dateOutput
        /*testAmount.addView(selectedPrice);*/
//        containerLayout.addView(childTextView);


//        containerLayout.addView(destinationTextView);
//        destinationScrollView.addView(containerLayout);
        // Check if the destinationTextView already has a parent
//        ViewGroup parentView = (ViewGroup) destinationTextView.getParent();
//        if (parentView != null) {
//            parentView.removeView(destinationTextView);
//        }
        ViewGroup parentView = (ViewGroup) containerLayout.getParent();
        if (parentView != null) {
            parentView.removeView(containerLayout);
        }
        destinationScrollView.removeAllViews();
        destinationScrollView.addView(containerLayout);




        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        servicesLink.setOnClickListener(v -> {
            // Start SecondActivity (refresh the activity)
            Intent secondActivityIntent = new Intent(CartActivity.this, SecondActivity.class);
            startActivity(secondActivityIntent);
        });

        cartLink.setOnClickListener(v -> {
            // Start CartActivity (refresh the activity)
            Intent cartActivityIntent = new Intent(CartActivity.this, CartActivity.class);
            startActivity(cartActivityIntent);
        });

        aboutLink.setOnClickListener(v -> {
            Intent aboutActivityIntent = new Intent(CartActivity.this, AboutActivity.class);
            startActivity(aboutActivityIntent);
        });


//        scrollView = findViewById(R.id.scrollView);
////        linearLayout = findViewById(R.id.linearLayout);
//        enterCouponCodeTextView = findViewById(R.id.textView2);
//        couponCodeEditText = findViewById(R.id.editTextText);
//        submitButton = findViewById(R.id.button);
//        totalAmountTextView = findViewById(R.id.textView13);
//        checkoutButton = findViewById(R.id.button2);
//        textView = findViewById(R.id.textView3);
//
//        cardView = findViewById(R.id.cardView);
////        servicesLayout = findViewById(R.id.services_layout);
//        servicesLinkTextView = findViewById(R.id.services_link);
//        cartLinkTextView = findViewById(R.id.cart_link);
//        aboutLinkTextView = findViewById(R.id.about_link);
//
//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle submit button click event
//            }
//        });
//
//        checkoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle checkout button click event
//            }
//        });
//
//        servicesLinkTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle services link click event
//            }
//        });
//
//        cartLinkTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle cart link click event
//            }
//        });
//
//        aboutLinkTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle about link click event
//            }
//        });
    }
}
