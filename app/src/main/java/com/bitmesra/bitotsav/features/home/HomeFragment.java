package com.bitmesra.bitotsav.features.home;


import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.EventDtoType;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.details.DetailsActivity;
import com.bitmesra.bitotsav.features.home.adapter.HomeNotificationAdapter;
import com.bitmesra.bitotsav.ui.CustomTextView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends BaseFragment implements HomeViewInterface {


    HomePresenter presenter;
    @BindView(R.id.homeSliderLayout)
    SliderLayout sliderLayout;
    @BindView(R.id.homeNotificationRecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.homeNotificationLoadingImage)
    ImageView loadingImage;
    @BindView(R.id.homeNotificationLoadingText)
    CustomTextView loadingText;
    private boolean stopAnimation = false;
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
        TextSliderView nukkad = new TextSliderView(getActivity());
        nukkad.description("Nukkad");
        nukkad.image(R.drawable.nukkad);
        nukkad.setOnSliderClickListener(slider -> startDetailsActivity("Nukkad"));
        TextSliderView dancesaga = new TextSliderView(getActivity());
        dancesaga.image(R.drawable.dancesaga);
        dancesaga.description("Dance saga");
        dancesaga.setOnSliderClickListener(slider -> startDetailsActivity("Dance saga"));
        TextSliderView mun = new TextSliderView(getActivity());
        mun.image(R.drawable.mun);
        mun.description("MUN");
        mun.setOnSliderClickListener(slider -> startDetailsActivity("MUN"));
        TextSliderView saptak = new TextSliderView(getActivity());
        saptak.image(R.drawable.saptak);
        saptak.description("Saptak");
        saptak.setOnSliderClickListener(slider -> startDetailsActivity("Saptak"));
        TextSliderView stomptheyard = new TextSliderView(getActivity());
        stomptheyard.image(R.drawable.stomptheyard);
        stomptheyard.description("Stomp the yard");
        stomptheyard.setOnSliderClickListener(slider -> startDetailsActivity("Stomp the yard"));

        sliderLayout.addSlider(nukkad);
        sliderLayout.addSlider(dancesaga);
        sliderLayout.addSlider(mun);
        sliderLayout.addSlider(saptak);
        sliderLayout.addSlider(stomptheyard);
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setDuration(4000);
        sliderLayout.setVisibility(View.GONE);
    }

    private void setUpNotificationRecyclerView() {
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter = new HomeNotificationAdapter(getActivity(), presenter.getNotifications());
        if (adapter.getItemCount() == 0) {
            showLoading();
            presenter.getNotificationsFromServer();
        }
        recyclerView.setAdapter(adapter);
        startAnimation();
    }

    private void startDetailsActivity(String name) {

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                (View) sliderLayout, "event_image");
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("eventName", name);
        intent.putExtra("fetchNetwork", true);
        intent.putExtra("eventDtoType", EventDtoType.TYPE_FLAGSHIP);
        startActivity(intent,options.toBundle());
    }

    @Override
    public void showLoading() {
        loadingImage.setVisibility(View.VISIBLE);
        loadingText.setVisibility(View.VISIBLE);
        AnimatedVectorDrawable vectorDrawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.play_pause_repeat_animated_vector);
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
        stopAnimation = true;
        loadingImage.setImageDrawable(getResources().getDrawable(R.drawable.error_mario));
        loadingText.setText("Oops!");
    }

    private void startAnimation() {
        sliderLayout.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeInDown)
                .playOn(sliderLayout);
    }
}
