package com.bitmesra.bitotsav.features.todo_ranchi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.database.models.todoranchi.TodoModel;
import com.bitmesra.bitotsav.features.IdForFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Batdroid on 7/3/17 for Bitotsav.
 */

public class TodoGamingFragment extends BaseFragment {

    @BindView(R.id.todo_partial_recycler_view)
    RecyclerView todoPartialRecyclerView;
    TodoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_partial, container, false);
        ButterKnife.bind(this, view);
        List<TodoModel> list = new ArrayList<>();
        list.add(new TodoModel("Bunk n Bonk", getString(R.string.todo_bunk), "http://www.bitotsav.in/events/bunk.png"));
        list.add(new TodoModel("Amoeba Bowling alley and Gaming Zone", getString(R.string.todo_ameoba), "http://www.bitotsav.in/events/ameoba.png"));
        list.add(new TodoModel("Spring City Mall", getString(R.string.todo_spring), "http://www.bitotsav.in/events/spring.png"));
        list.add(new TodoModel("Fun Cinema", getString(R.string.todo_fun), "http://www.bitotsav.in/events/fun.png"));
        adapter = new TodoAdapter(getActivity(), list);
        todoPartialRecyclerView.setHasFixedSize(true);
        todoPartialRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        todoPartialRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.TODOGAMING;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.TODORANCHI;
    }
}
