package com.app.cellstudio.holidayplanner.di.component;

import com.app.cellstudio.holidayplanner.di.module.PublicHolidayModule;
import com.app.cellstudio.holidayplanner.presentation.view.fragment.PublicHolidayFragment;

import dagger.Subcomponent;

/**
 * Created by coyan on 16/09/2018.
 */

@ActivityScope
@Subcomponent(modules = {PublicHolidayModule.class})
public interface PublicHolidayComponent {
    void inject(PublicHolidayFragment publicHolidayFragment);
}