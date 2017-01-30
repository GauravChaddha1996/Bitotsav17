package com.bitmesra.bitotsav.features.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.home.BitotsavNotification;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaurav on 30/1/17 for Bitotsav.
 */
public class HomeNotificationAdapter extends RecyclerView.Adapter<HomeNotificationAdapter.NotificationViewHolder> {
    private final Context context;
    private List<BitotsavNotification> items;

    public HomeNotificationAdapter(Context context, List<BitotsavNotification> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        return new NotificationViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_home_notification_view, parent, false));
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        BitotsavNotification item = items.get(position);
        holder.title.setText(item.getTitle());
        holder.time.setText(item.getTime());
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public void setItems(List<BitotsavNotification> items) {
        this.items = items;
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.notification_title)
        TextView title;
        @BindView(R.id.notification_time)
        TextView time;

        NotificationViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}