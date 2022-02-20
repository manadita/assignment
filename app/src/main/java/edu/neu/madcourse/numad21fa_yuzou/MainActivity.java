package edu.neu.madcourse.numad21fa_yuzou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonAboutMe = findViewById(R.id.button_about_me);
        Button buttonLink = findViewById(R.id.button_link);
        Button buttonLoc = findViewById(R.id.btn_location);
        Button buttonClicky = findViewById(R.id.button_clicky);

        buttonAboutMe.setOnClickListener(new MyButtoListener());
        buttonLink.setOnClickListener(new MyButtoListener());
        buttonLoc.setOnClickListener((new MyButtoListener()));
        buttonClicky.setOnClickListener(new MyButtoListener());
    }

    class MyButtoListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button_about_me:
                    intent = new Intent(MainActivity.this, AboutMeInfo.class);
                    startActivity(intent);
                    break;
                case R.id.button_clicky:
                    intent = new Intent(MainActivity.this, ClickyClickyActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_link:
                    intent = new Intent(MainActivity.this,
                            RecyclerViewActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_location:
                    intent = new Intent(MainActivity.this,
                            DisplayLocationActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}