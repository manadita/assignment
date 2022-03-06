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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MealDetailActivity extends AppCompatActivity {
    private static final String TAG = "Meal Detail Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        TextView detail = findViewById(R.id.txtView_meal_detail);
        TextView instruction = findViewById(R.id.txtView_meal_instruction);
        ImageView image = findViewById(R.id.imageView_meal);

        instruction.setMovementMethod(ScrollingMovementMethod.getInstance());
        detail.setText(getIntent().getStringExtra("meal"));
        instruction.setText(getIntent().getStringExtra("instruction"));
        Log.i(TAG, getIntent().getStringExtra("imagepath"));
        //Drawable drawable = getDrawableFromURL(getIntent().getStringExtra("imagepath"));


    }

    private Drawable getDrawableFromURL(String imagepath) {
        Log.i(TAG, imagepath);
        try {
            Bitmap bmp;
            HttpURLConnection connection = (HttpURLConnection) new URL(imagepath).openConnection();
            connection.connect();
            InputStream input = connection.getInputStream();
            bmp = BitmapFactory.decodeStream(input);
            return new BitmapDrawable(Resources.getSystem(), bmp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}