package edu.neu.madcourse.numad21fa_yuzou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClickyClickyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky_clicky);

        Button buttonA = findViewById(R.id.button_a);
        Button buttonB = findViewById(R.id.button_b);
        Button buttonC = findViewById(R.id.button_c);
        Button buttonD = findViewById(R.id.button_d);
        Button buttonE = findViewById(R.id.button_e);
        Button buttonF = findViewById(R.id.button_f);
        TextView textButtonPressed = findViewById(R.id.textView_buttonpressed);
        textButtonPressed.setText("Pressed: - ");
        textButtonPressed.setGravity(Gravity.CENTER);

        buttonA.setOnClickListener(new MyButtoListener());
        buttonB.setOnClickListener(new MyButtoListener());
        buttonC.setOnClickListener(new MyButtoListener());
        buttonD.setOnClickListener(new MyButtoListener());
        buttonE.setOnClickListener(new MyButtoListener());
        buttonF.setOnClickListener(new MyButtoListener());
    }

    class MyButtoListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button_a:
                    TextView textPressedA = findViewById(R.id.textView_buttonpressed);
                    textPressedA.setGravity(Gravity.CENTER);
                    textPressedA.setText("Pressed: A ");
                    break;
                case R.id.button_b:
                    TextView textPressedB = findViewById(R.id.textView_buttonpressed);
                    textPressedB.setGravity(Gravity.CENTER);
                    textPressedB.setText("Pressed: B ");
                    break;
                case R.id.button_c:
                    TextView textPressedC = findViewById(R.id.textView_buttonpressed);
                    textPressedC.setGravity(Gravity.CENTER);
                    textPressedC.setText("Pressed: C ");
                    break;
                case R.id.button_d:
                    TextView textPressedD = findViewById(R.id.textView_buttonpressed);
                    textPressedD.setGravity(Gravity.CENTER);
                    textPressedD.setText("Pressed: D ");
                    break;
                case R.id.button_e:
                    TextView textPressedE = findViewById(R.id.textView_buttonpressed);
                    textPressedE.setGravity(Gravity.CENTER);
                    textPressedE.setText("Pressed: E ");
                    break;
                case R.id.button_f:
                    TextView textPressedF = findViewById(R.id.textView_buttonpressed);
                    textPressedF.setGravity(Gravity.CENTER);
                    textPressedF.setText("Pressed: F ");
                    break;
            }
        }
    }

}