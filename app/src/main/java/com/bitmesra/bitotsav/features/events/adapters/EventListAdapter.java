package com.bitmesra.bitotsav.features.events.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.events.EventItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaurav on 5/2/17 for Bitotsav.
 */
public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventItemViewHolder> {
    private final Context context;
    private List<EventItem> items;

    public EventListAdapter(Context context, List<EventItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public EventItemViewHolder onCreateViewHolder(ViewGroup parent,
                                                  int viewType) {
        return new EventItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_event_items, parent, false));
    }

    @Override
    public void onBindViewHolder(EventItemViewHolder holder, int position) {
        EventItem item = items.get(position);
        holder.backgroundImage.setImageDrawable(context.getDrawable(item.getBackGroundImageId()));
        holder.eventType.setText(item.getType());
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public EventItem getItem(int position) {
        return items.get(position);
    }

    class EventItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.background_image)
        ImageView backgroundImage;
        @BindView(R.id.event_type)
        TextView eventType;

        EventItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}