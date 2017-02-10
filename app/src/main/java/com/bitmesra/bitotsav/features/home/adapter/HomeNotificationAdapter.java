package com.bitmesra.bitotsav.features.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.home.NotificationItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by gaurav on 30/1/17 for Bitotsav.
 */
public class HomeNotificationAdapter extends RealmRecyclerViewAdapter<NotificationItem, HomeNotificationAdapter.NotificationViewHolder> {
    private final Context context;

    public HomeNotificationAdapter(Context context, OrderedRealmCollection<NotificationItem> items) {
        super(context, items, true);
        this.context = context;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        return new NotificationViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_home_notification_view, parent, false));
    }


    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        NotificationItem item = getData().get(position);
        holder.title.setText(item.getTitle());
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a dd MMM", Locale.getDefault());
        holder.time.setText(dateFormat.format(new Date(item.getTime())));
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