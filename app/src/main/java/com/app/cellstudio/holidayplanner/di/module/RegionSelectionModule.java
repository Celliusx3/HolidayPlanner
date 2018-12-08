package com.app.cellstudio.holidayplanner.di.module;

import android.content.Context;

import com.app.cellstudio.holidayplanner.data.repository.ConfigRepository;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.MainViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.NavigationViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.RegionSelectionViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.impl.MainViewModelImpl;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.impl.NavigationViewModelImpl;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.impl.RegionSelectionViewModelImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coyan on 16/09/2018.
 */

@Module
public class RegionSelectionModule {

    private Context mContext;

    public RegionSelectionModule(Context context) {
        mContext = context;
    }

    @Provides
    RegionSelectionViewModel provideRegionSelectionViewModel(ConfigRepository configRepository, BaseSchedulerProvider provider) {
        return new RegionSelectionViewModelImpl(configRepository, provider);
    }
}
