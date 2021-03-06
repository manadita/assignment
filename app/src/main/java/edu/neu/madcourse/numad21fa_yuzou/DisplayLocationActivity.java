package edu.neu.madcourse.numad21fa_yuzou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.widget.TextView;
import android.location.Location;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;


public class DisplayLocationActivity extends AppCompatActivity {
    private TextView txtLatitude;
    private TextView txtLongitude;
    private double latitude;
    private double longitude;
    private FusedLocationProviderClient flpc;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_location);
        txtLatitude = findViewById(R.id.txt_latitudenum);
        txtLongitude = findViewById(R.id.txt_longitudenum);
        flpc = LocationServices.getFusedLocationProviderClient(this);
        initialLocation();
        createLocationRequest();
        getCurrentLocation();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            // access was granted when activity created.
        }
        flpc.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }

    @Override
    protected void onPause() {
        super.onPause();
        flpc.removeLocationUpdates(locationCallback);
    }

    private void initialLocation() {
        txtLatitude.setText(String.valueOf(latitude));
        txtLongitude.setText(String.valueOf(longitude));
    }

    private void createLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000); // every 10s.
        locationRequest.setFastestInterval(5000); // every 5s.
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    private void getCurrentLocation() {
        // check if app has permission to access location.
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // request for permission
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},0);

        }
        // request for current location updates.
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    Toast.makeText(
                            DisplayLocationActivity.this,
                            "No location found. Check location access permission " +
                                    "or GPS connection.",
                            Toast.LENGTH_LONG
                    ).show();
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        txtLatitude.setText(String.valueOf(latitude));
                        txtLongitude.setText(String.valueOf(longitude));
                    }
                }
            }
        };
    }
}
