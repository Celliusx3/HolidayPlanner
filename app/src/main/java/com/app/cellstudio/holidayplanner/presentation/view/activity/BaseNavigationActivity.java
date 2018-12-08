package com.app.cellstudio.holidayplanner.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.cellstudio.holidayplanner.R;

import butterknife.BindView;

/**
 * Created by coyan on 13/09/2018.
 */

public abstract class BaseNavigationActivity extends BaseActivity {

    @BindView(R.id.nav_view)
    protected NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    protected DrawerLayout drawer;

    @BindView(R.id.nav_drawer_recycler_view)
    protected RecyclerView rvNavDrawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        View headerView = navigationView.getHeaderView(0);
    }
}