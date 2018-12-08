package com.app.cellstudio.holidayplanner.presentation.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import com.app.cellstudio.holidayplanner.R;
import com.app.cellstudio.holidayplanner.databinding.FragmentSettingsBinding;
import com.app.cellstudio.holidayplanner.di.module.SettingsModule;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.SettingsViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.ViewModel;
import com.app.cellstudio.holidayplanner.presentation.BaseApplication;
import com.app.cellstudio.holidayplanner.presentation.view.dialog.NonWorkingDaysDialog;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class SettingsFragment extends BaseFragment {

    private static final String TAG = SettingsFragment.class.getSimpleName();

    @BindView(R.id.scrollView)
    ScrollView svSettings;

    @Inject
    SettingsViewModel settingsViewModel;

    @Inject
    BaseSchedulerProvider scheduler;

    public static SettingsFragment newInstance() {
        final Bundle args = new Bundle();
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_settings;
    }

    @Override
    protected List<ViewModel> getViewModels() {
        return Collections.singletonList(settingsViewModel);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
    }

    @Override
    protected void onInject() {
        BaseApplication.getInstance()
                .getApplicationComponent()
                .plus(new SettingsModule(getContext()))
                .inject(this);
    }

    @Override
    protected void onBindData(View view) {
        FragmentSettingsBinding binding = DataBindingUtil.bind(view);
        binding.setViewModel(settingsViewModel);

        settingsViewModel.getRegionSelection()
                .compose(bindToLifecycle())
                .observeOn(getUiScheduler())
                .subscribe(s -> getNavigator().navigateToRegionSelection(getContext()),
                Throwable::printStackTrace);

        settingsViewModel.getUpdateNonWorkingDays()
                .compose(bindToLifecycle())
                .observeOn(getUiScheduler())
                .subscribe(this::showNonWorkingDaysDialog,
                        Throwable::printStackTrace);
    }

    private void showNonWorkingDaysDialog(List<Integer> nonWorkingDays){
        NonWorkingDaysDialog nonWorkingDaysDialog = NonWorkingDaysDialog.newInstance(nonWorkingDays, true);
        nonWorkingDaysDialog.setListener(daysInWeek -> {
            settingsViewModel.onSelectedNonWorkingDays(daysInWeek);
            navigator.navigateToMain(getContext());
        });
        nonWorkingDaysDialog.show(getChildFragmentManager(), "non-working-days-dialog");
    }
}
