package edu.neu.madcourse.numad21fa_yuzou;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class RviewHolder extends RecyclerView.ViewHolder{
    public TextView linkID;
    public TextView linkName;
    public TextView linkURL;
    private Intent intent;

    public RviewHolder(View itemView) {
        super(itemView);
        //linkID = itemView.findViewById(R.id.link_id);
        linkName = itemView.findViewById(R.id.link_name);
        linkURL = itemView.findViewById(R.id.link_url);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.setData(Uri.parse(linkURL.getText().toString()));
                intent.setAction(Intent.ACTION_VIEW);
                view.getContext().startActivity(intent);
            }
        });
    }

}
