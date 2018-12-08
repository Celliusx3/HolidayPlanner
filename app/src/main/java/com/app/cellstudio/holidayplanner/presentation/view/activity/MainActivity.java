package com.app.cellstudio.holidayplanner.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.app.cellstudio.holidayplanner.R;
import com.app.cellstudio.holidayplanner.databinding.ContentMainBinding;
import com.app.cellstudio.holidayplanner.databinding.NavMainBinding;
import com.app.cellstudio.holidayplanner.di.module.MainModule;
import com.app.cellstudio.holidayplanner.interactor.model.PageModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.MainViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.NavigationViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.ViewModel;
import com.app.cellstudio.holidayplanner.presentation.BaseApplication;
import com.app.cellstudio.holidayplanner.presentation.view.adapter.MainMenuAdapter;
import com.app.cellstudio.holidayplanner.presentation.view.adapter.MainPagerAdapter;
import com.app.cellstudio.holidayplanner.presentation.view.dialog.NonWorkingDaysDialog;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseNavigationActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    MainViewModel mainViewModel;

    @Inject
    NavigationViewModel navigationViewModel;

    @BindView(R.id.ll_im_main)
    LinearLayout llMain;

    @BindView(R.id.nav_view)
    NavigationView nv;

    @BindView(R.id.main_vp_content)
    ViewPager vpContent;

    private MainPagerAdapter mainPagerAdapter;
    private MainMenuAdapter mainMenuAdapter;

    private List<PageModel> pageModels;

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected List<ViewModel> getViewModels() {
        return Collections.singletonList(mainViewModel);
    }

    @Override
    protected View getRootView() {
        return llMain;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.app_name);
    }

    @Override
    protected void onGetInputData(Bundle savedInstanceState) {
        super.onGetInputData(savedInstanceState);
    }

    @Override
    protected void onInject() {
        BaseApplication.getInstance()
                .getApplicationComponent()
                .plus(new MainModule(this))
                .inject(this);
    }

    @Override
    protected void setViewModelViewEvent() {
        super.setViewModelViewEvent();
    }

    @Override
    protected void onBindView() {
        super.onBindView();
    }

    @Override
    protected void onBindData(View view, Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvNavDrawer.setLayoutManager(layoutManager);

        ContentMainBinding binding = DataBindingUtil.bind(view);
        binding.setViewModel(mainViewModel);

        NavMainBinding mb = DataBindingUtil.bind(nv);
        mb.setNavViewModel(navigationViewModel);

        // Show non working dialog
        if (mainViewModel.isShowNonWorkingDialog())
            showNonWorkingDaysDialog(null);

        mainViewModel.generateViewData();

        mainViewModel.getDrawerItems()
                .compose(bindToLifecycle())
                .observeOn(getUiScheduler())
                .subscribe(drawerModels -> {
                    this.pageModels = drawerModels;
                    if (mainMenuAdapter == null) {
                        mainMenuAdapter = new MainMenuAdapter(drawerModels);
                        rvNavDrawer.setAdapter(mainMenuAdapter);
                    } else {
                        mainMenuAdapter.setData(drawerModels);
                    }
                    subscribeSelectedPageModel();

                    //Setup View Pager
                    int size = pageModels.size();
                    vpContent.setOffscreenPageLimit(size);
                    mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), pageModels, isTablet());
                    vpContent.setAdapter(mainPagerAdapter);

                    // Set view pager to destination or homepage for default
                    int defaultIndex = 0;
                    if (!pageModels.isEmpty()) {
                        PageModel defaultPageModel = pageModels.get(defaultIndex);
                        setPage(defaultPageModel, defaultIndex);
                    }
                });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        subscribeSelectedPageModel();
    }

    @Override
    public boolean requireDoubleBackPressToFinishActivity() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void subscribeSelectedPageModel() {
        if (mainMenuAdapter == null)
            return;

        mainMenuAdapter.getSelectedPageModel()
                .compose(bindToLifecycle())
                .observeOn(getUiScheduler())
                .subscribe(pageModel -> {
                    int position = mainPagerAdapter.getPagePositionById(pageModel.getTitle());
                    setPage(pageModel, position);
                    drawer.closeDrawer(Gravity.START);
                });
    }

    private void setPage(PageModel pageModel, int index) {
        vpContent.setCurrentItem(index);
        String pageTitle = pageModel.getTitle();
        setToolbarTitle(pageTitle);

        mainViewModel.getSelectedPageModel().onNext(pageModel);
    }

    private void showNonWorkingDaysDialog(List<Integer> nonWorkingDays){
        NonWorkingDaysDialog nonWorkingDaysDialog = NonWorkingDaysDialog.newInstance(nonWorkingDays, false);
        nonWorkingDaysDialog.setListener(daysInWeek -> {
            mainViewModel.onSelectedNonWorkingDays(daysInWeek);
            navigator.navigateToMain(this);
        });
        nonWorkingDaysDialog.show(getSupportFragmentManager(), "TAG");
    }
}
