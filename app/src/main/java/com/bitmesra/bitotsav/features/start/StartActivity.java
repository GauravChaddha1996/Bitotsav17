package com.bitmesra.bitotsav.features.start;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.features.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity implements StartViewInterface {

    @BindView(R.id.startLogo)
    ImageView startLogo;
    @BindView(R.id.startBlurLogo)
    ImageView startBlurLogo;
    @BindView(R.id.startPPRText)
    TextView startPPRText;

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
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    //Handles all animation on this start activity
    private void startAnimation() {
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(startLogo, "scaleX", 0.8f, 1f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(startLogo, "scaleY", 0.8f, 1f);
        scaleDownX.setDuration(2000);
        scaleDownY.setDuration(2000);
        scaleDownY.start();
        scaleDownX.start();
        ObjectAnimator alpha = ObjectAnimator.ofFloat(startLogo, "alpha", 0f, 1f);
        alpha.setDuration(1000);
        alpha.start();

        ObjectAnimator translateY = ObjectAnimator.ofFloat(startBlurLogo, "translationY", 0f, 300f);
        translateY.setDuration(2000);
        translateY.start();
        ObjectAnimator alphaBlur = ObjectAnimator.ofFloat(startBlurLogo, "alpha", 0f, 1f);
        alphaBlur.setDuration(1000);
        alphaBlur.start();
        ObjectAnimator alphaBlur2 = ObjectAnimator.ofFloat(startBlurLogo, "alpha", 1f, 0.2f);
        alphaBlur2.setDuration(1000).setStartDelay(1000);
        alphaBlur2.start();

        ObjectAnimator finalscaleDownX = ObjectAnimator.ofFloat(startLogo, "scaleX", 1f, 25f);
        ObjectAnimator finalscaleDownY = ObjectAnimator.ofFloat(startLogo, "scaleY", 1f, 25f);
        finalscaleDownX.setStartDelay(2500);
        finalscaleDownY.setStartDelay(2500);
        finalscaleDownY.setDuration(500);
        finalscaleDownX.setDuration(500);
        finalscaleDownY.start();
        finalscaleDownX.start();
        ObjectAnimator alphaBlurOut = ObjectAnimator.ofFloat(startBlurLogo, "alpha", 0.2f, 0f);
        alphaBlurOut.setStartDelay(2500);
        alphaBlurOut.setDuration(500);
        alphaBlurOut.start();
    }


}
