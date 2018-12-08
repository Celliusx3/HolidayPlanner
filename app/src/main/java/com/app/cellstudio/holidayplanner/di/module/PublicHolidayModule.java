package com.app.cellstudio.holidayplanner.di.module;

import android.content.Context;

import com.app.cellstudio.holidayplanner.data.repository.CalendarRepository;
import com.app.cellstudio.holidayplanner.data.repository.ConfigRepository;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.PublicHolidayViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.RegionSelectionViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.impl.PublicHolidayViewModelImpl;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.impl.RegionSelectionViewModelImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coyan on 16/09/2018.
 */

@Module
public class PublicHolidayModule {

    private Context mContext;

    public PublicHolidayModule(Context context) {
        mContext = context;
    }

    @Provides
    PublicHolidayViewModel providePublicHolidayViewModel(ConfigRepository configRepository, CalendarRepository calendarRepository, BaseSchedulerProvider provider) {
        return new PublicHolidayViewModelImpl(configRepository, calendarRepository, provider);
    }
}
