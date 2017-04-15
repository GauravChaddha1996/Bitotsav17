package com.bitmesra.bitotsav.features.tshirt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Batdroid on 6/3/17 for Bitotsav.
 */

public class TShirtFragment extends BaseFragment {

    @BindView(R.id.tshirt_demo)
    CardView tshirtDemo;
    @BindView(R.id.tshirt_book)
    CardView tshirtBook;
    @BindView(R.id.tshirt_payment)
    CardView tshirtPayment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tshirt, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public IdForFragment getFragmentId() {
        return IdForFragment.TSHIRT;
    }

    @Override
    public IdForFragment getBackToFragmentId() {
        return IdForFragment.HOME;
    }

    @OnClick(R.id.tshirt_book)
    void setTshirtBook() {
        ((MainActivity) getActivity()).setFragment(IdForFragment.BOOKTSHIRT);
    }

    @OnClick(R.id.tshirt_payment)
    void payForTShirt() {
        ((MainActivity) getActivity()).setFragment(IdForFragment.PAYTSHIRT);
    }

    @OnClick(R.id.tshirt_demo)
    void demo() {
        ((MainActivity) getActivity()).setFragment(IdForFragment.TSHIRTDEMO);
    }


}
