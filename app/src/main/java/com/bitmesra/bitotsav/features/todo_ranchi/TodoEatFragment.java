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

public class TodoEatFragment extends BaseFragment {

    @BindView(R.id.todo_partial_recycler_view)
    RecyclerView todoPartialRecyclerView;
    TodoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_partial, container, false);
        ButterKnife.bind(this, view);
        List<TodoModel> list = new ArrayList<>();
        list.add(new TodoModel("Kaveri Restaurant", getString(R.string.todo_kaveri), "http://www.bitotsav.in/events/kaveri.png"));
        list.add(new TodoModel("Kathi Kabab", getString(R.string.todo_kathi), "http://www.bitotsav.in/events/kathi.png"));
        list.add(new TodoModel("Snacks Valley", getString(R.string.todo_snacks_valley), "http://www.bitotsav.in/events/snacks_valley.png"));
        list.add(new TodoModel("Subway", getString(R.string.todo_subway), "http://www.bitotsav.in/events/subway.png"));
        list.add(new TodoModel("Nirvana", getString(R.string.todo_nirvana), "http://www.bitotsav.in/events/nirvana.png"));
        list.add(new TodoModel("Royal Retreat", getString(R.string.todo_royal_retreat), "http://www.bitotsav.in/events/royal_retreat.png"));
        list.add(new TodoModel("BNR Chanakya", getString(R.string.todo_bnr), "http://www.bitotsav.in/events/bnr.png"));
        list.add(new TodoModel("Radisson Blu", getString(R.string.todo_radisson), "http://www.bitotsav.in/events/radisson.png"));
        list.add(new TodoModel("Dominos", getString(R.string.todo_dominos), "http://www.bitotsav.in/events/dominos.png"));
        list.add(new TodoModel("KFC", getString(R.string.todo_kfc), "http://www.bitotsav.in/events/kfc.png"));
        list.add(new TodoModel("Madhuban", getString(R.string.todo_madhuban), "http://www.bitotsav.in/events/madhuban.png"));
        list.add(new TodoModel("Urban Brava", getString(R.string.todo_urban_brava), "http://www.bitotsav.in/events/urban_brava.png"));
        list.add(new TodoModel("Capitol Hill", getString(R.string.todo_capitol_hill), "http://www.bitotsav.in/events/capitol_hill.png"));
        adapter = new TodoAdapter(getActivity(), list);
        todoPartialRecyclerView.setHasFixedSize(true);
        todoPartialRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        todoPartialRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.TODOEAT;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.TODORANCHI;
    }
}
