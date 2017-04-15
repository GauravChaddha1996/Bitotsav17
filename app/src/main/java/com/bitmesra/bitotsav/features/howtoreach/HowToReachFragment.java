package com.bitmesra.bitotsav.features.howtoreach;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.ui.CustomTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Batdroid on 6/3/17 for Bitotsav.
 */

public class HowToReachFragment extends BaseFragment {

    @BindView(R.id.instructions_text)
    CustomTextView instructionsText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_howtoreach, container, false);
        ButterKnife.bind(this, view);
        SpannableStringBuilder builder = new SpannableStringBuilder(getString(R.string.how_to_reach_1));
        SpannableString byAir = new SpannableString("\n\nBy Air ");
        byAir.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), 0, byAir.length(), 0);
        builder.append(byAir);
        builder.append(getString(R.string.how_to_reach_2));
        SpannableString byTrain = new SpannableString("\n\nBy Train ");
        byTrain.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), 0, byTrain.length(), 0);
        builder.append(byTrain);
        builder.append(getString(R.string.how_to_reach_3));
        SpannableString byBus = new SpannableString("\n\nBy Bus ");
        byBus.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), 0, byBus.length(), 0);
        builder.append(byBus);
        builder.append(getString(R.string.how_to_reach_4));
        SpannableString ps = new SpannableString("\n\nP.S. ");
        ps.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), 0, ps.length(), 0);
        builder.append(ps);
        builder.append(getString(R.string.how_to_reach_5));
        instructionsText.setText(builder, TextView.BufferType.SPANNABLE);
        return view;
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
