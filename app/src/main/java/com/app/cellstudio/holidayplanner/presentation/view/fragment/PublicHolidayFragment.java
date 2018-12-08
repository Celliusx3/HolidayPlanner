package com.app.cellstudio.holidayplanner.presentation.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.cellstudio.holidayplanner.R;
import com.app.cellstudio.holidayplanner.di.module.PublicHolidayModule;
import com.app.cellstudio.holidayplanner.interactor.model.YearHolidayModel;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.PublicHolidayViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.ViewModel;
import com.app.cellstudio.holidayplanner.presentation.BaseApplication;
import com.app.cellstudio.holidayplanner.presentation.view.adapter.YearHolidayAdapter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class PublicHolidayFragment extends BaseFragment {

    private static final String TAG = PublicHolidayFragment.class.getSimpleName();

    @Inject
    PublicHolidayViewModel publicHolidayViewModel;

    @Inject
    BaseSchedulerProvider scheduler;

    @BindView(R.id.rv_public_holiday_main)
    RecyclerView rvPublicHoliday;

    private YearHolidayAdapter publicHolidayAdapter;

    public static PublicHolidayFragment newInstance() {
        final Bundle args = new Bundle();
        PublicHolidayFragment fragment = new PublicHolidayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_public_holiday;
    }

    @Override
    protected List<ViewModel> getViewModels() {
        return Collections.singletonList(publicHolidayViewModel);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
    }

    @Override
    protected void onInject() {
        BaseApplication.getInstance()
                .getApplicationComponent()
                .plus(new PublicHolidayModule(getContext()))
                .inject(this);
    }

    @Override
    protected void onBindData(View view) {
        //FragmentSettingsBinding binding = DataBindingUtil.bind(view);

        setupPublicHoliday(publicHolidayViewModel.getHolidayInAYearModel());
    }

    private void setupPublicHoliday(List<YearHolidayModel> allHolidays) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        rvPublicHoliday.setLayoutManager(layoutManager);
        publicHolidayAdapter = new YearHolidayAdapter(allHolidays);
        rvPublicHoliday.setAdapter(publicHolidayAdapter);
        rvPublicHoliday.setNestedScrollingEnabled(false);
    }
}
