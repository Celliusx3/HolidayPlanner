package com.app.cellstudio.holidayplanner.interactor.viewmodel;

import android.view.View;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by coyan on 14/09/2018.
 */

public interface SettingsViewModel extends ViewModel {
    void onRegionSelectionClicked(View view);
    void onUpdateNonWorkingDays(View view);
    void onSelectedNonWorkingDays(List<Integer> nonWorkingDays);

    Observable<Boolean> getRegionSelection();
    Observable<List<Integer>> getUpdateNonWorkingDays();

    String getAppVersion();
    String getCurrentRegionInDisplay();

}
