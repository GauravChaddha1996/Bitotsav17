package com.bitmesra.bitotsav.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.utils.Utils;

/**
 * Created by Batdroid on 14/2/17 for Bitotsav.
 */

public class AchievementHelper {
    Context context;
    ImageView mario_image;
    TextView mario_text;
    boolean stopLoading;
    boolean isSuccess;
    ViewPropertyAnimator mario_image_animator;
    ViewPropertyAnimator mario_text_animator;

    public AchievementHelper(Context context, ImageView mario_image, TextView mario_text) {
        this.context = context;
        this.mario_image = mario_image;
        this.mario_text = mario_text;
    }

    public void startLoading() {
        stopLoading = false;
        mario_text.setText("Loading...");
        mario_text.setTranslationY(Utils.getScreenHeight(context));
        mario_image.setTranslationY(Utils.getScreenHeight(context));
        mario_image.setVisibility(View.VISIBLE);
        mario_text.setVisibility(View.VISIBLE);
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

    public void stopLoading() {
        isSuccess = true;
        stopLoading = true;
    }

    public void errorLoading() {
        isSuccess = false;
        stopLoading = true;
    }

    private void startAnimating() {
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
                                        else {
                                            mario_image.setImageDrawable(context.getDrawable(
                                                    isSuccess ? R.drawable.mario_success : R.drawable.error_mario
                                            ));
                                            if (isSuccess)
                                                mario_image.setScaleType(ImageView.ScaleType.CENTER);
                                            stopAnimating();
                                        }
                                    }
                                });
                    }
                });
        mario_image_animator.start();
    }

    private void stopAnimating() {
        mario_text_animator = mario_text.animate().alpha(0.0f).setDuration(500).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mario_text_animator.setListener(null);
                mario_text.setText(isSuccess ? "Done." : "Error");
                mario_text.animate().alpha(1.0f).setDuration(500).start();
            }
        });
        mario_text_animator.start();
        mario_image_animator = mario_image.animate().rotationBy(360).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mario_image_animator.setListener(null);
                mario_text.animate().translationY(Utils.getScreenHeight(context)).start();
                mario_image_animator = mario_image.animate().translationY(Utils.getScreenHeight(context)).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mario_text_animator.setListener(null);
                    }
                });
                mario_image_animator.start();
            }
        });
        mario_image_animator.start();
    }
}
