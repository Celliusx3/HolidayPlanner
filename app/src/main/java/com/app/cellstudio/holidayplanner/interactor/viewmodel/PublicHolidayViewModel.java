package com.app.cellstudio.holidayplanner.interactor.viewmodel;

import com.app.cellstudio.holidayplanner.interactor.model.YearHolidayModel;
import com.app.cellstudio.holidayplanner.interactor.model.HolidayModel;

import java.util.List;

/**
 * Created by coyan on 16/09/2018.
 */

public interface PublicHolidayViewModel extends ViewModel {

    List<HolidayModel> getAllPublicHolidays();
    List<HolidayModel> getAllPublicHolidaysBasedOnRegion();
    List<YearHolidayModel> getHolidayInAYearModel();


}
