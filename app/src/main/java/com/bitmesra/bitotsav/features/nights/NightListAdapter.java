package com.bitmesra.bitotsav.features.nights;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.nights.NightsModel;
import com.bitmesra.bitotsav.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaurav on 5/2/17 for Bitotsav.
 */
public class NightListAdapter extends RecyclerView.Adapter<NightListAdapter.FlagshipItemViewHolder> {
    private final Context context;
    private List<NightsModel> items;
    private int lastPos = -1;
    private boolean animationLocked = false;

    public NightListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public FlagshipItemViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        return new FlagshipItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_night_item, parent, false));
    }

    @Override
    public void onBindViewHolder(FlagshipItemViewHolder holder, int position) {
        NightsModel item = items.get(position);
        holder.eventName.setText(item.getName());
        if (!item.getImage1().trim().isEmpty()) {
            Picasso.with(context).load(item.getImage1()).into(holder.backgroundImage);
        } else {
            holder.backgroundImage.setImageDrawable(context.getDrawable(R.drawable.ic_logo));
        }
        runEnterAnimation(holder.itemView, position);
    }

    public void setItems(List<NightsModel> items) {
        this.items = items;
    }

    public NightsModel getItem(int pos) {
        return items.get(pos);
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


    class FlagshipItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.flagship_name)
        TextView eventName;
        @BindView(R.id.background_image)
        ImageView backgroundImage;

        FlagshipItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}