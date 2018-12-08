package com.app.cellstudio.holidayplanner.di.component;

import com.app.cellstudio.holidayplanner.di.module.HomeModule;
import com.app.cellstudio.holidayplanner.presentation.view.fragment.HomeFragment;

import dagger.Subcomponent;

/**
 * Created by coyan on 14/09/2018.
 */

@ActivityScope
@Subcomponent(modules = {HomeModule.class})
public interface HomeComponent {
    void inject(HomeFragment homeFragment);
}
