package com.bitmesra.bitotsav.features.nights;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.database.models.nights.NightsModel;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.ui.AchievementHelper;
import com.bitmesra.bitotsav.ui.CustomTextView;
import com.bitmesra.bitotsav.utils.ItemClickSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NightFragment extends BaseFragment implements NightViewInterface {


    LinearLayoutManager layoutManager;
    NightListAdapter adapter;
    NightPresenter presenter;
    AchievementHelper achievementHelper;
    @BindView(R.id.timeline_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.mario_loading_image)
    ImageView marioLoadingImage;
    @BindView(R.id.mario_loading_text)
    CustomTextView marioLoadingText;
    @BindView(R.id.achievemnt_loading_holder)
    RelativeLayout achievemntLoadingHolder;


    public NightFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nights, container, false);
        ButterKnife.bind(this, view);
        presenter = new NightPresenter(getActivity(), this);
        setUpNightList();
        return view;
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.NIGHTS;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.HOME;
    }

    private void setUpNightList() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new NightListAdapter(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        achievementHelper = new AchievementHelper(getActivity(), achievemntLoadingHolder, marioLoadingImage, marioLoadingText);
        presenter.getNightsEvents();
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView1, position, v) -> {
            Intent intent = new Intent(getActivity(), NightDetailsActivity.class);
            intent.putExtra("nightId", adapter.getItem(position).getId());
            startActivity(intent);
        });
        refreshLayout.setOnRefreshListener(() -> presenter.getNightsEvents());
    }

    @Override
    public void updateList(List<NightsModel> list) {
        adapter.setItems(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showAchievment() {
        refreshLayout.setRefreshing(true);
        achievementHelper.startLoading();
    }

    @Override
    public void hideAchievment() {
        refreshLayout.setRefreshing(false);
        achievementHelper.stopLoading();
    }

    @Override
    public void errorAchievment() {
        refreshLayout.setRefreshing(false);
        achievementHelper.errorLoading();
    }
}
