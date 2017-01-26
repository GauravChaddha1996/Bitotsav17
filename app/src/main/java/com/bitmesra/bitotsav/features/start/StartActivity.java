package com.bitmesra.bitotsav.features.start;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.features.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity implements StartViewInterface {

    @BindView(R.id.startLogo)
    ImageView startLogo;
    private StartPresenterImpl startPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*todo press home button when this activity is running, it will start home activity
        * after 5 seconds. Ask for intended behaviour and then implement it */

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.
                FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        startAnimation();
        startPresenter = new StartPresenterImpl(this);
    }

    @Override
    public void goToHomeActivity() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    //Handles all animation on this start activity
    private void startAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(startLogo, "rotationY", 0, -360 * 120).setDuration(5 * 60 * 1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }


}
