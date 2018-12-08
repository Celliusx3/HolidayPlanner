package com.app.cellstudio.holidayplanner.di.component;

import android.content.Context;

import com.app.cellstudio.holidayplanner.di.module.ApplicationModule;
import com.app.cellstudio.holidayplanner.di.module.MainModule;
import com.app.cellstudio.holidayplanner.di.module.PublicHolidayModule;
import com.app.cellstudio.holidayplanner.di.module.RecommendedModule;
import com.app.cellstudio.holidayplanner.di.module.RegionSelectionModule;
import com.app.cellstudio.holidayplanner.di.module.RepositoryModule;
import com.app.cellstudio.holidayplanner.di.module.SettingsModule;
import com.app.cellstudio.holidayplanner.di.module.HomeModule;
import com.app.cellstudio.holidayplanner.presentation.BaseApplication;
import com.app.cellstudio.holidayplanner.presentation.view.activity.SplashActivity;
import com.app.cellstudio.holidayplanner.presentation.view.dialog.NonWorkingDaysDialog;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by coyan on 10/09/2018.
 */

@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class})
public interface ApplicationComponent {
    BaseApplication getApplication();
    Context getApplicationContext();
    void inject(BaseApplication baseApplication);
    void inject(SplashActivity splashActivity);
    void inject(NonWorkingDaysDialog nonWorkingDaysDialog);
    MainComponent plus(MainModule mainModule);
    SettingsComponent plus (SettingsModule settingsModule);
    HomeComponent plus(HomeModule homeModule);
    RegionSelectionComponent plus (RegionSelectionModule regionModule);
    PublicHolidayComponent plus (PublicHolidayModule publicHolidayModule);
    RecommendedComponent plus (RecommendedModule recommendedModule);
}
