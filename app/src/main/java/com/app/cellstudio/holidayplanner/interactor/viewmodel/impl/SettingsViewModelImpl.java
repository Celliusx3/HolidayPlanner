package com.app.cellstudio.holidayplanner.interactor.viewmodel.impl;

import android.view.View;

import com.app.cellstudio.holidayplanner.BuildConfig;
import com.app.cellstudio.holidayplanner.data.repository.ConfigRepository;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.BaseViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.SettingsViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by coyan on 14/09/2018.
 */

public class SettingsViewModelImpl extends BaseViewModel implements SettingsViewModel {

    private PublishSubject<Boolean> regionSelection = PublishSubject.create();
    private PublishSubject<List<Integer>> updateNonWorkingDays = PublishSubject.create();

    public SettingsViewModelImpl(ConfigRepository configRepository,
                                 BaseSchedulerProvider schedulerProvider) {
        super(configRepository, schedulerProvider);
    }

    @Override
    public void onRegionSelectionClicked(View view) {
        regionSelection.onNext(true);
    }

    @Override
    public void onUpdateNonWorkingDays(View view) {
        updateNonWorkingDays.onNext(getNonWorkingDays());
    }

    @Override
    public void onSelectedNonWorkingDays(List<Integer> nonWorkingDays) {
        configRepository.setNonWorkingDayInAWeek(nonWorkingDays);
    }

    @Override
    public Observable<Boolean> getRegionSelection() {
        return regionSelection;
    }

    @Override
    public Observable<List<Integer>> getUpdateNonWorkingDays() {
        return updateNonWorkingDays;
    }

    @Override
    public String getAppVersion() {
        return String.format("%1$s %2$s-%3$s", "Test", BuildConfig.VERSION_NAME,
                BuildConfig.VERSION_CODE);
    }

    @Override
    public String getCurrentRegionInDisplay() {
        return configRepository.getRegion().getRegion();
    }

    private List<Integer> getNonWorkingDays(){
        List<Integer> nonWorkingDays = configRepository.getNonWorkingDayInAWeek();
        if (nonWorkingDays != null && !nonWorkingDays.isEmpty())
            return configRepository.getNonWorkingDayInAWeek();
        return new ArrayList<>();
    }
}
