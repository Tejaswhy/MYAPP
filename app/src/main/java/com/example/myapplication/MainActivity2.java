package com.example.myapplication;

import androidx.fragment.app.FragmentActivity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextInputEditText searchBar;
    private MaterialButton showMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // XML layout with map and search

        // Initialize views
        searchBar = findViewById(R.id.searchbar3);
        showMapButton = findViewById(R.id.showMapButton);

        // Initialize Map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Button click listener for searching a location
        showMapButton.setOnClickListener(v -> {
            String locationName = searchBar.getText().toString().trim();
            if (locationName.isEmpty()) {
                Toast.makeText(MainActivity2.this, "Enter a location", Toast.LENGTH_SHORT).show();
                return;
            }

            Geocoder geocoder = new Geocoder(MainActivity2.this, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocationName(locationName, 1);
                if (addresses != null && !addresses.isEmpty()) {
                    double lat = addresses.get(0).getLatitude();
                    double lng = addresses.get(0).getLongitude();
                    LatLng location = new LatLng(lat, lng);

                    if (mMap != null) {
                        mMap.clear();
                        mMap.addMarker(new MarkerOptions().position(location).title(locationName));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
                    }
                } else {
                    Toast.makeText(MainActivity2.this, "Location not found", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity2.this, "Error finding location", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}

