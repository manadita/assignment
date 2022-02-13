package edu.neu.madcourse.numad21fa_yuzou;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class RviewHolder extends RecyclerView.ViewHolder{
    public TextView linkID;
    public TextView linkName;
    public TextView linkURL;

    public RviewHolder(View itemView) {
        super(itemView);
        //linkID = itemView.findViewById(R.id.link_id);
        linkName = itemView.findViewById(R.id.link_name);
        linkURL = itemView.findViewById(R.id.link_url);
        linkURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show url in browser.
            }
        });
    }

}
