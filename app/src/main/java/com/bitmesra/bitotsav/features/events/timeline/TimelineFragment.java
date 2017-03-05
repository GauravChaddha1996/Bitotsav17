package com.bitmesra.bitotsav.features.events.timeline;


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

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.database.models.events.EventDto;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.MainActivity;
import com.bitmesra.bitotsav.features.details.DetailsActivity;
import com.bitmesra.bitotsav.features.events.adapters.TimelineListAdapter;
import com.bitmesra.bitotsav.ui.AchievementHelper;
import com.bitmesra.bitotsav.ui.CustomTextView;
import com.bitmesra.bitotsav.utils.ItemClickSupport;
import com.bitmesra.bitotsav.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimelineFragment extends BaseFragment implements TimelineViewInterface {


    @BindView(R.id.timeline_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.mario_loading_image)
    ImageView marioLoadingImage;
    @BindView(R.id.mario_loading_text)
    CustomTextView marioLoadingText;
    @BindView(R.id.achievemnt_loading_holder)
    View achievemntHolder;

    LinearLayoutManager layoutManager;
    TimelineListAdapter adapter;
    TimelinePresenter presenter;
    int dayNumber;
    private boolean firstTime = true;
    private AchievementHelper achievementHelper;

    public TimelineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);
        ButterKnife.bind(this, view);
        presenter = new TimelinePresenter(getActivity(), this);
        setUpTimelineView();
        return view;
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.TIMELINE;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.EVENTS;
    }

    private void setUpTimelineView() {
        dayNumber = ((MainActivity) getActivity()).dayNumber;
        achievementHelper = new AchievementHelper(getActivity(), achievemntHolder, marioLoadingImage, marioLoadingText);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new TimelineListAdapter(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        presenter.getTimelineEvents(dayNumber);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView1, position, v) -> {
            Intent intent = new Intent(getActivity(), DetailsActivity.class);
            intent.putExtra("eventName", adapter.getEventName(position));
            intent.putExtra("id", adapter.getEventId(position));
            intent.putExtra("fetchNetwork", true);
            intent.putExtra("firstTime", true);
            intent.putExtra("eventDtoType", Utils.findEventDtoDayType(dayNumber));
            startActivityForResult(intent, 6993);
        });
        refreshLayout.setOnRefreshListener(() -> presenter.fetchTimelineEvents(dayNumber));
    }

    @Override
    public void updateTimelineEvents(List<EventDto> items) {
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 6993) {
            //presenter.loadTimelineFromRealm(dayNumber);
        }
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
