package com.app.cellstudio.holidayplanner.interactor.scheduler;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;

/**
 * Created by coyan on 10/09/2018.
 */

public interface BaseSchedulerProvider {
    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}