package com.app.cellstudio.holidayplanner.interactor.viewmodel;

import io.reactivex.Observable;

/**
 * Created by coyan on 12/09/2018.
 */

public interface ViewModel {

    Output getOutput();

    Input getInput();

    interface ViewEvent {

    }

    interface ViewData {

    }

    interface Output {
        Observable<String> getShowToastMessage();
    }

    interface Input {
        void onCreateView();

        void onAttachView();

        void onDetachView();
    }

}

