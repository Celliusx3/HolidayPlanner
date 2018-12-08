package com.app.cellstudio.holidayplanner.di.component;

import com.app.cellstudio.holidayplanner.di.module.RecommendedModule;
import com.app.cellstudio.holidayplanner.presentation.view.fragment.RecommendedFragment;

import dagger.Subcomponent;

/**
 * Created by coyan on 10/10/2018.
 */

@ActivityScope
@Subcomponent(modules = {RecommendedModule.class})
public interface RecommendedComponent {
    void inject(RecommendedFragment recommendedFragment);
}