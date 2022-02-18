package edu.neu.madcourse.numad21fa_yuzou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;


public class DisplayLocationActivity extends AppCompatActivity {
    private TextView txtlatitude;
    private TextView txtlongitude;
    private double latitude;
    private double longitude;
    private FusedLocationProviderClient flpc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_location);
        txtlatitude = findViewById(R.id.txt_latitudenum);
        txtlongitude = findViewById(R.id.txt_longitudenum);

        flpc = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // reuqest for permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    0);

        } else {
            // already permission granted
        }

        flpc.getLastLocation().addOnSuccessListener(this,location -> {
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                txtlatitude.setText(String.valueOf(latitude));
                txtlongitude.setText(String.valueOf(longitude));
            }
        });

    }

}
