package com.bitmesra.bitotsav.features.events.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bitmesra.bitotsav.R;

import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
    }
}
