package com.bitmesra.bitotsav.features.passes;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.IdForFragment;

public class PassesFragment extends BaseFragment {


    public PassesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_passes, container, false);
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.PASSES;
    }

    @Override
    public IdForFragment getBackToClazz() {
        return IdForFragment.HOME;
    }
}
