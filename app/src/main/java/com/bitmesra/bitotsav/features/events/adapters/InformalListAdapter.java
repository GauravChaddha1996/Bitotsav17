package com.bitmesra.bitotsav.features.events.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.events.InformalItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaurav on 5/2/17 for Bitotsav.
 */
public class InformalListAdapter extends RecyclerView.Adapter<InformalListAdapter.InformalViewHolder> {
    private final Context context;
    private List<InformalItem> items;

    public InformalListAdapter(Context context, List<InformalItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public InformalViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        return new InformalViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_informal_items, parent, false));
    }

    @Override
    public void onBindViewHolder(InformalViewHolder holder, int position) {
        InformalItem item = items.get(position);
        holder.informalName.setText(item.getName());
        holder.clubName.setText(item.getOrganizingClub());
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    class InformalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.informal_name)
        TextView informalName;
        @BindView(R.id.informal_club)
        TextView clubName;

        InformalViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}