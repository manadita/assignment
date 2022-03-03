package edu.neu.madcourse.numad21fa_yuzou;

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
    private List<ILinkCard> linkList;
    private String linkName;
    private String linkURL;
    private RecyclerView RView;
    private RviewAdapter rviewAdapter;
    AlertDialog.Builder failAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        linkList = new ArrayList<>();
        initialLinkDate();
        createRecyclerView();
        FloatingActionButton buttonAddLink = findViewById(R.id.fab_add);
        buttonAddLink.setOnClickListener(new MyButtoListener());
    }

    private void initialLinkDate(){
        linkList.add(new LinkCard("Name Example", "http://www.URLexample.com"));
    }

    private void createRecyclerView() {
        RView = findViewById(R.id.recyclerView);
        rviewAdapter = new RviewAdapter(linkList);
        RView.setLayoutManager(new LinearLayoutManager(this));
        RView.setItemAnimator(new DefaultItemAnimator());
        RView.setAdapter(rviewAdapter);
    }

    /**
     * Add a link to recycler view.
     */
    private void addLink() {
        // create link dialog
        AlertDialog.Builder createLinkDialog = new AlertDialog.Builder(
                RecyclerViewActivity.this);
        createLinkDialog.setTitle("Create New Link");
        final View dialogView = getLayoutInflater().inflate(
                R.layout.create_link_dialog, null);
        createLinkDialog.setView(dialogView);
        createLinkDialog.setCancelable(true);

        createLinkDialog.setPositiveButton("Add Link", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText et_name = (EditText) dialogView.findViewById(R.id.editText_linkName);
                EditText et_url = (EditText) dialogView.findViewById(R.id.editText_linkURL);
                linkName = et_name.getText().toString();
                linkURL = et_url.getText().toString();

                // check if name is empty.
                if (TextUtils.isEmpty(et_name.getText())) {
                    failAlert = new AlertDialog.Builder(
                            (RecyclerViewActivity.this));
                    editLinkFailAlert("Name can not be blank.");
                    failAlert.show();
                }
                else {
                    // check if url is valid.
                    if (!URLUtil.isValidUrl(linkURL)) {
                        failAlert = new AlertDialog.Builder(
                                (RecyclerViewActivity.this));
                        editLinkFailAlert("The input URL is not valid.");
                        failAlert.show();
                    }
                    // if name is not empty and url is valid, add link to recyclerview.
                    else {
                        addLinkCard();
                        // inform user link is added, and offer option to undo this action.
                        Snackbar snackbar = Snackbar.make(
                                RView, "Link successful added.",
                                Snackbar.LENGTH_INDEFINITE);
                        snackbar.setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                deleteLinkCard();
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

    private void editLinkFailAlert(String s) {
        failAlert.setTitle("Invalid Input").setMessage(s).setCancelable(true)
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

    private void deleteLinkCard() {
        linkList.remove(linkList.size() - 1);
        // if this is the only data in list, add an example data.
        if (linkList.size() == 0){
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
        linkList.add(new LinkCard(linkName, linkURL));
        // if fist data is example data, delete the first data.
        if (linkList.get(0).getName().equals("Name Example")) {
            linkList.remove(0);
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