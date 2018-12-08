package com.app.cellstudio.holidayplanner.presentation.view.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.cellstudio.holidayplanner.R;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.ViewModel;
import com.app.cellstudio.holidayplanner.presentation.navigation.Navigator;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by coyan on 14/09/2018.
 */

public abstract class BaseFragment extends RxFragment {

    @Inject
    Navigator navigator;

    @Inject
    BaseSchedulerProvider scheduler;

    private Unbinder unbinder;

    private BehaviorSubject<Boolean> currentUserVisibleHint = BehaviorSubject.createDefault(this.getUserVisibleHint());
    private ProgressDialog logoutProgress;
    private Dialog alertDialog;

    protected abstract @LayoutRes
    int getLayoutResource();

    protected abstract List<ViewModel> getViewModels();

    public Observable<Boolean> getCurrentUserVisibleHint() {
        return currentUserVisibleHint;
    }

    protected Scheduler getUiScheduler() {
        return scheduler.ui();
    }

    protected Scheduler getIoScheduler() {
        return scheduler.io();
    }

    protected Navigator getNavigator() {
        return navigator;
    }

    public String getTitle() {
        return "";
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        View inflate = inflater.inflate(getLayoutResource(), container, false);
        return inflate;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onGetInputData();
        onInject();
        onBindView(view);

        if (getViewModels() != null) {
            for (ViewModel viewModel : getViewModels()) {
                viewModel.getInput().onCreateView();

                ViewModel.Output output = viewModel.getOutput();

                output.getShowToastMessage()
                        .compose(bindToLifecycle())
                        .observeOn(getUiScheduler())
                        .subscribe(this::showToastMessage);
            }
        }

        onBindData(view);
    }

    protected void onBindView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    protected void onInject() {
    }

    protected void onBindData(View view) {
    }

    protected void onGetInputData() {
    }

    @Override
    public void setUserVisibleHint(final boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        currentUserVisibleHint.onNext(isVisibleToUser);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getViewModels() != null) {
            for (ViewModel viewModel : getViewModels()) {
                viewModel.getInput().onAttachView();
            }
        }
        setViewModelViewEvent();
    }

    protected void setViewModelViewEvent() {

    }

    @Override
    public void onPause() {
        super.onPause();
        if (getViewModels() != null) {
            for (ViewModel viewModel : getViewModels()) {
                viewModel.getInput().onDetachView();
            }
        }
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    protected String getDeviceId() {
        return Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    protected String getDeviceName() {
        return android.os.Build.MODEL;
    }

    protected boolean isTablet() {
        return getResources().getBoolean(R.bool.is_tablet);
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        if (isTablet()) {
            return new GridLayoutManager(getActivity(), 3);
        } else {
            return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        }
    }

    /**
     * Navigate to the top of scrollable page
     */
    public void navigateToTop() {

    }
}
