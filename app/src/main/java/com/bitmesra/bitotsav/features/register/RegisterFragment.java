package com.bitmesra.bitotsav.features.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Batdroid on 5/3/17 for Bitotsav.
 */

public class RegisterFragment extends BaseFragment {
    public RegisterFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.REGISTER;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.HOME;
    }

    @OnClick(R.id.single_player_registration)
    public void singlePLayerRegistration() {
        ((MainActivity) getActivity()).setFragment(IdForFragment.SINGLEPLAYER);
    }

    @OnClick(R.id.multiplayer_registration)
    public void multiplayerRegistration() {
        ((MainActivity) getActivity()).setFragment(IdForFragment.MULTIPLAYER);
    }

    @OnClick(R.id.pay_registration)
    public void payment() {
        ((MainActivity) getActivity()).setFragment(IdForFragment.PAY);
    }

    @OnClick(R.id.register_instructions)
    public void instructions() {
        ((MainActivity)getActivity()).setFragment(IdForFragment.INSTRUCTIONS);
    }
}
