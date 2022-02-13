package edu.neu.madcourse.numad21fa_yuzou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private List<ILinkCard> linklist;
    private String linkname;
    private String linkurl;
    private RecyclerView rview;
    private RviewAdapter rviewAdapter;
    private FloatingActionButton buttonAddLink;
    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        init(savedInstanceState);
        createRecyclerView();
        buttonAddLink = findViewById(R.id.fab_add);
        buttonAddLink.setOnClickListener(new MyButtoListener());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        int size = linklist == null ? 0 : linklist.size();
        outState.putInt(NUMBER_OF_ITEMS, size);
        for (int i = 0; i < size; i ++) {
            outState.putString(KEY_OF_INSTANCE + i + 0, linklist.get(i).getName());
            outState.putString(KEY_OF_INSTANCE + i + 1, linklist.get(i).getURL());
        }
        super.onSaveInstanceState(outState);
    }

    private void init(Bundle savedInstanceState){
        initialLinkDate(savedInstanceState);
        createRecyclerView();
    }

    private void initialLinkDate(Bundle savedInstanceState){
        linklist = new ArrayList<>();
        linklist.add(new LinkCard("Name Example", "http://www.URLexample.com"));
    }

    private void createRecyclerView() {
        rview = findViewById(R.id.recyclerView);
        rview.setHasFixedSize(true);
        rviewAdapter = new RviewAdapter(linklist);
        rview.setAdapter(rviewAdapter);
        rview.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Add a link to recycler view.
     */
    private void addLink() {
        // create link dialog
        AlertDialog.Builder createLinkDialog = new AlertDialog.Builder(RecyclerViewActivity.this);
        createLinkDialog.setTitle("Create New Link");
        final View dialogview = getLayoutInflater().inflate(R.layout.create_link_dialog, null);
        createLinkDialog.setView(dialogview);
        createLinkDialog.setCancelable(true);

        createLinkDialog.setPositiveButton("Add Link", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText et_name = (EditText) dialogview.findViewById(R.id.editText_name);
                EditText et_url = (EditText) dialogview.findViewById(R.id.editText_url);
                linkname = et_name.getText().toString();
                linkurl = et_url.getText().toString();
                // if edit text is not empty， add link.
                if (linkname != "" && linkurl != "") {
                    linklist.add(new LinkCard(linkname, linkurl));
                    rviewAdapter.notifyDataSetChanged();
                    // inform user link is added, and offer option to undo this action.
                    Snackbar snackbar = Snackbar.make(
                            rview, "Link added", Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //linklist.remove(linklist.size());   // remove the last input link.
                                    Toast.makeText(
                                            RecyclerViewActivity.this,
                                            "Action is undone",
                                            Toast.LENGTH_SHORT
                                    ).show();
                                }
                            });
                    snackbar.getView().setOnClickListener(view -> snackbar.dismiss());
                    snackbar.show();
                }
                // if edite text is empty. ask user to input something.
                else {
                    Toast.makeText(
                            RecyclerViewActivity.this,
                            "Must input both name and URL for the link.",
                            Toast.LENGTH_SHORT
                    ).show();
                };
                rviewAdapter.notifyDataSetChanged();
                dialogInterface.cancel();
            }
        });

        createLinkDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        createLinkDialog.show();
    }


    class MyButtoListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.fab_add:
                    addLink();
                    break;
            }
        }
    }



}