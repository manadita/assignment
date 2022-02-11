package edu.neu.madcourse.numad21fa_yuzou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Map;

public class RecyelerViewActivity extends AppCompatActivity {
    private Map links;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyelerview);
        Button buttonAddLink = findViewById(R.id.floatingAB_addlink);
        buttonAddLink.setOnClickListener(new MyButtoListener());
    }

    class MyButtoListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

        }
    }

}