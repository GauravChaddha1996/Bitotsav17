package com.bitmesra.bitotsav.features.events.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.events.EventDto;
import com.bitmesra.bitotsav.ui.CustomTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaurav on 5/2/17 for Bitotsav.
 */
public class InformalListAdapter extends RecyclerView.Adapter<InformalListAdapter.InformalViewHolder> {
    private final Context context;
    private List<EventDto> items;

    public InformalListAdapter(Context context, List<EventDto> items) {
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
        EventDto item = items.get(position);
        holder.title.setText(item.getName());
        holder.timeVenue.setText(item.getTime()+ " at "+item.getVenue());
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    class InformalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.informal_title)
        CustomTextView title;
        @BindView(R.id.informal_body)
        CustomTextView body;
        @BindView(R.id.informal_time_venue)
        CustomTextView timeVenue;

        InformalViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}