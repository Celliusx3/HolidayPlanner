package com.app.cellstudio.holidayplanner.di.module;

import android.content.Context;

import com.app.cellstudio.holidayplanner.data.repository.CalendarRepository;
import com.app.cellstudio.holidayplanner.data.repository.ConfigRepository;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.RecommendedViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.impl.RecommendedViewModelImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coyan on 10/10/2018.
 */

@Module
public class RecommendedModule {

    private Context mContext;

    public RecommendedModule(Context context) {
        mContext = context;
    }

    @Provides
    RecommendedViewModel provideRecommendedViewModel(ConfigRepository configRepository,
                                                     CalendarRepository calendarRepository,
                                              BaseSchedulerProvider provider) {
        return new RecommendedViewModelImpl(configRepository, calendarRepository, provider);
    }
}
