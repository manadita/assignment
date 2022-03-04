package edu.neu.madcourse.numad21fa_yuzou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WebServiceActivity extends AppCompatActivity {
    private static final String apikey = "046f38d91a8fb45bbbcbb710696b2b43";
    private static final String TAG = "Web Service Activity";
    private RadioButton rdbMovie;
    private RadioButton rdbPeople;
    private TextView txtResult;
    private EditText edtInput;
    private String searchTxt;
    private String urllink;
    private boolean searchMovie;
    private boolean searchPeople;
    //private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        searchMovie = false;
        searchPeople = false;
        //movieList = new ArrayList<>();

        rdbMovie = findViewById(R.id.rdb_webservice_movie);
        rdbPeople = findViewById(R.id.rdb_webservice_people);
        edtInput = findViewById(R.id.et_movie_input);

        txtResult = findViewById(R.id.txt_movie_result);
        txtResult.setText("");
        Button btnSearch = findViewById(R.id.btn_movie_search);
        btnSearch.setOnClickListener(new MyClickListener());

    }


    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            editSearchUrl();
            String info = getData();
            txtResult.setText(info);

        }
    }

    private void editSearchUrl() {
        searchTxt = edtInput.getText().toString();
        //urllink = "https://api.themoviedb.org/3/search/movie?api_key="
        //        +apikey+"&query="+"alien";
        urllink = "https://api.themoviedb.org/3/search/movie?api_key=046f38d91a8fb45bbbcbb710696b2b43&query=ALIEN";
        //urllink = "https://www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata";
;    }


    private String getData(){
        StringBuffer movieInfo = new StringBuffer();
        //movieInfo.append("get data");

        try {

            // Initial website is "https://jsonplaceholder.typicode.com/posts/1"
            URL url = new URL(urllink);
            // Get String response from the url address
            String resp = WebServiceUtil.httpResponse(url);

            //JSONObject jsonObject = new JSONObject(resp);
            //movieInfo.append(jsonObject.getString("strMeal"));
            movieInfo.append("get data");


        } catch (MalformedURLException e) {
            movieInfo.append("MalformedURLException: ");
            movieInfo.append(e.toString());
            Log.e(TAG,"MalformedURLException");
            e.printStackTrace();
        //}
        //catch (ProtocolException e) {
        //    movieInfo.append("ProtocolException");
        //    Log.e(TAG,"ProtocolException");
        //e.printStackTrace();
        } catch (IOException e) {
            movieInfo.append("IOException: ");
            movieInfo.append(e.toString());
            Log.e(TAG,"IOException");
            e.printStackTrace();
        //} catch (JSONException e) {
        //    movieInfo.append("JSONException");
        //    Log.e(TAG,"JSONException");
            //   e.printStackTrace();
        }

        return movieInfo.toString();
    }




}