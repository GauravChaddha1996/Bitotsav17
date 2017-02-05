package com.bitmesra.bitotsav.features;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
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
import com.bitmesra.bitotsav.features.csa.CSAFragment;
import com.bitmesra.bitotsav.features.events.EventsFragment;
import com.bitmesra.bitotsav.features.events.informal.InformalFragment;
import com.bitmesra.bitotsav.features.flagships.FlagshipFragment;
import com.bitmesra.bitotsav.features.home.HomeFragment;
import com.bitmesra.bitotsav.features.register.RegisterFragment;
import com.bitmesra.bitotsav.features.tshirt.TShirtFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    private BaseFragment currentFragment;

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
            if (currentFragment.getBackToFragmentId() != null) {
                setFragment(currentFragment.getBackToFragmentId());
            } else {
                super.onBackPressed();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            setFragment(IdForFragment.HOME);
        } else if (id == R.id.nav_flagship) {
            setFragment(IdForFragment.FLAGSHIP);
        } else if (id == R.id.nav_events) {
            setFragment(IdForFragment.EVENTS);
        } else if (id == R.id.nav_register) {
            setFragment(IdForFragment.REGISTER);
        } else if (id == R.id.nav_tshirt) {
            setFragment(IdForFragment.TSHIRT);
        } else if (id == R.id.nav_contact_about_sponsor) {
            setFragment(IdForFragment.CSA);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setUpNavigationDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);
    }

    private BaseFragment handleNavViewTransition(IdForFragment idForFragment) {
        BaseFragment toReturnFragment = new HomeFragment();
        switch (idForFragment) {
            case HOME:
                navView.setCheckedItem(R.id.nav_home);
                getSupportActionBar().setTitle("Home");
                toReturnFragment = new HomeFragment();
                break;
            case FLAGSHIP:
                navView.setCheckedItem(R.id.nav_events);
                getSupportActionBar().setTitle("Flagships");
                toReturnFragment = new FlagshipFragment();
                break;
            case EVENTS:
                navView.setCheckedItem(R.id.nav_events);
                getSupportActionBar().setTitle("Events");
                toReturnFragment = new EventsFragment();
                break;
            case INFORMAL:
                navView.setCheckedItem(R.id.nav_events);
                getSupportActionBar().setTitle("Informals");
                toReturnFragment = new InformalFragment();
                break;
            case REGISTER:
                navView.setCheckedItem(R.id.nav_register);
                getSupportActionBar().setTitle("Register");
                toReturnFragment = new RegisterFragment();
                break;
            case TSHIRT:
                navView.setCheckedItem(R.id.nav_tshirt);
                getSupportActionBar().setTitle("T-Shirt Registration");
                toReturnFragment = new TShirtFragment();
                break;
            case CSA:
                navView.setCheckedItem(R.id.nav_contact_about_sponsor);
                getSupportActionBar().setTitle("Contact|About|Sponsor");
                toReturnFragment = new CSAFragment();
                break;
        }
        return toReturnFragment;
    }

    public void setFragment(IdForFragment idForFragment) {
        if (currentFragment != null && idForFragment == currentFragment.getFragmentId()) {
            return;
        }
        BaseFragment newFragment = handleNavViewTransition(idForFragment);
        currentFragment = newFragment;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.homeFrameLayout, newFragment);
        transaction.commit();
        appBarLayout.setExpanded(true, true);
    }

}
