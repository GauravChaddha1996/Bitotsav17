package com.bitmesra.bitotsav.features.start;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.features.MainActivity;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity implements StartViewInterface {

    @BindView(R.id.startLogo)
    ImageView startLogo;
    @BindView(R.id.startBitotsavTitle)
    TextView startBitotsavTitle;
    @BindView(R.id.startSponsor)
    TextView startSponsor;
    @BindView(R.id.startPPRText)
    TextView startPPRText;
    @BindView(R.id.startPPRImage)
    ImageView startPPRImage;

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
        YoYo.with(Techniques.FadeInDown)
                .duration(2000)
                .playOn(startLogo);
        YoYo.with(Techniques.SlideInRight)
                .duration(2000)
                .playOn(startBitotsavTitle);
        YoYo.with(Techniques.FlipInX)
                .duration(2000)
                .playOn(startSponsor);
        YoYo.with(Techniques.Landing)
                .duration(2000)
                .playOn(startSponsor);
        YoYo.with(Techniques.SlideInUp)
                .duration(2000)
                .playOn(startPPRText);
        AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) getBaseContext().
                getDrawable(R.drawable.play_pause_repeat_animated_vector);
        startPPRImage.setImageDrawable(animatedVectorDrawable);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                animatedVectorDrawable.start();
                sendEmptyMessageDelayed(0, 500);
            }
        };
        handler.sendEmptyMessage(0);
    }


}
