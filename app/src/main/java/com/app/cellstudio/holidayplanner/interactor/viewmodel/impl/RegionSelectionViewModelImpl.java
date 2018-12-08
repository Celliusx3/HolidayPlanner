package com.app.cellstudio.holidayplanner.interactor.viewmodel.impl;

import android.databinding.ObservableBoolean;

import com.app.cellstudio.holidayplanner.data.entity.Region;
import com.app.cellstudio.holidayplanner.data.repository.ConfigRepository;
import com.app.cellstudio.holidayplanner.interactor.model.RegionModel;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.BaseViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.RegionSelectionViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coyan on 16/09/2018.
 */

public class RegionSelectionViewModelImpl extends BaseViewModel implements RegionSelectionViewModel {
    private List<RegionModel> allRegions = new ArrayList<>();
    private RegionModel currentRegionModel;
    private ObservableBoolean loading = new ObservableBoolean(false);

    public RegionSelectionViewModelImpl(ConfigRepository configRepository, BaseSchedulerProvider scheduler) {
        super(configRepository, scheduler);
        initRegionModels();
    }

    @Override
    public RegionModel getCurrentRegionModel() {
        return currentRegionModel;
    }

    @Override
    public List<RegionModel> getAllRegions() {
        return allRegions;
    }

    @Override
    public void setCurrentRegion(RegionModel currentRegionModel) {
        configRepository.setCurrentRegion(currentRegionModel.getRegion());
    }

    @Override
    public ObservableBoolean getLoading() {
        return loading;
    }

    private void initRegionModels() {
        for (Region region : Region.values()) {
            RegionModel modelName = initRegionModel(region);
            allRegions.add(modelName);
        }
    }

    private RegionModel initRegionModel(Region region) {
        Region currentRegion = getCurrentRegion();
        boolean displaySelected = region.equals(currentRegion);
        RegionModel regionModel = new RegionModel(region, region.getRegion(), displaySelected);
        if (displaySelected) {
            currentRegionModel = regionModel;
        }
        return regionModel;
    }

    private Region getCurrentRegion() {
        return configRepository.getRegion();
    }
}
