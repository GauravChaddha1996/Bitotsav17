package com.bitmesra.bitotsav.features.home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bitmesra.bitotsav.R;
import com.bitmesra.bitotsav.base.BaseFragment;
import com.bitmesra.bitotsav.features.IdForFragment;
import com.bitmesra.bitotsav.features.events.EventsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private BaseFragment currentFragment;
    private BaseFragment newFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setUpNavigationDrawer();
        setFragment(IdForFragment.HOME);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(currentFragment.getBackToClazz()!=null) {
                setFragment(currentFragment.getBackToClazz());
            }else{
                super.onBackPressed();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            setFragment(IdForFragment.HOME);
        } else if (id == R.id.nav_events) {
            setFragment(IdForFragment.EVENTS);
        } else if (id == R.id.nav_maps) {

        } else if (id == R.id.nav_register) {

        } else if (id == R.id.nav_scoreboard) {

        } else if (id == R.id.nav_passes) {

        } else if (id == R.id.nav_livechat) {

        } else if (id == R.id.nav_contactaboutsponsor) {

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setUpNavigationDrawer() {
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);
    }

    private BaseFragment handleNavViewTransition(IdForFragment idForFragment) {
        BaseFragment toReturnFragment = new HomeFragment();
        switch (idForFragment){
            case HOME:
                navView.setCheckedItem(R.id.nav_home);
                toReturnFragment = new HomeFragment();
                break;
            case EVENTS:
                navView.setCheckedItem(R.id.nav_events);
                toReturnFragment = new EventsFragment();
                break;
            case MAPS:
                navView.setCheckedItem(R.id.nav_maps);
                break;
            case REGISTER:
                navView.setCheckedItem(R.id.nav_register);
                break;
            case SCOREBOARD:
                navView.setCheckedItem(R.id.nav_scoreboard);
                break;
            case PASSES:
                navView.setCheckedItem(R.id.nav_passes);
                break;
            case LIVECHAT:
                navView.setCheckedItem(R.id.nav_livechat);
                break;
            case CSA:
                navView.setCheckedItem(R.id.nav_contactaboutsponsor);
                break;
        }
        return toReturnFragment;
    }

    private void setFragment(IdForFragment idForFragment) {
        if(currentFragment!=null && idForFragment == currentFragment.getFragmentId()) {
            return;
        }
        newFragment = handleNavViewTransition(idForFragment);
        currentFragment = newFragment;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.homeFrameLayout, newFragment);
        transaction.commit();
    }
}
