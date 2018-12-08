package com.app.cellstudio.holidayplanner.di.component;

import com.app.cellstudio.holidayplanner.di.module.RegionSelectionModule;
import com.app.cellstudio.holidayplanner.presentation.view.activity.RegionSelectionActivity;

import dagger.Subcomponent;

/**
 * Created by coyan on 16/09/2018.
 */

@ActivityScope
@Subcomponent(modules = {RegionSelectionModule.class})
public interface RegionSelectionComponent {
    void inject(RegionSelectionActivity regionSelectionActivity);
}