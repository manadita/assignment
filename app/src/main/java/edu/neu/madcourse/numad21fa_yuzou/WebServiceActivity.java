package edu.neu.madcourse.numad21fa_yuzou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    private static final String TAG = "Web Service Activity";
    private String urllink;
    private List<Meal> mealList;
    private TextView txtResult;
    private EditText edtInput;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        mealList = new ArrayList<>();

        edtInput = findViewById(R.id.et_movie_input);

        txtResult = findViewById(R.id.txt_movie_result);
        txtResult.setText("");
        ProgressBar pbSearch = findViewById(R.id.pb_moviesearch);
        Button btnSearch = findViewById(R.id.btn_movie_search);
        btnSearch.setOnClickListener(new MyClickListener());
    }


    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            // get user input as search keyword.
            editSearchUrl();

            // alert user if nothing typed in the search text area.
            if (urllink == null){
                AlertDialog.Builder aleart_nullInput = new AlertDialog.Builder(
                        (WebServiceActivity.this));
                aleart_nullInput.setTitle("Invalid Input").setMessage("Enter search content.")
                        .setCancelable(true)
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                })
                        .setPositiveButton("Re-Enter",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                aleart_nullInput.show();
            }
            // search the database using keyword.
            else {
                Thread searchThread = new Thread(new WebSearch());
                searchThread.start();
                if (mealList.size() == 0){
                    txtResult.setText("0 meal found.");
                }
                else {
                    txtResult.setText(String.format("%d meals found.", mealList.size()));
                    // list result.
                    creatRecyclerView();
                }
            }

        }
    }

    private void editSearchUrl() {
        String keyWord = edtInput.getText().toString();
        if (TextUtils.isEmpty(keyWord)) {
            urllink = null;
        }
        else {
            urllink = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + keyWord;
        }

;    }


    private class WebSearch implements Runnable {

        @Override
        public void run() {

            JSONArray jArray = new JSONArray();
            JSONObject jObject = new JSONObject();


            try {

                URL url = new URL(urllink);

                // Get String response from the url address
                String resp = null;
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();

                // Read response.
                InputStream inputStream = conn.getInputStream();
                StringBuilder stringBuilder=new StringBuilder();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                String len = null;
                while((len = bufferedReader.readLine()) != null){
                    stringBuilder.append(len);
                }
                bufferedReader.close();
                resp = stringBuilder.toString();

                // handle result
                jObject = new JSONObject(resp);
                jArray = jObject.getJSONArray("meals");

            } catch (MalformedURLException e) {
                Log.e(TAG, "***** Create URL err: " + e.toString());
                e.printStackTrace();
            } catch (ProtocolException e) {
                Log.e(TAG, "***** OPEN URL err: " + e.toString());
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(TAG, "***** OPEN URL err: " + e.toString());
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i(TAG, String.valueOf(jArray.length()));

            mealList = new ArrayList<>();
            // loop through all meals
            try {
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject jmeal = jArray.getJSONObject(i);
                    String id = jmeal.getString("idMeal");
                    String name = jmeal.getString("strMeal");
                    String category = jmeal.getString("strCategory");
                    String area = jmeal.getString("strArea");
                    String instruction = jmeal.getString("strInstructions");
                    Meal meal = new Meal(id, name, category, area, instruction);
                    mealList.add(meal);
                }
                Log.d(TAG, "***** Convert meals");

                for (int i = 0; i < mealList.size(); i ++){
                    String ee = mealList.get(i).getMeal();
                    Log.i(TAG, ee);
                }

            } catch (JSONException e) {
                Log.i(TAG,"get movie details"+e.toString());
                e.printStackTrace();
            }


        }
    }


    private void creatRecyclerView() {
        RecyclerView mealView = findViewById(R.id.recyclerview_movie);
        MviewAdapter mealAdapter = new MviewAdapter(mealList);
        mealView.setLayoutManager(new LinearLayoutManager(this));
        mealView.setItemAnimator(new DefaultItemAnimator());
        mealView.setAdapter(mealAdapter);

    }

    private class MviewAdapter extends RecyclerView.Adapter<MviewHolder>{
        private final List<Meal> list;

        public MviewAdapter(List<Meal> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public MviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.meal_card, parent,false);
            return new MviewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MviewHolder holder, int position) {
            Meal card = (Meal) list.get(position);
            holder.mealName.setText(card.getMeal());
            holder.category.setText(card.getCategory());
            holder.area.setText(card.getArea());
            holder.id = card.getId();
            holder.instruction = card.getInstruction();
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private class MviewHolder extends RecyclerView.ViewHolder{
        private TextView mealName;
        private TextView category;
        private TextView area;
        private String id;
        private String instruction;

        public MviewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.txt_mealinfo_meal);
            category = itemView.findViewById(R.id.txt_mealinfo_category);
            area = itemView.findViewById(R.id.txt_mealinfo_area);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(WebServiceActivity.this, MealDetailActivity.class);
                    String mealDetail = String.format("Meal: %s\n\n%s\n\n%s\n\n\n",
                            mealName.getText().toString(), category.getText().toString(),
                            area.getText().toString());
                    intent.putExtra("meal", mealDetail);
                    intent.putExtra("instruction", instruction);
                    startActivity(intent);
                }
            });
        }
    }




}