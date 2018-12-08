package com.app.cellstudio.holidayplanner.interactor.viewmodel;

import com.app.cellstudio.holidayplanner.interactor.model.PageModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by coyan on 13/09/2018.
 */

public interface MainViewModel extends ViewModel {
    boolean isShowNonWorkingDialog();
    void generateViewData();
    void onSelectedNonWorkingDays(List<Integer> nonWorkingDays);
    Observable<List<PageModel>> getDrawerItems();
    PublishSubject<PageModel> getSelectedPageModel();

}
