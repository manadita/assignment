package edu.neu.madcourse.numad21fa_yuzou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.URLUtil;
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
    AlertDialog.Builder failalert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        linklist = new ArrayList<>();
        initialLinkDate();
        createRecyclerView();
        FloatingActionButton buttonAddLink = findViewById(R.id.fab_add);
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
        AlertDialog.Builder createLinkDialog = new AlertDialog.Builder(
                RecyclerViewActivity.this);
        createLinkDialog.setTitle("Create New Link");
        final View dialogview = getLayoutInflater().inflate(
                R.layout.create_link_dialog, null);
        createLinkDialog.setView(dialogview);
        createLinkDialog.setCancelable(true);

        createLinkDialog.setPositiveButton("Add Link", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText et_name = (EditText) dialogview.findViewById(R.id.editText_name);
                EditText et_url = (EditText) dialogview.findViewById(R.id.editText_url);
                linkname = et_name.getText().toString();
                linkurl = et_url.getText().toString();


                // check if name is empty.
                if (TextUtils.isEmpty(et_name.getText())) {
                    failalert = new AlertDialog.Builder(
                            (RecyclerViewActivity.this));
                    eidtLinkFailAlert("Name can not be blank.");
                    failalert.show();
                }
                else {
                    // check if url is valid.
                    if (!URLUtil.isValidUrl(linkurl)) {
                        failalert = new AlertDialog.Builder(
                                (RecyclerViewActivity.this));
                        eidtLinkFailAlert("The input URL is not valid.");
                        failalert.show();
                    }
                    // if name is not empty and url is valid, add link.
                    else {
                        addLinkCard();
                        // inform user link is added, and offer option to undo this action.
                        Snackbar snackbar = Snackbar.make(
                                rview, "Link successful added.",
                                Snackbar.LENGTH_INDEFINITE);
                        snackbar.setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                delLinkCard();
                            }
                        });
                        snackbar.getView().setOnClickListener(view -> snackbar.dismiss());
                        snackbar.show();
                    }
                }
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

    private void eidtLinkFailAlert(String s) {
        failalert.setTitle("Invalid Input").setMessage(s).setCancelable(true)
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(
                                        RecyclerViewActivity.this,
                                        "Fail. Link not added.",
                                        Toast.LENGTH_SHORT
                                ).show();
                                dialogInterface.cancel();
                            }
                        })
                .setPositiveButton("Re-Enter",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(
                                        RecyclerViewActivity.this,
                                        "Fail. Link not added.",
                                        Toast.LENGTH_SHORT
                                ).show();
                                dialogInterface.cancel();
                            }
                        });
    }

    private void delLinkCard() {
        linklist.remove(linklist.size() - 1);
        // if this is the only data in list, add an example data.
        if (linklist.size() == 0 || linklist == null){
            initialLinkDate();
        }
        Toast.makeText(
                RecyclerViewActivity.this,
                "Link successful deleted",
                Toast.LENGTH_SHORT
        ).show();
        rviewAdapter.notifyDataSetChanged();
    }

    private void addLinkCard() {
        linklist.add(new LinkCard(linkname, linkurl));
        // if fist data is example data, delete the first data.
        if (linklist.get(0).getName().equals("Name Example")) {
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