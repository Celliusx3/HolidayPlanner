package com.app.cellstudio.holidayplanner.presentation.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.cellstudio.holidayplanner.R;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.ViewModel;
import com.app.cellstudio.holidayplanner.presentation.BaseApplication;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;

public class SplashActivity extends BaseActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @BindView(R.id.rl_splash_main)
    RelativeLayout rlMain;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_splash;
    }

    @Override
    protected List<ViewModel> getViewModels() {
        return null;
    }

    @Override
    protected View getRootView() {
        return rlMain;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Observable.timer(0, TimeUnit.MILLISECONDS)
                .compose(bindToLifecycle())
                .observeOn(getUiScheduler())
                .subscribe(aLong -> goToMain(), throwable -> {});
    }

    @Override
    protected void onInject() {
        BaseApplication.getInstance()
                .getApplicationComponent()
                .inject(this);
    }

    @Override
    protected void startActivityTransitions() {
    }

    @Override
    protected void finishActivityTransitions() {
    }

    private void goToMain() {
        getNavigator().navigateToMain(this);
        getNavigator().showPendingTransitionFadeInFadeOut(this);
    }

}
