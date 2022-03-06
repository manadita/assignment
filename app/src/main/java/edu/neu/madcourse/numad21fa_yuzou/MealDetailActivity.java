package edu.neu.madcourse.numad21fa_yuzou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class MealDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        TextView detail = findViewById(R.id.txtView_meal_detail);
        TextView instruction = findViewById(R.id.txtView_meal_instruction);
        instruction.setMovementMethod(ScrollingMovementMethod.getInstance());
        detail.setText(getIntent().getStringExtra("meal"));
        instruction.setText(getIntent().getStringExtra("instruction"));

    }
}