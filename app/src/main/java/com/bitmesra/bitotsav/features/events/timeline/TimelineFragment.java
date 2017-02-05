package com.bitmesra.bitotsav.features.events.timeline;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.IdForFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimelineFragment extends BaseFragment {


    public TimelineFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timeline, container, false);
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.TIMELINE;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.EVENTS;
    }
}
