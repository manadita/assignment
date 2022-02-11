package edu.neu.madcourse.numad21fa_yuzou;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RviewHolder extends RecyclerView.ViewHolder{
    public TextView linkName;
    public TextView linkURL;

    public RviewHolder(View itemView) {
        super(itemView);
        linkName = itemView.findViewById(R.id.link_name);
        linkURL = itemView.findViewById(R.id.link_url);
    }
}
