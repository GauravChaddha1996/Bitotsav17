package com.bitmesra.bitotsav.features.flagships;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.events.adapters.FlagshipListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlagshipFragment extends BaseFragment implements FlagshipViewInterface {


    @BindView(R.id.flagship_recycler_view)
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    FlagshipListAdapter adapter;
    FlagshipPresenter presenter;

    public FlagshipFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_flagship, container, false);
        ButterKnife.bind(this, view);
        presenter = new FlagshipPresenter(getActivity(), this);
        setUpFlagshipList();
        return view;
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.FLAGSHIP;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.EVENTS;
    }

    private void setUpFlagshipList() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new FlagshipListAdapter(getActivity(), presenter.getFlagshipEvents());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
