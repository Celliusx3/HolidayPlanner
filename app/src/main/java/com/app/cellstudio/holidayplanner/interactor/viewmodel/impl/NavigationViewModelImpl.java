package com.app.cellstudio.holidayplanner.interactor.viewmodel.impl;

import com.app.cellstudio.holidayplanner.data.repository.ConfigRepository;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.BaseViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.NavigationViewModel;

/**
 * Created by coyan on 13/09/2018.
 */

public class NavigationViewModelImpl extends BaseViewModel implements NavigationViewModel {

    public NavigationViewModelImpl(ConfigRepository configRepository, BaseSchedulerProvider scheduler) {
        super(configRepository, scheduler);
    }
}

