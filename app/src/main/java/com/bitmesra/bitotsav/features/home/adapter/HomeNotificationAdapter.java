package com.bitmesra.bitotsav.features.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.home.NotificationItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaurav on 30/1/17 for Bitotsav.
 */
public class HomeNotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private List<NotificationItem> items;

    public HomeNotificationAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        if (viewType == 1) {
            return new NotificationProgressViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_home_notification_progress, parent, false));
        } else {
            return new NotificationViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_home_notification_view, parent, false));
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==0) {
            NotificationItem item = items.get(position);
            ((NotificationViewHolder) holder).title.setText(item.getTitle());
            ((NotificationViewHolder) holder).time.setText(item.getTime());
        }
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position).getId() == -1) {
            return 1;
        }
        return 0;
    }

    public void addNextItems(List<NotificationItem> notificationItems) {
        removeProgressBar();
        int startPos = items.size();
        items.addAll(notificationItems);
        items.add(new NotificationItem(-1, "", ""));
        notifyItemRangeInserted(startPos, notificationItems.size());
    }

    public void addLatestItems(List<NotificationItem> notificationItems) {
        items.addAll(0,notificationItems);
        notifyItemRangeInserted(0,notificationItems.size());
    }

    public void removeProgressBar() {
        items.remove(items.size() - 1);
    }

    public void setItems(List<NotificationItem> items) {
        items.add(new NotificationItem(-1, "", ""));
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

    class NotificationProgressViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.notification_progressbar)
        ProgressBar progressBar;

        NotificationProgressViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}