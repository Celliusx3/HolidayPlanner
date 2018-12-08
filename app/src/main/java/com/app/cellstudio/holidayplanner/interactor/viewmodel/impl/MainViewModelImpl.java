package com.app.cellstudio.holidayplanner.interactor.viewmodel.impl;

import com.app.cellstudio.holidayplanner.data.repository.ConfigRepository;
import com.app.cellstudio.holidayplanner.interactor.model.PageModel;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.BaseViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.MainViewModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by coyan on 13/09/2018.
 */

public class MainViewModelImpl extends BaseViewModel implements MainViewModel {
    private PublishSubject<List<PageModel>> drawerItems = PublishSubject.create();
    private PublishSubject<PageModel> selectedPageModel = PublishSubject.create();

    public MainViewModelImpl(ConfigRepository configRepository, BaseSchedulerProvider schedulerProvider) {
        super(configRepository, schedulerProvider);
    }

    @Override
    public boolean isShowNonWorkingDialog() {
        List<Integer> nonWorkingDays = configRepository.getNonWorkingDayInAWeek();
        if (nonWorkingDays != null)
            return false;
        return true;
    }

    @Override
    public void generateViewData() {
        Observable.just(configRepository.getPages())
            .compose(applySchedulers())
            .flatMapIterable(pages-> pages)
            .map(PageModel::create)
            .toList()
            .subscribe(drawerModel -> drawerItems.onNext(drawerModel), throwable -> {

            });
    }

    @Override
    public void onSelectedNonWorkingDays(List<Integer> nonWorkingDays) {
        configRepository.setNonWorkingDayInAWeek(nonWorkingDays);
    }

    @Override
    public Observable<List<PageModel>> getDrawerItems() {
        return drawerItems;
    }

    @Override
    public PublishSubject<PageModel> getSelectedPageModel() {
        return selectedPageModel;
    }
}
