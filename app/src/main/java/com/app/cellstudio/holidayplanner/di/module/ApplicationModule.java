package com.app.cellstudio.holidayplanner.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.cellstudio.holidayplanner.data.pref.BasePref;
import com.app.cellstudio.holidayplanner.data.pref.impls.BaseSharedPref;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.scheduler.SchedulerProvider;
import com.app.cellstudio.holidayplanner.presentation.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coyan on 10/09/2018.
 */

@Module
public class ApplicationModule {
    private final BaseApplication mApplication;
    private SharedPreferences mPrefs;

    public ApplicationModule(final BaseApplication baseApplication) {
        mApplication = baseApplication;
    }

    @Provides
    @Singleton
    BaseApplication provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext(BaseApplication baseApplication) {
        return baseApplication;
    }

    @Provides
    @Singleton
    BaseSchedulerProvider provideScheduler() {
        return SchedulerProvider.getInstance();
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        if (mPrefs == null) {
            String key = context.getPackageName();
            if (key == null) {
                throw new NullPointerException("Prefs key may not be null");
            }
            mPrefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        }
        return mPrefs;
    }

    @Provides
    @Singleton
    BasePref provideBasePref(Context context, SharedPreferences sharedPreferences) {
        return new BaseSharedPref(context, sharedPreferences);
    }
}
