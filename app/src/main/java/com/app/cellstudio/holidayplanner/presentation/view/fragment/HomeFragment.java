package com.app.cellstudio.holidayplanner.presentation.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.cellstudio.holidayplanner.R;
import com.app.cellstudio.holidayplanner.databinding.ContentTodayHolidayBinding;
import com.app.cellstudio.holidayplanner.databinding.FragmentHomeBinding;
import com.app.cellstudio.holidayplanner.di.module.HomeModule;
import com.app.cellstudio.holidayplanner.interactor.model.HolidayModel;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.HomeViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.ViewModel;
import com.app.cellstudio.holidayplanner.presentation.BaseApplication;
import com.app.cellstudio.holidayplanner.presentation.util.AutomatedOnScrollListener;
import com.app.cellstudio.holidayplanner.presentation.view.adapter.UpcomingHolidayAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class HomeFragment extends BaseFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    @Inject
    BaseSchedulerProvider scheduler;

    @Inject
    HomeViewModel homeViewModel;

    @BindView(R.id.rl_cth_main)
    RelativeLayout rlCthMain;

    @BindView(R.id.rv_upcoming_holidays)
    RecyclerView rvUpcomingHolidays;

    @BindView(R.id.av_home_ads)
    AdView avHomeAds;

    private FragmentHomeBinding binding;
    private UpcomingHolidayAdapter upcomingHolidayAdapter;
    private Disposable disposable;
    private SnapHelper snapHelper;

    public static HomeFragment newInstance() {
        final Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected List<ViewModel> getViewModels() {
        return Collections.singletonList(homeViewModel);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
    }

    @Override
    protected void onInject() {
        BaseApplication.getInstance()
                .getApplicationComponent()
                .plus(new HomeModule(getContext()))
                .inject(this);
    }

    @Override
    protected void onBindData(View view) {

        binding = DataBindingUtil.bind(view);

        ContentTodayHolidayBinding thb = DataBindingUtil.bind(rlCthMain);

        homeViewModel.initHomeViewModel();
        thb.setModel(homeViewModel.getTodayHolidayModel());
        this.setupUpcomingHolidaysAdapter(homeViewModel.getUpcomingHolidayModels());

        initAdView();
    }

    @Override
    public void onResume() {
        super.onResume();
        createAutoScrollTimer(rvUpcomingHolidays, snapHelper);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    private void setupUpcomingHolidaysAdapter(@NonNull List<HolidayModel> holidayModels) {
        rvUpcomingHolidays.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvUpcomingHolidays);

        upcomingHolidayAdapter = new UpcomingHolidayAdapter(holidayModels);
        rvUpcomingHolidays.setAdapter(upcomingHolidayAdapter);
        rvUpcomingHolidays.setNestedScrollingEnabled(false);

        AutomatedOnScrollListener onScrollListener = new AutomatedOnScrollListener();
        AutomatedOnScrollListener.OnSnappedListener onSnappedListener = new AutomatedOnScrollListener.OnSnappedListener() {
            @Override
            public void onSnapped(int position) {
                createAutoScrollTimer(rvUpcomingHolidays, snapHelper);
            }

            @Override
            public void onDragging() {
                if (disposable != null && !disposable.isDisposed())
                    disposable.dispose();
            }
        };

        rvUpcomingHolidays.addOnScrollListener(onScrollListener);
        onScrollListener.setOnSnappedListener(onSnappedListener);

        rvUpcomingHolidays.scrollToPosition(upcomingHolidayAdapter.getItemCount() / 2 -
                upcomingHolidayAdapter.getItemCount() / 2 % upcomingHolidayAdapter.getRealCount());
        createAutoScrollTimer(rvUpcomingHolidays, snapHelper);
    }

    private void createAutoScrollTimer(RecyclerView rvBanners, SnapHelper snapHelper) {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();

       disposable = Observable.interval(5, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .observeOn(getUiScheduler())
                .subscribe(interval -> {
                    View snappedView = snapHelper.findSnapView(rvBanners.getLayoutManager());
                    int newPosition = rvBanners.getChildAdapterPosition(snappedView) + 1;
                    rvBanners.smoothScrollToPosition(newPosition);
                });
    }

    private void initAdView(){
        AdRequest adRequest = new AdRequest.Builder().build();
        avHomeAds.loadAd(adRequest);
    }
}
