package com.bitmesra.bitotsav.features.home;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.database.models.home.NotificationItem;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.home.adapter.HomeNotificationAdapter;
import com.bitmesra.bitotsav.ui.phoenix.PullToRefreshView;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends BaseFragment implements HomeViewInterface {


    HomePresenter presenter;
    @BindView(R.id.homeSliderLayout)
    SliderLayout sliderLayout;
    @BindView(R.id.homeNotificationRecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.home_pull_to_refresh)
    PullToRefreshView pullToRefreshView;
    private boolean loading = false;
    private HomeNotificationAdapter adapter;
    private LinearLayoutManager manager;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        presenter = new HomePresenter(getActivity(), this);
        setUpSliderLayout();
        setUpNotificationRecyclerView();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        sliderLayout.startAutoCycle(4000, 4000, true);
    }

    @Override
    public void onStop() {
        super.onStop();
        sliderLayout.stopAutoCycle();
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.HOME;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return null;
    }

    private void setUpSliderLayout() {
        TextSliderView view = new TextSliderView(getActivity());
        view.description("Bitotsav 17")
                .image(R.drawable.home1);
        TextSliderView view2 = new TextSliderView(getActivity());
        view2.description("Party at BIT 17")
                .image(R.drawable.home2);
        TextSliderView view3 = new TextSliderView(getActivity());
        view3.description("Party3 at BIT 17")
                .image(R.drawable.home1);
        TextSliderView view4 = new TextSliderView(getActivity());
        view4.description("Party4 at BIT 17")
                .image(R.drawable.home2);
        sliderLayout.addSlider(view);
        sliderLayout.addSlider(view2);
        sliderLayout.addSlider(view3);
        sliderLayout.addSlider(view4);
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setDuration(4000);
    }

    private void setUpNotificationRecyclerView() {
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter = new HomeNotificationAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(createScrollListener());
        pullToRefreshView.setOnRefreshListener(() -> {
            pullToRefreshView.setRefreshing(true);
            presenter.getLatestNotifications(10);
        });
        presenter.getRecentNotifications();
    }


    @Override
    public void updateRecentNotifications(List<NotificationItem> notifications) {
        adapter.setItems(notifications);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateNextNotifications(List<NotificationItem> notifications) {
        if (notifications.size() == 0) {
            adapter.removeProgressBar();
            recyclerView.removeOnScrollListener(createScrollListener());
        } else {
            adapter.addNextItems(notifications);
            loading = !loading;
        }
    }

    @Override
    public void updateLatestNotifications(List<NotificationItem> notifications) {
        adapter.addLatestItems(notifications);
        recyclerView.scrollToPosition(0);
        pullToRefreshView.setRefreshing(false);
    }

    public RecyclerView.OnScrollListener createScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (manager.findLastVisibleItemPosition() >= manager.getItemCount() - 2) {
                    if (!loading) {
                        presenter.getNextNotifications(10);
                        loading = !loading;
                    }
                }
            }
        };
    }
}
