package com.app.cellstudio.holidayplanner.di.module;

import android.content.Context;

import com.app.cellstudio.holidayplanner.data.repository.CalendarRepository;
import com.app.cellstudio.holidayplanner.data.repository.ConfigRepository;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.HomeViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.SettingsViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.impl.HomeViewModelImpl;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.impl.SettingsViewModelImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coyan on 14/09/2018.
 */

@Module
public class HomeModule {

    private Context mContext;

    public HomeModule(Context context) {
        mContext = context;
    }

    @Provides
    HomeViewModel provideHomeViewModel(ConfigRepository configRepository, CalendarRepository calendarRepository,
                                       BaseSchedulerProvider provider) {
        return new HomeViewModelImpl(configRepository, calendarRepository, provider);
    }
}