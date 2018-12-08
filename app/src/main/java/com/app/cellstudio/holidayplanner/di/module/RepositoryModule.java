package com.app.cellstudio.holidayplanner.di.module;

import android.content.Context;

import com.app.cellstudio.holidayplanner.data.pref.BasePref;
import com.app.cellstudio.holidayplanner.data.repository.CalendarRepository;
import com.app.cellstudio.holidayplanner.data.repository.ConfigRepository;
import com.app.cellstudio.holidayplanner.data.repository.impl.CalendarRepositoryImpl;
import com.app.cellstudio.holidayplanner.data.repository.impl.ConfigRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coyan on 13/09/2018.
 */

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    ConfigRepository provideConfigRepository(Context context, BasePref basePref) {
        return new ConfigRepositoryImpl(context, basePref);
    }

    @Singleton
    @Provides
    CalendarRepository provideCalendarRepository(Context context) {
        return new CalendarRepositoryImpl(context);
    }
}
