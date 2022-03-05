package edu.neu.madcourse.numad21fa_yuzou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        searchMovie = false;
        searchPeople = false;
        movieList = new ArrayList<>();


        //rdbMovie = findViewById(R.id.rdb_webservice_movie);
        //rdbPeople = findViewById(R.id.rdb_webservice_people);
        edtInput = findViewById(R.id.et_movie_input);

        txtResult = findViewById(R.id.txt_movie_result);
        txtResult.setText("");
        Button btnSearch = findViewById(R.id.btn_movie_search);
        btnSearch.setOnClickListener(new MyClickListener());

        getTestData();
        creatRecyclerView();

    }


    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            editSearchUrl();
            WebServiceTask task = new WebServiceTask();
            task.execute(urllink);
        }
    }

    private void editSearchUrl() {
        searchTxt = edtInput.getText().toString();
        //urllink = "https://api.themoviedb.org/3/search/movie?api_key="
        //        +apikey+"&query="+"alien";
        urllink = "https://api.themoviedb.org/3/search/movie?api_key=046f38d91a8fb45bbbcbb710696b2b43&query=Alien+Resurrection";
        //urllink = "https://www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata";
;    }


    private class WebServiceTask extends AsyncTask<String, Integer, JSONArray>{

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i(TAG, "Making progress...");
        }

        @Override
        protected JSONArray doInBackground(String... param) {

            JSONArray jArray = new JSONArray();
            JSONObject jObject = new JSONObject();

            try{
                URL url = new URL(urllink);
                String resp;
                // Get String response from the url address
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                // Read response.
                InputStream inputStream = conn.getInputStream();
                StringBuilder stringBuilder=new StringBuilder();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

                String len = "";
                while((len = bufferedReader.readLine()) != null){
                    stringBuilder.append(len);
                }
                bufferedReader.close();
                resp = stringBuilder.toString();
                Log.e(TAG, "Response from url: " + resp);

                // handle result
                // resp = testResp();
                jObject = new JSONObject(resp);
                jArray = jObject.getJSONArray("id");


            } catch (MalformedURLException mfURLe) {
                Log.i(TAG,"MalformedURLException");
                mfURLe.printStackTrace();
            } catch (ProtocolException proe) {
                Log.i(TAG, "ProtocolException");
                proe.printStackTrace();
            } catch (IOException ioe) {
                Log.i(TAG, "IOException");
                ioe.printStackTrace();
            } catch (JSONException jsoe) {
                Log.i(TAG,"JSONException");
                txtResult.setText("JSONException");
                jsoe.printStackTrace();
            }

            return jArray;
        }

        @Override
        protected void onPostExecute(JSONArray jArray) {
            super.onPostExecute(jArray);
            //TextView result_view = (TextView)findViewById(R.id.result_textview);
            if (jArray == null){
                txtResult.setText("Movie not found.");
            }
            else {
                // loop through all movies
                try {
                    for (int i = 0; i < jArray.length(); i ++){
                        JSONObject jmovie = jArray.getJSONObject(i);
                        String title = jmovie.getString("original_title");
                        String releasedata = jmovie.getString("release_date");
                        String rating = jmovie.getString("vote_average");
                        Movie movie = new Movie(title, releasedata, rating);
                        movieList.add(movie);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    private void creatRecyclerView() {
        RecyclerView movieView = findViewById(R.id.recyclerview_movie);
        MviewAdapter movieAdapter = new MviewAdapter(movieList);
        movieView.setLayoutManager(new LinearLayoutManager(this));
        movieView.setItemAnimator(new DefaultItemAnimator());
        movieView.setAdapter(movieAdapter);

    }

    private class MviewAdapter extends RecyclerView.Adapter<MviewHolder>{
        private final List<Movie> list;

        public MviewAdapter(List<Movie> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public MviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.move_info_card, parent,false);
            return new MviewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MviewHolder holder, int position) {
            Movie card = (Movie) list.get(position);
            holder.movieTitle.setText(card.getTitle());
            holder.movieReleaseDate.setText(card.getReleaseDte());
            holder.movieRating.setText(card.getRating());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private class MviewHolder extends RecyclerView.ViewHolder{
        private TextView movieTitle;
        private TextView movieReleaseDate;
        private TextView movieRating;

        public MviewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.txt_movieinfo_original_title);
            movieReleaseDate = itemView.findViewById(R.id.txt_movieinfo_release_date);
            movieRating = itemView.findViewById(R.id.txt_movieinfo_vote_average);


        }
    }


    private String testResp() {
        return "{" +
                "\"page\":1," +
                "\"results\":[" +
                "{\"adult\":false," +
                "\"backdrop_path\":\"/ikr0UILfvRerzMNoBTtJtyuWAEV.jpg\"," +
                "\"genre_ids\":[878,27,28]," +
                "\"id\":8078," +
                "\"original_language\":\"en\"," +
                "\"original_title\":\"Alien Resurrection\"," +
                "\"overview\":\"Two hundred years after Lt. Ripley died, " +
                "a group of scientists clone her, hoping to breed the ultimate weapon. " +
                "But the new Ripley is full of surprises … as are the new aliens. " +
                "Ripley must team with a band of smugglers to keep the creatures from reaching Earth.\"," +
                "\"popularity\":30.553," +
                "\"poster_path\":\"/9aRDMlU5Zwpysilm0WCWzU2PCFv.jpg\"," +
                "\"release_date\":\"1997-11-12\"," +
                "\"title\":\"Alien Resurrection\"," +
                "\"video\":false," +
                "\"vote_average\":6.1," +
                "\"vote_count\":3740}," +
                "]," +
                "\"total_pages\":1," +
                "\"total_results\":1}";
    }

    private void getTestData(){
        movieList.add(new Movie("Movie 01", "2022-3-15", "6.0"));
        movieList.add(new Movie("Movie 02", "2019-7-06", "8.2"));
        movieList.add(new Movie("Movie 03", "1981-11-25", "7.5"));
    }


}