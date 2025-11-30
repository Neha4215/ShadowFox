package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewError = findViewById(R.id.textViewError);

        // Set click listener for login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Basic validation
        if (username.isEmpty()) {
            showError(getString(R.string.enter_username));
            return;
        }

        if (password.isEmpty()) {
            showError(getString(R.string.enter_password));
            return;
        }

        // In a real app, you would validate credentials against a database or server
        // For this example, we'll just check for a hardcoded username/password
        if (validateCredentials(username, password)) {
            // Clear any previous error
            textViewError.setVisibility(View.GONE);
            
            // Navigate to welcome page
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
            finish(); // Close MainActivity so user can't go back to login with back button
        } else {
            showError("Invalid username or password");
        }
    }

    private boolean validateCredentials(String username, String password) {
        // Hardcoded credentials for demonstration
        // In a real app, this would involve server authentication
        return "admin".equals(username) && "password".equals(password);
    }

    private void showError(String errorMessage) {
        textViewError.setText(errorMessage);
        textViewError.setVisibility(View.VISIBLE);
    }
}