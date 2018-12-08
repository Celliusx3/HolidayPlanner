package com.app.cellstudio.holidayplanner.di.module;

import android.content.Context;

import com.app.cellstudio.holidayplanner.data.repository.ConfigRepository;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.SettingsViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.impl.SettingsViewModelImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coyan on 14/09/2018.
 */

@Module
public class SettingsModule {

    private Context mContext;

    public SettingsModule(Context context) {
        mContext = context;
    }

    @Provides
    SettingsViewModel provideSettingsViewModel(ConfigRepository configRepository, BaseSchedulerProvider provider) {
        return new SettingsViewModelImpl(configRepository, provider);
    }
}

