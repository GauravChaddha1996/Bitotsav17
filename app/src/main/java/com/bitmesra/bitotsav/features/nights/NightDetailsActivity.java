package com.bitmesra.bitotsav.features.nights;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.database.DataManager;
import com.bitmesra.bitotsav.database.models.nights.NightsModel;
import com.bitmesra.bitotsav.ui.CustomTextView;
import com.bitmesra.bitotsav.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NightDetailsActivity extends AppCompatActivity {


    @BindView(R.id.background_image)
    ImageView backgroundImage;
    @BindView(R.id.frame_image)
    ImageView frameImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    CustomTextView toolbarTitle;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.detail_desc)
    CustomTextView detailDesc;
    @BindView(R.id.detail_links)
    CustomTextView detailLinks;
    int nightId;
    NightsModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_detail);
        ButterKnife.bind(this);
        nightId = getIntent().getIntExtra("nightId", 0);
        model = DataManager.getDataManager().getRealmManager().getNightEvent(nightId);
        setData(model);
        model.addChangeListener(element -> setData((NightsModel) element));
        animate();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setData(NightsModel model) {
        if (!model.getImage1().trim().isEmpty()) {
            Picasso.with(this).load(model.getImage1()).into(backgroundImage);
        } else {
            backgroundImage.setImageDrawable(this.getDrawable(R.drawable.ic_logo));
        }
        toolbarTitle.setText(model.getName());
        detailDesc.setText(model.getDesc());
        String links = model.getLinks();
        String linksArray[] = links.split("\n");
        StringBuilder builder = new StringBuilder("Links:\n");
        for (int i = 0; i < linksArray.length; i++) {
            builder.append("\n").append(i+1).append(". ").append(linksArray[i]);
        }
        detailLinks.setText(builder.toString());
        for (int i = 0; i < linksArray.length; i++) {
            Linkify.addLinks(detailLinks, Pattern.compile(linksArray[i]), linksArray[i]);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        model.removeChangeListeners();
    }

    private void animate() {
        toolbarTitle.setAlpha(0f);
        toolbarTitle.animate().alpha(1f).setDuration(1000).start();
        frameImage.setAlpha(0f);
        frameImage.animate().alpha(1f).setDuration(1000).start();
        detailDesc.setTranslationY(Utils.getScreenHeight(this));
        detailDesc.animate().translationY(0).setDuration(1100).start();
        detailLinks.setTranslationY(Utils.getScreenHeight(this));
        detailLinks.animate().translationY(0).setDuration(1200).start();
    }
}
