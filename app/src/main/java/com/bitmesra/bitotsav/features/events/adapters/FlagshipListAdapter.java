package com.bitmesra.bitotsav.features.events.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.events.FlagshipItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaurav on 5/2/17 for Bitotsav.
 */
public class FlagshipListAdapter extends RecyclerView.Adapter<FlagshipListAdapter.FlagshipItemViewHolder> {
    private final Context context;
    private List<FlagshipItem> items;

    public FlagshipListAdapter(Context context, List<FlagshipItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public FlagshipItemViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        return new FlagshipItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_flagship_items, parent, false));
    }

    @Override
    public void onBindViewHolder(FlagshipItemViewHolder holder, int position) {
        FlagshipItem item = items.get(position);
        holder.eventName.setText(item.getName());
        holder.clubName.setText(item.getOrganizingClub());
        holder.backgroundImage.setImageDrawable(context.getDrawable(item.getBackgroundId()));
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    class FlagshipItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.flagship_name)
        TextView eventName;
        @BindView(R.id.flagship_organizing_club)
        TextView clubName;
        @BindView(R.id.background_image)
        ImageView backgroundImage;

        FlagshipItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}