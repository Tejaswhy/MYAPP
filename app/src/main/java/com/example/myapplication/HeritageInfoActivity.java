package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HeritageInfoActivity extends AppCompatActivity {

    TextView heritageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heritage);

        heritageText = findViewById(R.id.heritageText);

        // Get place name from intent
        String place = getIntent().getStringExtra("place_name");

        // Display static information or placeholder text
        if (place != null && !place.isEmpty()) {
            heritageText.setText("Information about " + place + " will be shown here.");
        } else {
            heritageText.setText("Select a heritage place to view details.");
        }
    }
}
