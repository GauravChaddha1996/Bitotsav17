package com.bitmesra.bitotsav.features.todo_ranchi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Batdroid on 7/3/17 for Bitotsav.
 */

public class TodoRanchiFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todoranchi, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.TODORANCHI;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.HOME;
    }

    @OnClick(R.id.todo_eat)
    void toeat() {
        ((MainActivity) getActivity()).setFragment(IdForFragment.TODOEAT);
    }

    @OnClick(R.id.todo_sightseeing)
    void todoSightseeing() {
        ((MainActivity) getActivity()).setFragment(IdForFragment.TODOSIGHT);
    }

    @OnClick(R.id.todo_gaming)
    void toGaming() {
        ((MainActivity) getActivity()).setFragment(IdForFragment.TODOGAMING);
    }
}
