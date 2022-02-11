package edu.neu.madcourse.numad21fa_yuzou;

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



    @Override
    public RviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                (R.layout.list_card, parent, false);
        return new RviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RviewHolder holder, int position) {
        LinkCard currentlink = (LinkCard) linklist.get(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
