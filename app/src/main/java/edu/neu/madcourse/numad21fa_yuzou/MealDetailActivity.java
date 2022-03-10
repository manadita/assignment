package edu.neu.madcourse.numad21fa_yuzou;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

public class MealDetailActivity extends AppCompatActivity {
    private static final String TAG = "Meal Detail Activity";
    private String id;
    private String name;
    private String category;
    private String area;
    private String instruction;
    private String path;
    private String detail;
    private TextView txt_detail;
    private TextView txt_instruction;
    private ImageView bmImage;
    private Bitmap bmp;
    private Handler myHandler;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        myHandler = new Handler();
        id = getIntent().getStringExtra("id");

        bmImage = findViewById(R.id.imageView_meal);
        txt_detail = findViewById(R.id.txtView_meal_detail);
        txt_instruction = findViewById(R.id.txtView_meal_instruction);
        progressBar = findViewById(R.id.pb_meal_detailsearch);
        progressBar.setVisibility(View.VISIBLE);
        txt_instruction.setMovementMethod(ScrollingMovementMethod.getInstance());

        getDetail();

    }

    private void getDetail() {
        Thread searchThread = new idSearch();
        searchThread.start();
    }

    private class idSearch extends Thread {
        @Override
        public void run() {

            JSONArray jArray = new JSONArray();
            JSONObject jObject = new JSONObject();

            try {
                URL url = new URL("https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + id);
                // Get String response from the url address
                String resp = null;
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();

                // Read response.
                InputStream inputStream = conn.getInputStream();
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String len = null;
                while ((len = bufferedReader.readLine()) != null) {
                    stringBuilder.append(len);
                }
                bufferedReader.close();
                resp = stringBuilder.toString();

                // handle result
                jObject = new JSONObject(resp);
                jArray = jObject.getJSONArray("meals");
                conn.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                JSONObject jmeal = jArray.getJSONObject(0);
                name = jmeal.getString("strMeal");
                path = jmeal.getString("strMealThumb");
                category = jmeal.getString("strCategory");
                area = jmeal.getString("strArea");
                instruction = jmeal.getString("strInstructions");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            // get meal poster.
            try {
                bmp = BitmapFactory.decodeStream(new URL(path).openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }


            myHandler.post(new Runnable() {
                @SuppressLint("SetTextI18n")
                @Override
                public void run() {
                    progressBar.setVisibility(View.INVISIBLE);
                    detail = String.format("Meal: %s\n\nCategory: %s\n\nCuisine: %s",
                            name, category, area);
                    txt_detail.setText(detail);
                    txt_instruction.setText("Instruction: \n" + instruction);
                    bmImage.setImageBitmap(bmp);
                }
            });
        }

    }

}