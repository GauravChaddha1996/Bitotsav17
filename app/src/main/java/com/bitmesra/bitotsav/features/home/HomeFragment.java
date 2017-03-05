package com.bitmesra.bitotsav.features.home;


import android.content.Intent;
import android.os.Bundle;
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
import com.bitmesra.bitotsav.ui.AchievementHelper;
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
    @BindView(R.id.mario_loading_image)
    ImageView marioLoadingImage;
    @BindView(R.id.mario_loading_text)
    CustomTextView marioLoadingText;
    @BindView(R.id.achievemnt_loading_holder)
    View achievemntHolder;

    private boolean stopAnimation = false;
    private HomeNotificationAdapter adapter;
    private LinearLayoutManager manager;
    private AchievementHelper achievementHelper;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        presenter = new HomePresenter(getActivity(), this);
        achievementHelper = new AchievementHelper(getActivity(), achievemntHolder, marioLoadingImage, marioLoadingText);
        setUpSliderLayout();
        setUpNotificationRecyclerView();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        sliderLayout.startAutoCycle(4000, 4000, true);
    }

    @Override
    public void onPause() {
        super.onPause();
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

        TextSliderView mrmissbitotsav = new TextSliderView(getActivity());
        mrmissbitotsav.description("Mr. And Miss Bitotsav");
        mrmissbitotsav.image(R.drawable.mrmissbitotsav);
        mrmissbitotsav.setOnSliderClickListener(slider -> startDetailsActivity("Mr. And Miss Bitotsav"));

        TextSliderView dancesaga = new TextSliderView(getActivity());
        dancesaga.image(R.drawable.dancesaga);
        dancesaga.description("Dance Saga");
        dancesaga.setOnSliderClickListener(slider -> startDetailsActivity("Dance Saga"));

        TextSliderView mun = new TextSliderView(getActivity());
        mun.image(R.drawable.mun);
        mun.description("MUN");
        mun.setOnSliderClickListener(slider -> startDetailsActivity("MUN"));

        TextSliderView saptak = new TextSliderView(getActivity());
        saptak.image(R.drawable.saptak);
        saptak.description("Saptak");
        saptak.setOnSliderClickListener(slider -> startDetailsActivity("Saptak"));

        TextSliderView rhapsody = new TextSliderView(getActivity());
        rhapsody.image(R.drawable.rhapsody);
        rhapsody.description("Rhapsody");
        rhapsody.setOnSliderClickListener(slider -> startDetailsActivity("Rhapsody"));

        TextSliderView stomptheyard = new TextSliderView(getActivity());
        stomptheyard.image(R.drawable.stomptheyard);
        stomptheyard.description("Stomp The Yard");
        stomptheyard.setOnSliderClickListener(slider -> startDetailsActivity("Stomp The Yard"));

        TextSliderView talkies = new TextSliderView(getActivity());
        talkies.image(R.drawable.talkies);
        talkies.description("Talkies");
        talkies.setOnSliderClickListener(slider -> startDetailsActivity("Talkies"));

        sliderLayout.addSlider(nukkad);
        sliderLayout.addSlider(mrmissbitotsav);
        sliderLayout.addSlider(rhapsody);
        sliderLayout.addSlider(dancesaga);
        sliderLayout.addSlider(mun);
        sliderLayout.addSlider(saptak);
        sliderLayout.addSlider(stomptheyard);
        sliderLayout.addSlider(talkies);


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
        startActivity(intent, options.toBundle());
    }

    @Override
    public void showLoading() {
        achievementHelper.startLoading();
    }

    @Override
    public void hideLoading() {
        achievementHelper.stopLoading();
    }

    @Override
    public void showError() {
        achievementHelper.errorLoading();
    }

    private void startAnimation() {
        sliderLayout.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeInDown)
                .playOn(sliderLayout);
    }
}
