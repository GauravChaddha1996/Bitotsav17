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

public class TodoSIghtFragment extends BaseFragment {

    @BindView(R.id.todo_partial_recycler_view)
    RecyclerView todoPartialRecyclerView;
    TodoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_partial, container, false);
        ButterKnife.bind(this, view);
        List<TodoModel> list = new ArrayList<>();
        list.add(new TodoModel("Dassam Falls", getString(R.string.todo_dassam), "http://www.bitotsav.in/events/dassam.png"));
        list.add(new TodoModel("Jonah Falls", getString(R.string.todo_jonah), "http://www.bitotsav.in/events/jonah.png"));
        list.add(new TodoModel("Hundru Falls", getString(R.string.todo_hundru), "http://www.bitotsav.in/events/hundru.png"));
        list.add(new TodoModel("Rock Garden", getString(R.string.todo_rock_gardens), "http://www.bitotsav.in/events/rock_garden.png"));
        list.add(new TodoModel("Ranchi lake", getString(R.string.todo_ranchi_lake), "http://www.bitotsav.in/events/ranchi_lake.png"));
        list.add(new TodoModel("Birsa munda zoological park", getString(R.string.todo_birsa_zoo), "http://www.bitotsav.in/events/birsa_zoo.png"));
        list.add(new TodoModel("Tagore hill", getString(R.string.todo_tagore_hill), "http://www.bitotsav.in/events/tagore_hill.png"));
        list.add(new TodoModel("Panch ghat", getString(R.string.todo_panch_ghat), "http://www.bitotsav.in/events/panch_ghat.png"));
        list.add(new TodoModel("Kanke dam", getString(R.string.todo_kanke_dam), "http://www.bitotsav.in/events/kanke_dam.png"));
        list.add(new TodoModel("Birsa deer park", getString(R.string.todo_birsa_deer), "http://www.bitotsav.in/events/birsa_deer.png"));
        list.add(new TodoModel("Patratu valley", getString(R.string.todo_patratu_valley), "http://www.bitotsav.in/events/patratu_valley.png"));
        adapter = new TodoAdapter(getActivity(), list);
        todoPartialRecyclerView.setHasFixedSize(true);
        todoPartialRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        todoPartialRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.TODOSIGHT;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.TODORANCHI;
    }
}
