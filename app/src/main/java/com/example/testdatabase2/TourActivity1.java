package com.example.testdatabase2;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.view.View;
public class TourActivity1 extends AppCompatActivity {
    private TextView totalTextView;
    private Connection database;
    private int selectedValue = 0;
    private Calendar calendar;
    private TextView textViewSelectedDate;
    public static final String EXTRA_SELECTED_DATE = "selectedDate";

    @SuppressLint({"SetTextI18n", "StaticFieldLeak"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tours);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        TextView servicesLink = findViewById(R.id.services_link);
        TextView aboutLink = findViewById(R.id.about_link);
        Spinner numberSpinner = findViewById(R.id.number_spinner);
        Button buttonDatePicker = findViewById(R.id.buttonDatePicker);
        calendar = Calendar.getInstance();
        textViewSelectedDate = findViewById(R.id.textViewSelectedDate);
        TextView cartLink = findViewById(R.id.cart_link);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.number_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberSpinner.setAdapter(adapter);
        //Grabbing the scrollView
        ScrollView scrollView = findViewById(R.id.tourScroll);
        totalTextView = findViewById(R.id.total);
        //The Submit Button
        Button button = findViewById(R.id.addToCart);
        button.setOnClickListener(this::addToCartClicked);


        numberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedText = adapterView.getItemAtPosition(position).toString();
                selectedValue = Integer.parseInt(selectedText);

                // Retrieve the selected place from the spinner
                String selectedPlace = adapterView.getSelectedItem().toString();

                // Retrieve the price for the selected place in the background
                new RetrievePriceTask().execute(selectedPlace);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedValue = 1;
                totalTextView.setText("0.0");
            }
        });

        new DatabaseConnection() {
            @Override
            protected void onPostExecute(Connection connection) {
                if (connection != null) {
                    database = connection;
                    populateScrollView();
                }
            }
        }.execute();

        servicesLink.setOnClickListener(v -> {
            Intent intent = new Intent(TourActivity1.this, TourActivity1.class);
            startActivity(intent);
        });

        aboutLink.setOnClickListener(v -> {
            Intent intent = new Intent(TourActivity1.this, AboutActivity.class);
            startActivity(intent);
        });

        cartLink.setOnClickListener(v -> {
            // Start SecondActivity (refresh the activity)
            Intent intent = new Intent(TourActivity1.this, CartActivity.class);
            startActivity(intent);
        });


        buttonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current date values from the calendar
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Create a date picker dialog and set its initial date to the current date
                DatePickerDialog datePickerDialog = new DatePickerDialog(TourActivity1.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update the calendar with the selected date
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        // Format the selected date as desired
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        String selectedDate = dateFormat.format(calendar.getTime());

                        // Display the selected date in the TextView
                        textViewSelectedDate.setText(selectedDate);

                        // Create the intent and include the selected date as an extra
                        Intent intent = new Intent(TourActivity1.this, CartActivity.class);
                        intent.putExtra("selectedDate", selectedDate);
//                        startActivity(intent);
                    }
                }, year, month, dayOfMonth);

                // Show the date picker dialog
                datePickerDialog.show();
            }
        });

    }

//    Submit button function when clicked
    public void addToCartClicked(View view) {

        String selectedData = totalTextView.getText().toString();
        Intent intent = new Intent(this, CartActivity.class);
        intent.putExtra("selectedData", selectedData);
        intent.putExtra(EXTRA_SELECTED_DATE, textViewSelectedDate.getText().toString());

        startActivity(intent);
    }

    public static class DatabaseConnection extends AsyncTask<Void, Void, Connection> {

        @Override
        protected Connection doInBackground(Void... voids) {
            Connection connection = null;
            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.100.20:1433/seolstay", "sa", "1234");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }

    @SuppressLint({"SetTextI18n", "StaticFieldLeak"})
    private void populateScrollView() {
        new AsyncTask<Void, Void, List<TourData>>() {
            @Override
            protected List<TourData> doInBackground(Void... voids) {
                List<TourData> tourDataList = new ArrayList<>();

                String query = "SELECT places, prices FROM tours";
                try (Statement statement = database.createStatement();
                     ResultSet resultSet = statement.executeQuery(query)) {

                    while (resultSet.next()) {
                        String place = resultSet.getString("places");
                        double price = resultSet.getDouble("prices");

                        TourData tourData = new TourData(place, price);
                        tourDataList.add(tourData);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return tourDataList;
            }

            @SuppressLint("SetTextI18n")
            @Override
            protected void onPostExecute(List<TourData> tourDataList) {
                LinearLayout scrollLayout = findViewById(R.id.scrollLayout);

                for (TourData tourData : tourDataList) {
                    TextView item = new TextView(TourActivity1.this);
                    item.setText(tourData.getPlace() + " - $" + tourData.getPrice());
                    item.setOnClickListener(view -> {
                        double price = tourData.getPrice();
                        String place = tourData.getPlace();
                        // Retrieve the price for the selected place in the background
                        Intent intent = new Intent(TourActivity1.this, CartActivity.class);
                        intent.putExtra("selectedPlace", place);
                        intent.putExtra("selectedPrice", price);
                        startActivity(intent);
                        new RetrievePriceTask().execute(place);
                    });

                    scrollLayout.addView(item);
                }
            }
        }.execute();
    }

    private class RetrievePriceTask extends AsyncTask<String, Void, Double> {

        @Override
        protected Double doInBackground(String... strings) {
            String place = strings[0];
            double price = 0;
            if (database != null) {
                String query = "SELECT prices FROM tours WHERE places = '" + place + "'";
                try (Statement statement = database.createStatement();
                     ResultSet resultSet = statement.executeQuery(query)) {
                    if (resultSet.next()) {
                        price = resultSet.getDouble("prices");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return price;
        }

        @Override
        protected void onPostExecute(Double price) {
            // Calculate the new total value based on the selected Spinner value and retrieved price
            double total = price * selectedValue;
            totalTextView.setText(String.valueOf(total));
        }
    }

    private static class TourData {
        private String place;
        private double price;

        public TourData(String place, double price) {
            this.place = place;
            this.price = price;
        }

        public String getPlace() {
            return place;
        }

        public double getPrice() {
            return price;
        }
    }
}
