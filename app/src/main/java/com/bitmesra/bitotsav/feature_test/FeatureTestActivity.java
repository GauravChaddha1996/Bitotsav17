package com.bitmesra.bitotsav.feature_test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.utils.Utils;

public class FeatureTestActivity extends AppCompatActivity {

    ImageView mario_image;
    TextView mario_text;
    boolean stopLoading;
    ViewPropertyAnimator mario_image_animator;
    ViewPropertyAnimator mario_text_animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_test);
        mario_image = (ImageView) findViewById(R.id.monster_image);
        mario_text = (TextView) findViewById(R.id.loading_text);
    }

    public void startLoading(View view) {
        stopLoading = false;
        mario_text.setText("Loading...");
        mario_text.setTranslationY(Utils.getScreenHeight(this));
        mario_image.setTranslationY(Utils.getScreenHeight(this));
        mario_text.animate().translationY(0).setDuration(500).start();
        mario_image_animator = mario_image.animate().translationY(0).setDuration(500).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mario_image_animator.setListener(null);
                startAnimating();
            }
        });
        mario_image_animator.start();
    }

    public void stopLoading(View view) {
        stopLoading = true;
    }

    public void startAnimating() {
        mario_image_animator = mario_image.animate().translationY(-200)
                .setInterpolator(new DecelerateInterpolator()).setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mario_image_animator.setListener(null);
                        mario_image_animator = mario_image.animate().translationY(0)
                                .setInterpolator(new AccelerateInterpolator()).setDuration(500)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        mario_image_animator.setListener(null);
                                        if (!stopLoading) startAnimating();
                                        else stopAnimating();
                                    }
                                });
                    }
                });
        mario_image_animator.start();
    }

    public void stopAnimating() {
        mario_text_animator = mario_text.animate().alpha(0.0f).setDuration(500).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mario_text_animator.setListener(null);
                mario_text.setText("Done.");
                mario_text.animate().alpha(1.0f).setDuration(500).start();
            }
        });
        mario_text_animator.start();
        mario_image_animator = mario_image.animate().rotationBy(360).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mario_image_animator.setListener(null);
                mario_text.animate().translationY(Utils.getScreenHeight(FeatureTestActivity.this)).start();
                mario_image.animate().translationY(Utils.getScreenHeight(FeatureTestActivity.this)).start();
            }
        });
        mario_image_animator.start();
    }
}
