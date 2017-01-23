package com.bitmesra.bitotsav.features.start;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.features.base.BaseAppCompatActivity;

import butterknife.ButterKnife;

public class StartActivity extends BaseAppCompatActivity implements StartViewInterface {

    StartPresenterImpl StartPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.
                FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        StartPresenter = new StartPresenterImpl(this);
    }


}
