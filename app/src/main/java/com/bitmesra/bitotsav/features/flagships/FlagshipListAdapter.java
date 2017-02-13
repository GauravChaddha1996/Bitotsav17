package com.bitmesra.bitotsav.features.flagships;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.database.models.events.FlagshipItem;
import com.bitmesra.bitotsav.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaurav on 5/2/17 for Bitotsav.
 */
public class FlagshipListAdapter extends RecyclerView.Adapter<FlagshipListAdapter.FlagshipItemViewHolder> {
    private final Context context;
    private List<FlagshipItem> items;
    private int lastPos = -1;
    private boolean animationLocked = false;

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
        if(DataManager.getDataManager().getRealmManager().isTopicSubscribed(item.getName())) {
            holder.subscribedButton.setImageDrawable(context.getDrawable(R.drawable.ic_bell));
        }else{
            holder.subscribedButton.setImageDrawable(context.getDrawable(R.drawable.ic_no_bell));
        }
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

    public String getEventName(int pos) {
        return items.get(pos).getName();
    }

    class FlagshipItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.flagship_name)
        TextView eventName;
        @BindView(R.id.flagship_organizing_club)
        TextView clubName;
        @BindView(R.id.background_image)
        ImageView backgroundImage;
        @BindView(R.id.subscribedButton)
        FloatingActionButton subscribedButton;

        FlagshipItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}