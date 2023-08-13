//package com.example.testdatabase2;
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.DatePickerDialog;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.LinearLayout;
//import android.widget.Spinner;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.Calendar;
//import java.util.Locale;
//import java.text.SimpleDateFormat;
//
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//import java.sql.Connection;
//import java.sql.Statement;
//
//public class TourActivity extends AppCompatActivity {
//    private Calendar calendar;
//    private Button buttonDatePicker;
//    private TextView textViewSelectedDate;
//    private Spinner numberSpinner;
//    private LinearLayout scrollLayout;
//    private TextView amountTotalTextView;
//    private List<TourData> tourDataList;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tours);
//
//        buttonDatePicker = findViewById(R.id.buttonDatePicker);
//        textViewSelectedDate = findViewById(R.id.textViewSelectedDate);
//        calendar = Calendar.getInstance();
//        TextView servicesLink = findViewById(R.id.services_link);
//        TextView aboutLink = findViewById(R.id.about_link);
//
//        numberSpinner = findViewById(R.id.number_spinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.number_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        numberSpinner.setAdapter(adapter);
//
//        scrollLayout = findViewById(R.id.scrollLayout);
//        amountTotalTextView = findViewById(R.id.amount_total);
//
//        servicesLink.setOnClickListener(v -> {
//            // Start SecondActivity (refresh the activity)
//            Intent intent = new Intent(TourActivity.this, TourActivity.class);
//            startActivity(intent);
//        });
//        aboutLink.setOnClickListener(v -> {
//            Intent intent = new Intent(TourActivity.this,AboutActivity.class);
//            startActivity(intent);
//        });
//
////        List<String> tourDataList = retrieveDataFromDatabase();
////
////        LinearLayout scrollLayout = findViewById(R.id.scrollLayout);
////
////        for (String tourData : tourDataList) {
////            TextView textView = new TextView(this);
////            textView.setText(tourData);
////            scrollLayout.addView(textView);
////        }
////        numberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////            @Override
////            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                String selectedNumber = parent.getItemAtPosition(position).toString();
////                int number = Integer.parseInt(selectedNumber);
////                int totalAmount = calculateTotalAmount(tourDataList, number);
////                amountTotalTextView.setText(String.valueOf(totalAmount));
////                // Handle the selected number
////            }
////
////            @Override
////            public void onNothingSelected(AdapterView<?> parent) {
//                // Handle the case when no number is selected
////            }
////        });
//
//        buttonDatePicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get the current date values from the calendar
//                int year = calendar.get(Calendar.YEAR);
//                int month = calendar.get(Calendar.MONTH);
//                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//
//                // Create a date picker dialog and set its initial date to the current date
//                DatePickerDialog datePickerDialog = new DatePickerDialog(TourActivity.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        // Update the calendar with the selected date
//                        calendar.set(Calendar.YEAR, year);
//                        calendar.set(Calendar.MONTH, monthOfYear);
//                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//                        // Format the selected date as desired
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//                        String selectedDate = dateFormat.format(calendar.getTime());
//
//                        // Display the selected date in the TextView
//                        textViewSelectedDate.setText(selectedDate);
//                    }
//                }, year, month, dayOfMonth);
//
//                // Show the date picker dialog
//                datePickerDialog.show();
//            }
//        });
//        new DatabaseAsyncTask().execute();
//    }
//    @SuppressLint("StaticFieldLeak")
//    private class DatabaseAsyncTask extends AsyncTask<Void, Void, List<TourData>> {
//        @Override
//        protected List<TourData> doInBackground(Void... voids) {
////            return retrieveDataFromDatabase();
//            return retrievePriceFromDatabase();
////            return retrieveDataFromDatabase();
//        }
//
//        protected void onPostExecute(List<TourData> tourDataList) {
////            LinearLayout scrollLayout = findViewById(R.id.scrollLayout);
////            TextView amountTotalTextView = findViewById(R.id.amount_total);
////
////            int number = Integer.parseInt(numberSpinner.getSelectedItem().toString());
////            int totalAmount = calculateTotalAmount(tourDataList, number);
////            amountTotalTextView.setText(String.valueOf(totalAmount));
//            TourActivity.this.tourDataList = tourDataList;
//
//            numberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                    String selectedNumber = parent.getItemAtPosition(position).toString();
//                    int number = Integer.parseInt(parent.getItemAtPosition(position).toString());
//                    int totalAmount = calculateTotalAmount(number);
//                    amountTotalTextView.setText(String.valueOf(totalAmount));
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//                    // Handle the case when no number is selected
//                }
//            });
//
//            for (TourData tourData : tourDataList) {
//                TextView textView = new TextView(TourActivity.this);
//                textView.setText(tourData.getTourName());
//                textView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        float tourPrice = retrievePriceFromDatabase(tourData.getId()); // Retrieve the price from the database
//                        float tourPrice = tourData.getPrice();
//                        int totalAmount = calculateTotalAmount(Integer.parseInt(numberSpinner.getSelectedItem().toString()));
//                        // Update the total amount and display it in amount_total TextView
////                        float totalPay = totalAmount * calculateAmountForTour(tourData.getId());
//                        float totalPay = totalAmount * tourPrice; // Calculate the total pay based on the price
//                        amountTotalTextView.setText(String.valueOf(totalPay));
//                    }
//                });
//                scrollLayout.addView(textView);
//            }
//        }
//    }
//
//    private int calculateTotalAmount(int number) {
//        int totalAmount = 0;
//        if (tourDataList != null && !tourDataList.isEmpty()) {
//            TourData selectedTour = tourDataList.get(numberSpinner.getSelectedItemPosition());
//            int price = (int) selectedTour.getPrice();
//            totalAmount = price * number;
//        }
//        return totalAmount;
//    }
//
//
//
////    private int calculateAmountForTour(int id) {
////        //To Add With Spinner days
////        return id;
////    }
//
////    private List<TourData> retrieveDataFromDatabase() {
////        List<TourData> tourDataList = new ArrayList<>();
//    private List<TourData> retrievePriceFromDatabase() {
//        List<TourData> tourDataList = new ArrayList<>();
//
//        float price = 0;
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            Class.forName("net.sourceforge.jtds.jdbc.Driver");
//            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.100.20:1433/seolstay", "sa", "1234");
//            statement = connection.createStatement();
//            String sqlQuery = "SELECT prices, places FROM tours";
//            resultSet = statement.executeQuery(sqlQuery);
//
//            // Process the query results
//            while (resultSet.next()) {
////                int prices = resultSet.getInt("prices");
//                price = resultSet.getInt("prices");
//                String tourName = resultSet.getString("places");
//                tourDataList.add(new TourData((int) price, tourName));
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // Close the resources
//            try {
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//                if (statement != null) {
//                    statement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return tourDataList;
//    }
//    public class TourData {
//        private float price;
//        private String tourName;
//
//        public TourData(float price, String tourName) {
//            this.price = price;
//            this.tourName = tourName;
//        }
//
//        public float getPrice() {
//            return price;
//        }
//
//        public String getTourName() {
//            return tourName;
//        }
//    }
//
//}
//
//
//
