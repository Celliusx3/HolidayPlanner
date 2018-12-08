package com.app.cellstudio.holidayplanner.presentation.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.cellstudio.holidayplanner.R;
import com.app.cellstudio.holidayplanner.di.module.RecommendedModule;
import com.app.cellstudio.holidayplanner.interactor.model.BestHolidayWithAnnualLeaveModel;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.RecommendedViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.ViewModel;
import com.app.cellstudio.holidayplanner.presentation.BaseApplication;
import com.app.cellstudio.holidayplanner.presentation.view.adapter.RecommendedHolidayAdapter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class RecommendedFragment extends BaseFragment {

    private static final String TAG = RecommendedFragment.class.getSimpleName();

    @Inject
    RecommendedViewModel recommendedViewModel;

    @Inject
    BaseSchedulerProvider scheduler;

    @BindView(R.id.rv_recommended_main)
    RecyclerView rvRecommendedHoliday;

    private RecommendedHolidayAdapter recommendedHolidayAdapter;

    public static RecommendedFragment newInstance() {
        final Bundle args = new Bundle();
        RecommendedFragment fragment = new RecommendedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_recommended;
    }

    @Override
    protected List<ViewModel> getViewModels() {
        return Collections.singletonList(recommendedViewModel);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
    }

    @Override
    protected void onInject() {
        BaseApplication.getInstance()
                .getApplicationComponent()
                .plus(new RecommendedModule(getContext()))
                .inject(this);
    }

    @Override
    protected void onBindData(View view) {

        recommendedViewModel.initRecommendedViewModel();
        setupRecommendedHoliday(recommendedViewModel.getBestHolidayWithAnnualLeaveModels());
    }

    private void setupRecommendedHoliday(List<BestHolidayWithAnnualLeaveModel> bestHolidayWithAnnualLeaveModels) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        rvRecommendedHoliday.setLayoutManager(layoutManager);
        recommendedHolidayAdapter = new RecommendedHolidayAdapter(bestHolidayWithAnnualLeaveModels);
        rvRecommendedHoliday.setAdapter(recommendedHolidayAdapter);
        rvRecommendedHoliday.setNestedScrollingEnabled(false);
    }
}