package edu.neu.madcourse.numad21fa_yuzou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class WebServiceActivity extends AppCompatActivity {
    private static final String apikey = "046f38d91a8fb45bbbcbb710696b2b43";
    private static final String TAG = "Web Service Activity";
    private RadioButton rdbMovie;
    private RadioButton rdbPeople;
    private TextView txtResult;
    private String searchTxt;
    private String url;
    private boolean searchMovie;
    private boolean searchPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        searchMovie = false;
        searchPeople = false;

        rdbMovie = findViewById(R.id.rdb_webservice_movie);
        rdbPeople = findViewById(R.id.rdb_webservice_people);
        EditText edtInput = findViewById(R.id.et_movie_input);
        searchTxt = edtInput.getText().toString();
        txtResult = findViewById(R.id.txt_movie_result);
        txtResult.setText("");
        Button btnSearch = findViewById(R.id.btn_movie_search);
        btnSearch.setOnClickListener(new MyClickListener());

    }


    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            checkSearch();
            if (searchMovie && searchPeople){
                // TODO alert msg : can't search both
            }
            else if (!searchMovie && !searchPeople){
                // TODO alear msg : must select one
            }
            else {
                // TODO search database and get result
                editSearchUrl();
                getResult();
                appendResponse();
            }
        }
    }

    private void getResult() {
        // TODO get data from database using url.
    }


    private void checkSearch() {
        //Check if search preference is selected.
        if (rdbMovie.isChecked()){
            searchMovie = true;
        }
        if (rdbPeople.isChecked()){
            searchPeople = true;
        }
    }


    private void editSearchUrl() {
        if (searchTxt.isEmpty() || searchTxt == null){
            // TODO aleart msg: input title or name for search.
        }
        else {
            if (searchMovie){
                url = "https://api.themoviedb.org/3/search/movie?api_key="
                        +apikey+"&query="+searchTxt;
            }
            if (searchPeople){
                url = ""+"";
            }
        }
    }

}