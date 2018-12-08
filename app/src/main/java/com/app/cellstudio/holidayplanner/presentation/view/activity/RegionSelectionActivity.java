package com.app.cellstudio.holidayplanner.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.cellstudio.holidayplanner.R;
import com.app.cellstudio.holidayplanner.databinding.ActivityRegionSelectionBinding;
import com.app.cellstudio.holidayplanner.di.module.RegionSelectionModule;
import com.app.cellstudio.holidayplanner.interactor.model.RegionModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.RegionSelectionViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.ViewModel;
import com.app.cellstudio.holidayplanner.presentation.BaseApplication;
import com.app.cellstudio.holidayplanner.presentation.view.adapter.RegionSelectionAdapter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class RegionSelectionActivity extends BaseActivity {

    private static final String TAG = RegionSelectionActivity.class.getSimpleName();

    @Inject
    RegionSelectionViewModel regionSelectionViewModel;

    @BindView(R.id.rl_region_selection_main)
    RelativeLayout rlRegion;

    @BindView(R.id.rv_region_selection_main)
    RecyclerView rvRegion;

    private RegionSelectionAdapter regionSelectionAdapter;
    private ActivityRegionSelectionBinding binding;

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, RegionSelectionActivity.class);
        return intent;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_region_selection;
    }

    @Override
    protected List<ViewModel> getViewModels() {
        return Collections.singletonList(regionSelectionViewModel);
    }

    @Override
    protected View getRootView() {
        return rlRegion;
    }

    @Override
    protected String getToolbarTitle() {
        return "Region";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected void onInject() {
        super.onInject();
        BaseApplication.getInstance()
                .getApplicationComponent()
                .plus(new RegionSelectionModule(this))
                .inject(this);
    }

    @Override
    protected void onBindData(@Nullable View view, Bundle savedInstanceState) {
        super.onBindData(view, savedInstanceState);
        binding = DataBindingUtil.bind(view);
        binding.setViewModel(regionSelectionViewModel);
        setToolbarTitle(getToolbarTitle());

        setupRegionAdapter(regionSelectionViewModel.getAllRegions(),
                regionSelectionViewModel.getCurrentRegionModel());
    }

    @Override
    protected void onBindView() {
        super.onBindView();
        toolbar.setNavigationIcon(R.drawable.ic_back_24dp);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void onResume() {
        super.onResume();
        subscribeSelectedRegisteredDevice();
    }

    private void setupRegionAdapter(List<RegionModel> allRegions,
                                      RegionModel currentRegion) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(RegionSelectionActivity.this,
                LinearLayoutManager.VERTICAL, false);
        rvRegion.setLayoutManager(layoutManager);
        regionSelectionAdapter = new RegionSelectionAdapter(allRegions, currentRegion);
        rvRegion.setAdapter(regionSelectionAdapter);
    }

    private void subscribeSelectedRegisteredDevice() {
        if (regionSelectionAdapter == null)
            return;

        // Switch Region
        regionSelectionAdapter.getSelectedSwitchRegion()
                .compose(bindToLifecycle())
                .observeOn(getUiScheduler())
                .subscribe(regionModel -> {
                    regionModel.first.setDisplaySelected(true);
                    regionModel.second.setDisplaySelected(false);
                    navigator.navigateToMain(this);
                    regionSelectionViewModel.setCurrentRegion(regionModel.first);
                },Throwable::printStackTrace);
    }
}
