package com.bitmesra.bitotsav.features.events.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.events.EventDto;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaurav on 5/2/17 for Bitotsav.
 */
public class TimelineListAdapter extends RecyclerView.Adapter<TimelineListAdapter.TimelineViewHolder> {
    private final Context context;
    private final int TYPE_FIRST_ITEM = 0;
    private final int TYPE_MIDDLE_ITEM = 1;
    private final int TYPE_LAST_ITEM = 2;
    private List<EventDto> items;

    public TimelineListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public TimelineViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        if (viewType == TYPE_FIRST_ITEM) {
            return new TimelineViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.timeline_start_item, parent, false));
        } else if (viewType == TYPE_MIDDLE_ITEM) {
            return new TimelineViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.timeline_middle_item, parent, false));
        } else {
            return new TimelineViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.timeline_end_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(TimelineViewHolder holder, int position) {
        EventDto item = items.get(position);
        holder.name.setText(item.getName());
        holder.timeVenue.setText(item.getTime() + " at " + item.getVenue());
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_FIRST_ITEM;
        } else if (position == items.size() - 1) {
            return TYPE_LAST_ITEM;
        } else {
            return TYPE_MIDDLE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public String getEventName(int pos) {
        return items.get(pos).getName();
    }

    public void setItems(List<EventDto> items) {
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