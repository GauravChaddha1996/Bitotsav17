package com.bitmesra.bitotsav.features.events.timeline;


import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
    @BindView(R.id.loadingImage)
    ImageView loadingImage;
    @BindView(R.id.loadingText)
    CustomTextView loadingText;
    @BindView(R.id.mario_loading_image)
    ImageView marioLoadingImage;
    @BindView(R.id.mario_loading_text)
    CustomTextView marioLoadingText;

    LinearLayoutManager layoutManager;
    TimelineListAdapter adapter;
    TimelinePresenter presenter;
    int dayNumber;
    private boolean stopAnimation = false;
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
        achievementHelper = new AchievementHelper(getActivity(), marioLoadingImage, marioLoadingText);
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
            intent.putExtra("fetchNetwork", false);
            intent.putExtra("firstTime", false);
            intent.putExtra("eventDtoType", Utils.findEventDtoDayType(dayNumber));
            startActivityForResult(intent, 6993);
        });
        refreshLayout.setOnRefreshListener(() -> presenter.fetchTimelineEvents(dayNumber));
    }

    @Override
    public void updateTimelineEvents(List<EventDto> items) {
        hideLoading();
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 6993) {
            presenter.loadTimelineFromRealm(dayNumber);
        }
    }

    @Override
    public void showLoading() {
        loadingImage.setVisibility(View.VISIBLE);
        loadingText.setVisibility(View.VISIBLE);
        AnimatedVectorDrawable vectorDrawable = (AnimatedVectorDrawable) getActivity().getDrawable(R.drawable.play_pause_repeat_animated_vector);
        loadingImage.setImageDrawable(vectorDrawable);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                vectorDrawable.start();
                if (!stopAnimation) sendEmptyMessageDelayed(0, 500);
            }
        };
        handler.sendEmptyMessage(0);
    }

    @Override
    public void hideLoading() {
        stopAnimation = true;
        loadingImage.setVisibility(View.GONE);
        loadingText.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        if (adapter.getItemCount() == 0) {
            stopAnimation = true;
            loadingImage.setImageDrawable(getResources().getDrawable(R.drawable.error_mario));
            loadingText.setText("Oops!");
        }
    }

    @Override
    public void showAchievment() {
        refreshLayout.setRefreshing(true);
        if (firstTime) {
            refreshLayout.setEnabled(false);
            achievementHelper.startLoading();
        }
    }

    @Override
    public void hideAchievment() {
        refreshLayout.setRefreshing(false);
        if (firstTime) {
            refreshLayout.setEnabled(true);
            achievementHelper.stopLoading();
        }
    }

    @Override
    public void errorAchievment() {
        refreshLayout.setRefreshing(false);
        if (firstTime) {
            achievementHelper.errorLoading();
        }
    }
}
