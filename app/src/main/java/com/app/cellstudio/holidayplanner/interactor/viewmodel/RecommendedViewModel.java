package com.app.cellstudio.holidayplanner.interactor.viewmodel;

import com.app.cellstudio.holidayplanner.interactor.model.BestHolidayWithAnnualLeaveModel;

import java.util.List;

/**
 * Created by coyan on 10/10/2018.
 */

public interface RecommendedViewModel extends ViewModel {

    void initRecommendedViewModel();
    List<BestHolidayWithAnnualLeaveModel>getBestHolidayWithAnnualLeaveModels();

}
