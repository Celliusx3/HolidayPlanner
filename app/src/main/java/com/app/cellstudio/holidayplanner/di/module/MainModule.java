package com.app.cellstudio.holidayplanner.di.module;

import android.content.Context;

import com.app.cellstudio.holidayplanner.data.repository.ConfigRepository;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.MainViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.NavigationViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.impl.MainViewModelImpl;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.impl.NavigationViewModelImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coyan on 13/09/2018.
 */

@Module
public class MainModule {

    private Context mContext;

    public MainModule(Context context) {
        mContext = context;
    }

    @Provides
    MainViewModel provideMainViewModel(ConfigRepository configRepository, BaseSchedulerProvider provider) {
        return new MainViewModelImpl(configRepository, provider);
    }

    @Provides
    NavigationViewModel provideNavigationViewModel(ConfigRepository configRepository, BaseSchedulerProvider provider) {
        return new NavigationViewModelImpl(configRepository, provider);
    }
}