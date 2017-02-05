package com.bitmesra.bitotsav.features.events.timeline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.models.events.TimelineItem;
import com.bitmesra.bitotsav.features.events.adapters.TimelineListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimelineActivity extends AppCompatActivity implements TimelineViewInterface {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.timeline_recycler_view)
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    TimelineListAdapter adapter;
    TimelinePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new TimelinePresenter(this, this);
        setUpTimelineView();
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

    private void setUpTimelineView() {
        int dayNumber = getIntent().getIntExtra("day", 1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new TimelineListAdapter(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        presenter.getTimelineEvents(dayNumber);
    }

    @Override
    public void updateTimelineEvents(List<TimelineItem> items) {
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }
}
