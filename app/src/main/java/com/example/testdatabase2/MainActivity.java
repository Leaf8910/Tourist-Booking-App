package com.example.testdatabase2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                new AsyncTask<Void, Void, Boolean>() {
                    @SuppressLint("StaticFieldLeak")
                    @Override
                    protected Boolean doInBackground(Void... voids) {
                        Connection connection = null;
                        try {
                            Class.forName("net.sourceforge.jtds.jdbc.Driver");
                            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://ip:1433/seolstay", "sa", "1234");

                            String query = "SELECT * FROM login WHERE username = ? AND password = ?";
                            PreparedStatement statement = connection.prepareStatement(query);
                            statement.setString(1, username);
                            statement.setString(2, password);
                            ResultSet resultSet = statement.executeQuery();

                            if (resultSet.next()) {
                                return true; // Login successful
                            } else {
                                return false; // Invalid credentials
                            }
                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                        } finally {
                            if (connection != null) {
                                try {
                                    connection.close();t
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        return false; // Error occurred
                    }



                    @Override
                    protected void onPostExecute(Boolean loginResult) {
                        if (loginResult) {
                            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            openSecondActivity();
                        } else {
                            Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                }.execute();
            }
        });
    }

    public void openSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
