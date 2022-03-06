package edu.neu.madcourse.numad21fa_yuzou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
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
    private TextView txt_detail;
    private TextView txt_instruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        id = getIntent().getStringExtra("id");

        txt_detail = findViewById(R.id.txtView_meal_detail);
        txt_instruction = findViewById(R.id.txtView_meal_instruction);
        txt_instruction.setMovementMethod(ScrollingMovementMethod.getInstance());

        String detail = String.format("Meal: %s\n\nCategory: %s\n\nCuisine: %s",
                getIntent().getStringExtra("meal"),
                getIntent().getStringExtra("category"),
                getIntent().getStringExtra("area"));

        txt_detail.setText(detail);
        txt_instruction.setText(getIntent().getStringExtra("instruction"));

    }

}