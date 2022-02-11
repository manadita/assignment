package edu.neu.madcourse.numad21fa_yuzou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecyelerViewActivity extends AppCompatActivity {
    private List<ILinkCard> links = new ArrayList<>();
    private RecyclerView rview;
    private RviewAdapter rviewAdapter;
    private RecyclerView.LayoutManager rLayoutManager;
    private FloatingActionButton buttonAddLink;
    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recyelerview);
        buttonAddLink = findViewById(R.id.floatingAB_addlink);
        RecyclerView linkview = findViewById(R.id.recyclerview_link);
        linkview.setLayoutManager(new LinearLayoutManager(this));

        buttonAddLink.setOnClickListener(new MyButtoListener());
    }

    class MyButtoListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.floatingAB_addlink:
                    break;

            }

        }
    }

}