package com.bitmesra.bitotsav.features.details;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.events.EventDto;
import com.bitmesra.bitotsav.features.EventDtoType;
import com.bitmesra.bitotsav.ui.AchievementUnlocked;
import com.bitmesra.bitotsav.ui.CustomTextView;
import com.bitmesra.bitotsav.utils.Utils;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class DetailsActivity extends AppCompatActivity implements DetailsViewInterface {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    CustomTextView toolbarTitle;

    String eventName;
    int eventDtoType;
    boolean fetch = true;
    DetailsPresenter presenter;
    @BindView(R.id.detail_time_venue)
    CustomTextView timeVenue;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.detail_desc)
    CustomTextView desc;
    @BindView(R.id.detail_rules)
    CustomTextView rules;
    @BindView(R.id.detail_money)
    CustomTextView money;
    @BindView(R.id.divider)
    View divider;
    @BindView(R.id.star_subscribe)
    FloatingActionButton subscribeButton;
    @BindView(R.id.loadingImage)
    ImageView loadingImage;
    @BindView(R.id.loadingText)
    CustomTextView loadingText;
    @BindView(R.id.frame_image)
    ImageView frame_image;
    @BindView(R.id.background_image)
    ImageView background_image;
    AchievementUnlocked achievement;
    private boolean firstTime = true;
    private boolean stopAnimation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flagship_detail);
        ButterKnife.bind(this);
        eventName = getIntent().getStringExtra("eventName");
        eventDtoType = getIntent().getIntExtra("eventDtoType", EventDtoType.TYPE_FLAGSHIP);
        fetch = getIntent().getBooleanExtra("fetchNetwork", true);
        firstTime = getIntent().getBooleanExtra("firstTime", true);

        presenter = new DetailsPresenter(this, this);
        String storedImageName = presenter.getImageName(eventName);
        if (storedImageName != null) {
            background_image.setImageDrawable(getResources().getDrawable(
                    getResources().getIdentifier(storedImageName, "drawable", getPackageName())
            ));
        }
        desc.setText(presenter.getDescription(eventName));
        toolbarTitle.setText(eventName);
        toolbarTitle.setAlpha(0f);
        toolbarTitle.animate().alpha(1f).setDuration(1000).start();
        frame_image.setAlpha(0f);
        frame_image.animate().alpha(1f).setDuration(1000).start();
        timeVenue.setTranslationY(-200.0f);
        timeVenue.animate().translationY(0f).setDuration(1000).start();
        desc.setTranslationY(Utils.getScreenHeight(this));
        desc.animate().translationY(0).setDuration(1000).start();
        divider.setTranslationY(Utils.getScreenHeight(this));
        divider.animate().translationY(0).setDuration(1200).start();
        rules.setTranslationY(Utils.getScreenHeight(this));
        rules.animate().translationY(0).setDuration(1400).start();
        money.setTranslationY(Utils.getScreenHeight(this));
        money.animate().translationY(0).setDuration(1500).start();


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter.getDetailsDtoFromRealm(eventName);
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        refreshLayout.setOnRefreshListener(() -> presenter.fetchDetailsDto(eventName, eventDtoType));
        if (fetch) {
            presenter.fetchDetailsDto(eventName, eventDtoType);
        }
        if (presenter.isTopicSubscribed(eventName)) {
            subscribeButton.setImageDrawable(getDrawable(R.drawable.ic_bell));
        } else {
            subscribeButton.setImageDrawable(getDrawable(R.drawable.ic_no_bell));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateDetailView(EventDto eventDto) {
        rules.setText(eventDto.getRules());
        timeVenue.setText(eventDto.getTime() + " at " + eventDto.getVenue());
        money.setText("Prize money: " + eventDto.getMoney());
    }

    @Override
    protected void onDestroy() {
        try {
            achievement.dismissWithoutAnimation();
        } catch (Exception e) {

        } finally {
            super.onDestroy();
        }
    }

    @OnClick(R.id.star_subscribe)
    void onSubscribe() {
        if (presenter.isTopicSubscribed(eventName)) {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(eventName.replaceAll(" ", ""));
            subscribeButton.setImageDrawable(getDrawable(R.drawable.ic_no_bell));
            Snackbar.make(desc, "You have unsubscribed from " + eventName, Snackbar.LENGTH_LONG).show();
            presenter.unsubscribeFromTopic(eventName);
            setResult(21);
        } else {
            subscribeButton.setImageDrawable(getDrawable(R.drawable.ic_bell));
            Snackbar.make(desc, "You will now receive all FUTURE UPDATES for " + eventName, Snackbar.LENGTH_LONG).show();
            FirebaseMessaging.getInstance().subscribeToTopic(eventName.replaceAll(" ", ""));
            presenter.subscribeToTopic(eventName);
            setResult(21);
        }
    }


    @Override
    public void showLoading() {
        loadingImage.setVisibility(View.VISIBLE);
        loadingText.setVisibility(View.VISIBLE);
        AnimatedVectorDrawable vectorDrawable = (AnimatedVectorDrawable) getDrawable(R.drawable.play_pause_repeat_animated_vector);
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

    @Override
    public void showAchievment() {
        achievement = new AchievementUnlocked(this).alignTop(false).setYOffset(100)
                .isLarge(false)
                .isPersistent(false)
                .isRounded(true)
                .setIcon(getResources().getDrawable(R.drawable.monster))
                .setTitle("Loading...")
                .build();
        refreshLayout.setRefreshing(true);
        if (firstTime) {
            refreshLayout.setEnabled(false);
            achievement.show();
        }
    }

    @Override
    public void hideAchievment() {
        refreshLayout.setRefreshing(false);
        if (firstTime) {
            achievement.title.animate().alpha(0.0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    achievement.title.setText("Done.");
                    fadeInHideAchievment();
                }
            }).start();
        }
    }

    private void fadeInHideAchievment() {
        achievement.title.animate().alpha(1.0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                firstTime = false;
                refreshLayout.setEnabled(true);
                Observable.just(1).delay(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(integer -> achievement.dismiss());
            }
        }).start();
    }

    @Override
    public void errorAchievment() {
        refreshLayout.setRefreshing(false);
        if (firstTime) {
            achievement.title.animate().alpha(0.0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    achievement.title.setText("Oops!");
                    achievement.title.setTextColor(getResources().getColor(R.color.page_indicator_dark_selected));
                    fadeInHideAchievment();
                }
            }).start();
        }
    }
}
