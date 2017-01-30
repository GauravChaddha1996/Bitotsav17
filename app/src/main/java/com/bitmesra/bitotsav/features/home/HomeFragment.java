package com.bitmesra.bitotsav.features.home;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.database.models.home.BitotsavNotification;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.home.adapter.HomeNotificationAdapter;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends BaseFragment implements HomeViewInterface {


    HomePresenter homePresenter;
    @BindView(R.id.homeSliderLayout)
    SliderLayout homeSliderLayout;
    @BindView(R.id.homeNotificationRecyclerView)
    RecyclerView homeNotificationRecyclerView;
    private HomeNotificationAdapter homeNotificationAdapter;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        homePresenter = new HomePresenter(getActivity(), this);
        setUpSliderLayout();
        setUpNotificationRecyclerView();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        homeSliderLayout.startAutoCycle(4000, 4000, true);
    }

    @Override
    public void onStop() {
        super.onStop();
        homeSliderLayout.stopAutoCycle();
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.HOME;
    }

    @Override
    public IdForFragment getBackToClazz() {
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
        homeSliderLayout.addSlider(view);
        homeSliderLayout.addSlider(view2);
        homeSliderLayout.addSlider(view3);
        homeSliderLayout.addSlider(view4);
        homeSliderLayout.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        homeSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        homeSliderLayout.setDuration(4000);
    }

    private void setUpNotificationRecyclerView() {
        homeNotificationRecyclerView.setHasFixedSize(true);
        homeNotificationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeNotificationAdapter = new HomeNotificationAdapter(getActivity(), homePresenter.getNotificationData());
        homeNotificationRecyclerView.setAdapter(homeNotificationAdapter);
    }

    @Override
    public void updateNotificationData(List<BitotsavNotification> notifications) {
        homeNotificationAdapter.setItems(notifications);
        homeNotificationAdapter.notifyDataSetChanged();
    }
}
