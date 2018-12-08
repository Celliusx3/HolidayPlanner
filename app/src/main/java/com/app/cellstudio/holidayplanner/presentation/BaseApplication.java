package com.app.cellstudio.holidayplanner.presentation;

import android.support.multidex.MultiDexApplication;

import com.app.cellstudio.holidayplanner.R;
import com.app.cellstudio.holidayplanner.di.component.ApplicationComponent;
import com.app.cellstudio.holidayplanner.di.component.DaggerApplicationComponent;
import com.app.cellstudio.holidayplanner.di.module.ApplicationModule;
import com.app.cellstudio.holidayplanner.presentation.image.BaseImageLoader;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.MobileAds;

import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

/**
 * Created by coyan on 10/09/2018.
 */

public class BaseApplication extends MultiDexApplication {

    private ApplicationComponent mApplicationComponent;
    private final Object mLock = new Object();

    // Singleton Instance
    private static BaseApplication singleton;

    public static BaseApplication getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        synchronized (mLock) {
            singleton = this;
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
            mApplicationComponent.inject(this);
        }
        initializeSDKs();
    }

    private void initializeSDKs() {
        ButterKnife.setDebug(true);
        BaseImageLoader.getInstance().init(this);
        initCrashlytics();
        initAdMob();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    private void initCrashlytics(){
        final Fabric fabric = new Fabric
                .Builder(this)
                .kits(new Crashlytics())
                .build();
        Fabric.with(fabric);
    }

    private void initAdMob(){
        MobileAds.initialize(this, getString(R.string.admob_id));
    }

}
