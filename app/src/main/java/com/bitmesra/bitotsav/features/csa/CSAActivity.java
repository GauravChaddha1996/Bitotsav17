package com.bitmesra.bitotsav.features.csa;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.ui.ElasticDragDismissFrameLayout;
import com.bitmesra.bitotsav.ui.InkPageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CSAActivity extends Activity {

    @BindView(R.id.draggable_frame)
    ElasticDragDismissFrameLayout draggableFrame;
    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.indicator)
    InkPageIndicator pageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csa);
        ButterKnife.bind(this);

        pager.setAdapter(new AboutPagerAdapter(this));
        pager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.spacing_normal));
        pageIndicator.setViewPager(pager);
        draggableFrame.addListener(
                new ElasticDragDismissFrameLayout.SystemChromeFader(this) {
                    @Override
                    public void onDragDismissed() {
                        // if we drag dismiss downward then the default reversal of the enter
                        // transition would slide content upward which looks weird. So reverse it.
                        if (draggableFrame.getTranslationY() > 0) {
                            getWindow().setReturnTransition(
                                    TransitionInflater.from(CSAActivity.this)
                                            .inflateTransition(R.transition.about_return_downward));
                        }
                        finishAfterTransition();
                    }
                });

    }

    static class AboutPagerAdapter extends PagerAdapter {
        Context context;
        LayoutInflater inflater;

        public AboutPagerAdapter(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view;
            switch (position) {
                case 0:
                    view = inflater.inflate(R.layout.csa_contact, container, false);
                    container.addView(view);
                    return view;
                case 1:
                    view = inflater.inflate(R.layout.csa_sponsors, container, false);
                    container.addView(view);
                    return view;
                case 2:
                    view = inflater.inflate(R.layout.csa_about, container, false);
                    container.addView(view);
                    return view;
            }
            return null;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}

