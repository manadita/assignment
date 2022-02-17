package edu.neu.madcourse.numad21fa_yuzou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;

public class DisplayLocationActivity extends AppCompatActivity {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private double altitude;
    private double longitude;
    private FusedLocationProviderClient flpc;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_location);
        requestLocationPermission();
        getLocation();
        TextView txtaltitude = findViewById(R.id.txt_latitudenum);
        TextView txtlongitude = findViewById(R.id.txt_longitudenum);
        txtaltitude.setText(String.format("%f", altitude));
        txtlongitude.setText(String.format("%f", longitude));
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                0);
    }

    private void getLocation() {
        flpc = LocationServices.getFusedLocationProviderClient(this);
        altitude = 2.33;
        longitude = 4.22;
        requestLocationPermission();
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            flpc.getLastLocation().addOnSuccessListener(this,
                    new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    altitude = 1.55;
                    longitude = 7.66;

                    if (location != null) {
                        // get latitude and longitude.
                        //altitude = location.getAltitude();
                        //longitude = location.getLongitude();
                    } else {
                        Snackbar snackbar = Snackbar.make(coordinatorLayout,
                                "No location found. Check your signals and try again.",
                                Snackbar.LENGTH_INDEFINITE);
                        snackbar.setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                    }
                }
            });
        }

    }
}