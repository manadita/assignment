package edu.neu.madcourse.numad21fa_yuzou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        linklist = new ArrayList<>();
        initialLinkDate();
        createRecyclerView();
        buttonAddLink = findViewById(R.id.fab_add);
        buttonAddLink.setOnClickListener(new MyButtoListener());
    }

    private void initialLinkDate(){
        linklist.add(new LinkCard("Name Example", "http://www.URLexample.com"));
    }

    private void createRecyclerView() {
        rview = findViewById(R.id.recyclerView);
        rviewAdapter = new RviewAdapter(linklist);
        rview.setLayoutManager(new LinearLayoutManager(this));
        rview.setItemAnimator(new DefaultItemAnimator());
        rview.setAdapter(rviewAdapter);
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
                    addLinkCard();
                    // inform user link is added, and offer option to undo this action.
                    Snackbar snackbar = Snackbar.make(
                            rview, "Link added",
                            Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    delLinkCard();
                                    Toast.makeText(
                                            RecyclerViewActivity.this,
                                            "Link is deleted",
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

    private void delLinkCard() {
        linklist.remove(linklist.size() - 1);
        if (linklist.size() == 0 || linklist == null){
            initialLinkDate();
            Toast.makeText(
                    RecyclerViewActivity.this,
                    "intial link data.",
                    Toast.LENGTH_SHORT
            ).show();
        }
        rviewAdapter.notifyDataSetChanged();
    }

    private void addLinkCard() {
        linklist.add(new LinkCard(linkname, linkurl));
        if (linklist.get(0).getName().equals("Name Example")){
            linklist.remove(0);
        }
        rviewAdapter.notifyDataSetChanged();
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