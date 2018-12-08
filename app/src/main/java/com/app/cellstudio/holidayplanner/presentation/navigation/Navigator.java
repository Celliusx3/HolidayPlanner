package com.app.cellstudio.holidayplanner.presentation.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.app.cellstudio.holidayplanner.R;
import com.app.cellstudio.holidayplanner.presentation.view.activity.MainActivity;
import com.app.cellstudio.holidayplanner.presentation.view.activity.RegionSelectionActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by coyan on 12/09/2018.
 */

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

    private static final String TAG = Navigator.class.getSimpleName();

    @Inject
    public Navigator() {
    }

    public void navigateToMain(Context context) {
        if (context != null) {
            Intent intentToLaunch = MainActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToRegionSelection(Context context){
        if (context != null) {
            Intent intentToLaunch = RegionSelectionActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void showPendingTransitions(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    public void showPendingTransitionFadeInFadeOut(Activity activity) {
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void showPendingTransitionsSlideInRightFadeOut(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
    }

    public void showPendingTransitionModalIn(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_in_bottom, android.R.anim.fade_out);
    }

    public void showPendingTransitionModalOut(Activity activity) {
        activity.overridePendingTransition(android.R.anim.fade_in, R.anim.slide_out_bottom);
    }
}
