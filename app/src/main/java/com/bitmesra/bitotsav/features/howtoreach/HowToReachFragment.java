package com.bitmesra.bitotsav.features.howtoreach;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.IdForFragment;

/**
 * Created by Batdroid on 6/3/17 for Bitotsav.
 */

public class HowToReachFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_howtoreach, container, false);
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.HOWTOREACH;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.HOME;
    }
}
