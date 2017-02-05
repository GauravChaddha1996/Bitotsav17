package com.bitmesra.bitotsav.features.events;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.events.adapters.EventListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsFragment extends BaseFragment implements EventViewInterface {


    @BindView(R.id.event_recycler_view)
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    EventListAdapter adapter;

    EventPresenter presenter;

    public EventsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        ButterKnife.bind(this, view);
        presenter = new EventPresenter(getActivity(), this);
        setUpEventList();
        return view;
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.EVENTS;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.HOME;
    }

    private void setUpEventList() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new EventListAdapter(getActivity(), presenter.getEventList());
        recyclerView.setAdapter(adapter);
    }

}
