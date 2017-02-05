package com.bitmesra.bitotsav.features.events;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.MainActivity;
import com.bitmesra.bitotsav.features.events.adapters.EventListAdapter;
import com.bitmesra.bitotsav.features.events.timeline.TimelineActivity;
import com.bitmesra.bitotsav.utils.ItemClickSupport;

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
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView1, position, v) ->
        {
            Intent intent = new Intent(getActivity(), TimelineActivity.class);
            switch (adapter.getItem(position).getType()) {
                case "Day 1":
                    intent.putExtra("day", 1);
                    startActivityForResult(intent, 5);
                    break;
                case "Day 2":
                    intent.putExtra("day", 2);
                    startActivityForResult(intent, 5);
                    break;
                case "Day 3":
                    intent.putExtra("day", 3);
                    startActivityForResult(intent, 5);
                    break;
                case "Day 4":
                    intent.putExtra("day", 4);
                    startActivityForResult(intent, 5);
                    break;
                case "Informals":
                    ((MainActivity)getActivity()).setFragment(IdForFragment.INFORMAL);
                    break;
            }
        });
    }

}
