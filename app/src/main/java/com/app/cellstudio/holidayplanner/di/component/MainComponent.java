package com.app.cellstudio.holidayplanner.di.component;

import com.app.cellstudio.holidayplanner.di.module.MainModule;
import com.app.cellstudio.holidayplanner.presentation.view.activity.MainActivity;

import dagger.Subcomponent;

/**
 * Created by coyan on 13/09/2018.
 */

@ActivityScope
@Subcomponent(modules = {MainModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
