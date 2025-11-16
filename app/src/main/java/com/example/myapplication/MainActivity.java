package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editTextAadhaar;
    private TextInputEditText editTextPassword;
    private MaterialButton buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_2); // make sure this is your login layout file

        // Initialize views safely
        editTextAadhaar = findViewById(R.id.editTextAadhaar);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Check if any view is null (helps debug crashes)
        if (editTextAadhaar == null || editTextPassword == null || buttonRegister == null) {
            Toast.makeText(this, "View not found in layout! Check IDs.", Toast.LENGTH_LONG).show();
            return;
        }

        buttonRegister.setOnClickListener(view -> {
            String aadhaar = editTextAadhaar.getText() != null ? editTextAadhaar.getText().toString().trim() : "";
            String password = editTextPassword.getText() != null ? editTextPassword.getText().toString().trim() : "";

            if (aadhaar.equals("12345") && password.equals("admin1234")) {
                // Login successful, open MainActivity2
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            } else {
                // Show invalid credentials message
                Toast.makeText(MainActivity.this, "Invalid Aadhaar or Password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
