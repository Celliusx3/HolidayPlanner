package com.app.cellstudio.holidayplanner.di.component;

import com.app.cellstudio.holidayplanner.di.module.SettingsModule;
import com.app.cellstudio.holidayplanner.presentation.view.fragment.SettingsFragment;

import dagger.Subcomponent;

/**
 * Created by coyan on 14/09/2018.
 */

@ActivityScope
@Subcomponent(modules = {SettingsModule.class})
public interface SettingsComponent {
    void inject(SettingsFragment settingsFragment);
}
