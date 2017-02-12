package com.bitmesra.bitotsav.features.details;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.events.EventDto;
import com.bitmesra.bitotsav.features.EventDtoType;
import com.bitmesra.bitotsav.ui.CustomTextView;
import com.bitmesra.bitotsav.utils.Utils;
import com.google.firebase.messaging.FirebaseMessaging;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.detail_desc)
    CustomTextView desc;
    @BindView(R.id.detail_rules)
    CustomTextView rules;
    @BindView(R.id.detail_money)
    CustomTextView money;
    @BindView(R.id.star_subscribe)
    FloatingActionButton subscribeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flagship_detail);
        ButterKnife.bind(this);
        eventName = getIntent().getStringExtra("eventName");
        eventDtoType = getIntent().getIntExtra("eventDtoType", EventDtoType.TYPE_FLAGSHIP);
        fetch = getIntent().getBooleanExtra("fetchNetwork", true);
        toolbarTitle.setText(eventName);
        toolbarTitle.setAlpha(0f);
        toolbarTitle.animate().alpha(1f).setDuration(1000).start();
        timeVenue.setTranslationY(-200.0f);
        timeVenue.animate().translationY(0f).setDuration(1000).start();
        desc.setTranslationY(Utils.getScreenHeight(this));
        desc.animate().translationY(0).setDuration(1000).start();
        rules.setTranslationY(Utils.getScreenHeight(this));
        rules.animate().translationY(0).setDuration(1000).start();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new DetailsPresenter(this, this);
        presenter.getDetailsDtoFromRealm(eventName);
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
                finish();
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

    @OnClick(R.id.star_subscribe)
    void onSubscribe() {
        if (presenter.isTopicSubscribed(eventName)) {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(eventName.replaceAll(" ", ""));
            subscribeButton.setImageDrawable(getDrawable(R.drawable.ic_no_bell));
            presenter.unsubscribeFromTopic(eventName);
        } else {
            subscribeButton.setImageDrawable(getDrawable(R.drawable.ic_bell));
            FirebaseMessaging.getInstance().subscribeToTopic(eventName.replaceAll(" ", ""));
            presenter.subscribeToTopic(eventName);
        }
    }
}
