package com.app.cellstudio.holidayplanner.presentation.view.dialog;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.app.cellstudio.holidayplanner.interactor.viewmodel.ViewModel;
import com.trello.rxlifecycle2.components.support.RxDialogFragment;

import butterknife.ButterKnife;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by coyan on 16/10/2018.
 */

public abstract class BaseDialog extends RxDialogFragment {

    protected abstract ViewModel getViewModel();

    protected abstract @LayoutRes
    int getLayoutResource();

    public Scheduler getUiScheduler() {
        return AndroidSchedulers.mainThread();
    }

    protected Scheduler getIoScheduler() {
        return Schedulers.io();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.bind(this, view);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onInject();

        if (getViewModel() != null) {
            getViewModel().getInput().onCreateView();

            ViewModel.Output output = getViewModel().getOutput();
            output.getShowToastMessage()
                    .compose(bindToLifecycle())
                    .observeOn(getUiScheduler())
                    .subscribe(s -> Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show());
        }

        onBindData(view);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (getViewModel() != null) {
            getViewModel().getInput().onAttachView();
        }
        setViewModelViewEvent();
    }

    protected void setViewModelViewEvent() {

    }

    @Override
    public void onPause() {
        super.onPause();

        if (getViewModel() != null) {
            getViewModel().getInput().onDetachView();
        }
    }

    public void onInject() {

    }

    protected void onBindData(View view) {
    }
}
