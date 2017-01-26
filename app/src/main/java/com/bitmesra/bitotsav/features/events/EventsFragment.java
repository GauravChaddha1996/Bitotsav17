package com.bitmesra.bitotsav.features.events;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.IdForFragment;

public class EventsFragment extends BaseFragment {


    public EventsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.EVENTS;
    }

    @Override
    public IdForFragment getBackToClazz() {
        return IdForFragment.HOME;
    }
}
