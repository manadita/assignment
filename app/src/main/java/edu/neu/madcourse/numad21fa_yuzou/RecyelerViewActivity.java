package edu.neu.madcourse.numad21fa_yuzou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecyelerViewActivity extends AppCompatActivity {
    private List<ILinkCard> linklist = new ArrayList<>();
    private RecyclerView rview;
    private RviewAdapter rviewAdapter;
    private FloatingActionButton buttonAddLink;
    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyelerview);
        init(savedInstanceState);

        buttonAddLink = findViewById(R.id.floatingAB_addlink);
        buttonAddLink.setOnClickListener(new MyButtoListener());
    }

    private void init(Bundle savedInstanceState){
        initialLinkDate(savedInstanceState);
        createRecyclerView();
    }

    private void initialLinkDate(Bundle savedInstanceState){
        // not the first time open this activity
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)){
            if (linklist == null || linklist.size() == 0) {
                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);

                for (int i = 0; i < size; i ++) {
                    Integer linkID = savedInstanceState.getInt(KEY_OF_INSTANCE + i + "0");
                    String linkName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");
                    String linkURL = savedInstanceState.getString(KEY_OF_INSTANCE + i + "2");
                    ILinkCard linkcard = new LinkCard(linkName, linkURL);
                    linklist.add(linkcard);
                }
            }

        }
        // first time open this activity.
        else {

        }
    }

    private void createRecyclerView() {
        rview = findViewById(R.id.recyclerview_link);
        rview.setHasFixedSize(true);
        rviewAdapter = new RviewAdapter(linklist);
        rview.setAdapter(rviewAdapter);
        rview.setLayoutManager(new LinearLayoutManager(this));
    }

    private void addLink(int position) {
        linklist.add(position, new LinkCard(linkname, linkurl));
        Snackbar.make().show();
        rviewAdapter.notifyItemInserted(position);
    }


    class MyButtoListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.floatingAB_addlink:
                    int pos = 0;
                    addLink(pos);
                    break;
            }
        }
    }



}