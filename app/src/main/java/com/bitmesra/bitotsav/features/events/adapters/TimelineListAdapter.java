package com.bitmesra.bitotsav.features.events.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.events.TimelineItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaurav on 5/2/17 for Bitotsav.
 */
public class TimelineListAdapter extends RecyclerView.Adapter<TimelineListAdapter.TimelineViewHolder> {
    private final Context context;
    private List<TimelineItem> items;

    public TimelineListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public TimelineViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        if (viewType == 0) {
            return new TimelineViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.timeline_item, parent, false));
        } else {
            return new TimelineViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.timeline_end_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(TimelineViewHolder holder, int position) {
        TimelineItem item = items.get(position);
        holder.name.setText(item.getName());
        holder.timeVenue.setText(item.getTime() + " at " + item.getVenue());
    }

    @Override
    public int getItemViewType(int position) {
        if (position == items.size() - 1) {
            return 1;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public void setItems(List<TimelineItem> items) {
        this.items = items;
    }

    class TimelineViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.timeline_item_name)
        TextView name;
        @BindView(R.id.timeline_item_time_venue)
        TextView timeVenue;

        TimelineViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}