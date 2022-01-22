package edu.neu.madcourse.numad21fa_yuzou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonAboutMe = findViewById(R.id.button_about_me);
        buttonAboutMe.setOnClickListener(new MyClickListener());
    }

    class MyClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.button_about_me){
                Toast.makeText(MainActivity.this,
                        "Mana Yu Zou: zou.yu1@northeastern.edu", Toast.LENGTH_LONG).show();
            }
        }
    }
}