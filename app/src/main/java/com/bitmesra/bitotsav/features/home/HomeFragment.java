package com.bitmesra.bitotsav.features.home;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.database.models.home.NotificationDto;
import com.bitmesra.bitotsav.database.models.home.NotificationItem;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.home.adapter.HomeNotificationAdapter;
import com.bitmesra.bitotsav.ui.phoenix.PullToRefreshView;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import net.darkion.AchievementUnlockedLib.AchievementUnlocked;

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
    AchievementUnlocked test;
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
        if (Build.VERSION.SDK_INT >= 23) {

            if (!Settings.canDrawOverlays(getActivity())) {
                new AlertDialog.Builder(getActivity())

                        .setMessage("Starting from Android 6, " + getResources().getString(R.string.app_name) + " needs permission to display notifications. Click enable to proceed")
                        .setPositiveButton("Enable", (dialog, which) -> androidM())

                        .show();
            } else {
                test = new AchievementUnlocked(getActivity())
                        .setIcon(getResources().getDrawable(R.drawable.monster))
                        .alignTop(false)
                        .isPersistent(true)
                        .isLarge(false)
                        .setTitle("Loading new notifs...");
                setUpSliderLayout();
                setUpNotificationRecyclerView();
            }

        } else {
            test = new AchievementUnlocked(getActivity())
                    .setIcon(getResources().getDrawable(R.drawable.monster))
                    .setTitle("Loading new notifs...")
                    .build();
            setUpSliderLayout();
            setUpNotificationRecyclerView();
        }
        return view;
    }

    public void androidM() {
        Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getActivity().getPackageName()));
        startActivityForResult(intent, 123);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123) {
            if (!android.provider.Settings.canDrawOverlays(getActivity())) {
                Toast.makeText(getActivity(), "Couldn't get the permission, terminating process", Toast.LENGTH_LONG).show();
                getActivity().finish();
            } else {
                test = new AchievementUnlocked(getActivity())
                        .setIcon(getResources().getDrawable(R.drawable.monster))
                        .setTitle("Loading new notifs...")
                        .build();
                setUpSliderLayout();
                setUpNotificationRecyclerView();
            }
        }
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
        presenter.saveNotifications(new NotificationDto(adapter.getTop10Items()));
    }

    @Override
    public void showLoadingToast() {
        test.build().show();
    }

    @Override
    public void hideLoadingToast() {
        test.dismissWithoutAnimation();
        test.setTitle("Loading Complete...").build().show();
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                test.dismiss();
            }
        };
        handler.sendEmptyMessageDelayed(0, 1000);
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
