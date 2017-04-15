package com.bitmesra.bitotsav.features.tshirt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.IdForFragment;

import butterknife.ButterKnife;

/**
 * Created by Batdroid on 6/3/17 for Bitotsav.
 */

public class TShirtDemoFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tshirt_demo, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.TSHIRTDEMO;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.TSHIRT;
    }

}
