package com.app.cellstudio.holidayplanner.interactor.viewmodel;

import android.databinding.ObservableBoolean;

import com.app.cellstudio.holidayplanner.interactor.model.RegionModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by coyan on 16/09/2018.
 */

public interface RegionSelectionViewModel extends ViewModel {

    RegionModel getCurrentRegionModel();

    List<RegionModel> getAllRegions();

    void setCurrentRegion(RegionModel currentRegionModel);

    ObservableBoolean getLoading();

}
