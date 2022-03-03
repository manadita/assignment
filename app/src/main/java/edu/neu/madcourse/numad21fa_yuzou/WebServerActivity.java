package edu.neu.madcourse.numad21fa_yuzou;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class WebServerActivity extends AppCompatActivity {
    private static final String apikey = "046f38d91a8fb45bbbcbb710696b2b43";
    private static final String TAG = "Find Movies";
    private String searchTxt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        //EditText editSearch = findViewById(R.id.editTxt_movie_name);
        //searchTxt = editSearch.getText().toString();



        //Button button_imdb = findViewById(R.id.btn_imdb);
        //Button button_mealdb = findViewById(R.id.btn_mealdb);

        //button_imdb.setOnClickListener(new MyClickListener());



    }



}
