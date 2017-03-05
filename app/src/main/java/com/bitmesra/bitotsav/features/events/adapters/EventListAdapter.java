package com.bitmesra.bitotsav.features.events.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.events.EventItem;
import com.bitmesra.bitotsav.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaurav on 5/2/17 for Bitotsav.
 */
public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventItemViewHolder> {
    private final Context context;
    private List<EventItem> items;
    private int lastPos = -1;
    private boolean animationLocked = false;

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
        switch (position) {
            case 0:
                holder.backgroundImage.setBackground(context.getDrawable(R.drawable.gaurav_cover));
                break;
            case 1:
                holder.backgroundImage.setBackground(context.getDrawable(R.drawable.praneet_cover));
                break;
            case 2:
                holder.backgroundImage.setBackground(context.getDrawable(R.drawable.nikhil_cover));
                break;
            case 3:
                holder.backgroundImage.setBackground(context.getDrawable(R.drawable.ashish_cover));
                break;
        }
        holder.eventType.setText(item.getType());
        runEnterAnimation(holder.itemView, position);

    }

    private void runEnterAnimation(View view, int position) {
        if (!animationLocked) {
            if (position > lastPos) {
                lastPos = position;
                view.setTranslationY(Utils.getScreenHeight(context));
                view.setAlpha(0.0f);
                view.animate()
                        .translationY(0).alpha(1f)
                        .setStartDelay(100 * position)
                        .setInterpolator(new DecelerateInterpolator(2.f))
                        .setDuration(350)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                animationLocked = true;
                            }
                        }).start();
            }
        }
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
        RelativeLayout backgroundImage;
        @BindView(R.id.itemView)
        View itemView;
        @BindView(R.id.event_type)
        TextView eventType;

        EventItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}