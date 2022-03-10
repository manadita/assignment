package edu.neu.madcourse.numad21fa_yuzou;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private RecyclerView.Adapter mealAdapter;
    private ProgressBar progressBar;
    private RadioGroup rp_searchType;
    private boolean searchMeal;
    private boolean searchCuisine;
    private Handler myHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        mealList = new ArrayList<>();
        searchMeal = false;
        searchCuisine = false;

        TextView txtHowToUse = findViewById(R.id.txt_meal_howtouse);
        txtHowToUse.setText("Select Search by Meal or Search by Cuisine, " +
                "input search keyword then click SEARCH. " +
                "Click on a meal to see details.");
        edtInput = findViewById(R.id.et_meal_search_input);
        txtResult = findViewById(R.id.txt_meal_result);
        txtResult.setText("");
        progressBar = findViewById(R.id.pb_mealsearch);
        progressBar.setVisibility(View.GONE);
        rp_searchType = findViewById(R.id.rgoup_meal_searchgroup);
        myHandler = new Handler();

        Button btnSearch = findViewById(R.id.btn_meal_search);
        btnSearch.setOnClickListener(new MyClickListener());
        // check radio button group status.
        rp_searchType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                // check search type.
                RadioButton radioButton = findViewById(i);
                switch (radioButton.getId()){
                    case R.id.rb_search_meal:
                        searchMeal = true;
                        searchCuisine = false;
                        break;
                    case R.id.rb_search_area:
                        searchCuisine = true;
                        searchMeal = false;
                        break;
                }
            }
        });

    }


    /**
     * Click search button to search keyword in mealDB.
     */
    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            // get user input as search keyword.
            editSearchUrl();
            // alert user if nothing typed in the search text area.
            if (urllink == null){
                AlertDialog.Builder alert_nullInput = new AlertDialog.Builder(
                        (WebServiceActivity.this));
                alert_nullInput.setTitle("Invalid Input").setMessage("Enter search keyword.")
                        .setCancelable(true)
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                })
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                alert_nullInput.show();
            }
            // alert user if search type is not selected.
            else if (!searchMeal && !searchCuisine){
                AlertDialog.Builder alert_nullInput = new AlertDialog.Builder(
                        (WebServiceActivity.this));
                alert_nullInput.setTitle("Invalid Search Type").setMessage("Select a search type.")
                        .setCancelable(true)
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                })
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                alert_nullInput.show();
            }

            // search the database using keyword.
            else {
                progressBar.setVisibility(View.VISIBLE);
                Thread searchThread = new ThreadSearch();
                searchThread.start();
            }
        }
    }

    // edit url link according to user choice
    private void editSearchUrl() {
        String keyWord = edtInput.getText().toString();
        if (TextUtils.isEmpty(keyWord)) {
            urllink = null;
        }
        else {
            urllink = "";
            if (searchMeal){
                urllink = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + keyWord;
            }
            else if (searchCuisine){
                urllink = "https://www.themealdb.com/api/json/v1/1/filter.php?a=" + keyWord;
            }
        }
   }


    private class ThreadSearch extends Thread {
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

            mealList = new ArrayList<>();
            // loop through all meals
            try {
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject jmeal = jArray.getJSONObject(i);
                    String id = jmeal.getString("idMeal");
                    String name = jmeal.getString("strMeal");
                    String path = jmeal.getString("strMealThumb");
                    String category = "";
                    String area = "";
                    String instruction = "";
                    if (searchMeal) {
                        category = jmeal.getString("strCategory");
                        area = jmeal.getString("strArea");
                        instruction = jmeal.getString("strInstructions");
                    } else if (searchCuisine) {
                        area = edtInput.getText().toString();
                    }

                    Meal meal = new Meal(id, name, category, area, instruction, path);
                    mealList.add(meal);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            // display result.
            myHandler.post(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run () {
                // progress end.
                progressBar.setVisibility(View.INVISIBLE);

                // check result and displays.
                if (mealList.size() == 0) {
                    txtResult.setText("0 meal found.");
                } else {
                    txtResult.setText(String.format("%d meals found. Click a meal to see details.",
                                mealList.size()));
                }
                // create recycler view to hold result.
                createRecyclerView();
            }

        });
        }

    }

    /**
     * Create recyclerview that holds the result.
     */
    private void createRecyclerView() {
        RecyclerView mealView = findViewById(R.id.recyclerview_meal);
        mealAdapter = new TestAdapter(mealList);
        mealView.setLayoutManager(new LinearLayoutManager(this));
        mealView.setItemAnimator(new DefaultItemAnimator());
        mealView.setAdapter(mealAdapter);

    }

    private class TestAdapter extends RecyclerView.Adapter<MviewHolder>{
        private final List<Meal> list;

        public TestAdapter(List<Meal> list) {
            this.list = list;
        }

        @Override
        public MviewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.meal_card, parent,false);
            return new MviewHolder(view);
        }

        @Override
        public void onBindViewHolder(MviewHolder holder, int position) {
            Meal card = (Meal) list.get(position);
            holder.mealName.setText(card.getMeal());
            holder.category.setText(card.getCategory());
            holder.area.setText(card.getArea());
            holder.id = card.getId();
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

        public MviewHolder(View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.txt_mealinfo_meal);
            category = itemView.findViewById(R.id.txt_mealinfo_category);
            area = itemView.findViewById(R.id.txt_mealinfo_area);
            // when click the meal item, open new activity to show meal details.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(WebServiceActivity.this, MealDetailActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            });
        }
    }


}
