package com.bitmesra.bitotsav.features.events.timeline;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.IdForFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimelineFragment extends BaseFragment {


    public TimelineFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);
        ButterKnife.bind(this, view);
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.day1, R.id.day2, R.id.day3, R.id.day4})
    void dayItemClicked(View view) {
        Intent intent = new Intent(getActivity(), TimelineActivity.class);
        switch (view.getId()) {
            case R.id.day1:
                intent.putExtra("day", 1);
                break;
            case R.id.day2:
                intent.putExtra("day", 2);
                break;
            case R.id.day3:
                intent.putExtra("day", 3);
                break;
            case R.id.day4:
                intent.putExtra("day", 4);
                break;
        }
        startActivityForResult(intent, 5);
    }
}
