package com.bitmesra.bitotsav.features.events.informal;


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
import com.bitmesra.bitotsav.features.events.adapters.InformalListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformalFragment extends BaseFragment implements InformalViewInterface {


    @BindView(R.id.informal_recycler_view)
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    InformalListAdapter adapter;
    InformalPresenter presenter;

    public InformalFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_informal, container, false);
        ButterKnife.bind(this, view);
        presenter = new InformalPresenter(getActivity(), this);
        setUpInformalList();
        return view;
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.INFORMAL;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.EVENTS;
    }

    private void setUpInformalList() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new InformalListAdapter(getActivity(), presenter.getInformalEvents());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
