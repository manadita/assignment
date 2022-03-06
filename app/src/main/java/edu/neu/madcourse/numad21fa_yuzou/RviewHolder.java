package edu.neu.madcourse.numad21fa_yuzou;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RviewHolder extends RecyclerView.ViewHolder{
    public TextView linkID;
    public TextView linkName;
    public TextView linkURL;
    private Intent intent;

    public RviewHolder(View itemView) {
        super(itemView);
        linkName = itemView.findViewById(R.id.txt_mealinfo_meal);
        linkURL = itemView.findViewById(R.id.txt_mealinfo_category);
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
