package edu.neu.madcourse.numad21fa_yuzou;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RviewAdapter extends RecyclerView.Adapter<RviewHolder>{
    private final List<ILinkCard> linklist;

    public RviewAdapter(List<ILinkCard> linklist) {
        this.linklist = linklist;
    }

    // inflate the row layout from layout
    @Override
    public RviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.link_card, parent,false);
        return new RviewHolder(view);
    }

    // bind data to the TextView in each row.
    @Override
    public void onBindViewHolder(RviewHolder holder, int position) {
        ILinkCard card = (LinkCard) linklist.get(position);
        //holder.linkID.setText(position);
        holder.linkName.setText(card.getName());
        holder.linkURL.setText(card.getURL());
    }

    @Override
    public int getItemCount() {
        return linklist.size();
    }
}
