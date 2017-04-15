package com.bitmesra.bitotsav.features.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by Batdroid on 11/2/17 for Bitotsav.
 */

public class NotificationFeedItemAnimator extends DefaultItemAnimator {



    @Override
    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull List<Object> payloads) {
        return true;
    }
}
