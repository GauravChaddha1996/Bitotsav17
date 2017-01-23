package com.bitmesra.bitotsav.features.start;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.features.base.BaseAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends BaseAppCompatActivity implements StartViewInterface {

    @BindView(R.id.startLogo)
    ImageView startLogo;
    private StartPresenterImpl StartPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.
                FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        StartPresenter = new StartPresenterImpl(this);
        ObjectAnimator animator = ObjectAnimator.ofFloat(startLogo,"rotationY",0,360).setDuration(2500);
        animator.setInterpolator(new AnticipateOvershootInterpolator());
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }


}
