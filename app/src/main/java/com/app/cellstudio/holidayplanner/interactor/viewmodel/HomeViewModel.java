package com.app.cellstudio.holidayplanner.interactor.viewmodel;

import com.app.cellstudio.holidayplanner.interactor.model.HolidayModel;

import java.util.List;

/**
 * Created by coyan on 14/09/2018.
 */

public interface HomeViewModel extends ViewModel {
    void initHomeViewModel();
    HolidayModel getTodayHolidayModel();
    List<HolidayModel> getUpcomingHolidayModels();
}