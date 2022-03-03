package edu.neu.madcourse.numad21fa_yuzou;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class AboutMeInfo extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me_info);

        TextView name = findViewById(R.id.txt_aboutme_name);
        TextView email = findViewById(R.id.txt_aboutme_email);

        name.setText("Name: Mana Yu Zou");
        email.setText("Email: zou.yu1@northeastern.edu");

    }

}