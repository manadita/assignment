package edu.neu.madcourse.numad21fa_yuzou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonAboutMe = findViewById(R.id.btn_aboutme);
        Button buttonLink = findViewById(R.id.btn_link);
        Button buttonLoc = findViewById(R.id.btn_location);
        Button buttonClicky = findViewById(R.id.btn_clicky);
        Button buttonWeb = findViewById(R.id.btn_webserver);

        buttonAboutMe.setOnClickListener(new MyClickListener());
        buttonLink.setOnClickListener(new MyClickListener());
        buttonLoc.setOnClickListener((new MyClickListener()));
        buttonClicky.setOnClickListener(new MyClickListener());
        buttonWeb.setOnClickListener(new MyClickListener());
    }

    class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_aboutme:
                    intent = new Intent(MainActivity.this, AboutMeInfo.class);
                    startActivity(intent);
                    break;
                case R.id.btn_clicky:
                    intent = new Intent(MainActivity.this, ClickyClickyActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_link:
                    intent = new Intent(MainActivity.this,
                            RecyclerViewActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_location:
                    intent = new Intent(MainActivity.this,
                            DisplayLocationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_webserver:
                    intent = new Intent(MainActivity.this, WebServerActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}