package com.app.cellstudio.holidayplanner.interactor.viewmodel.impl;

import com.app.cellstudio.holidayplanner.data.entity.Holiday;
import com.app.cellstudio.holidayplanner.data.repository.CalendarRepository;
import com.app.cellstudio.holidayplanner.data.repository.ConfigRepository;
import com.app.cellstudio.holidayplanner.interactor.model.HolidayModel;
import com.app.cellstudio.holidayplanner.interactor.model.YearHolidayModel;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.BaseViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.PublicHolidayViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by coyan on 16/09/2018.
 */

public class PublicHolidayViewModelImpl extends BaseViewModel implements PublicHolidayViewModel {

    private CalendarRepository calendarRepository;

    public PublicHolidayViewModelImpl(ConfigRepository configRepository, CalendarRepository calendarRepository, BaseSchedulerProvider scheduler) {
        super(configRepository, scheduler);
        this.calendarRepository = calendarRepository;
    }

    @Override
    public List<HolidayModel> getAllPublicHolidays() {
        List<HolidayModel> holidayModels = new ArrayList<>();
        for (Holiday holiday: calendarRepository.getPublicHoliday()) {
            HolidayModel holidayModel = HolidayModel.create(holiday, calendarRepository.getTodayDate());
            holidayModels.add(holidayModel);
        }
        return holidayModels;
    }

    @Override
    public List<HolidayModel> getAllPublicHolidaysBasedOnRegion() {
        List<HolidayModel> holidayModels = new ArrayList<>();
        for (Holiday holiday: calendarRepository.getPublicHoliday()) {
            if (isHolidayForTheRegion(holiday)) {
                HolidayModel holidayModel = HolidayModel.create(holiday, calendarRepository.getTodayDate());
                holidayModels.add(holidayModel);
            }
        }
        return holidayModels;
    }

    @Override
    public List<YearHolidayModel> getHolidayInAYearModel() {
        List<YearHolidayModel> yearHolidayModels = new ArrayList<>();
        HashMap<Integer, ArrayList<HolidayModel>> maps = new HashMap<Integer, ArrayList<HolidayModel>>();

        Iterator<HolidayModel> iterator = getAllPublicHolidaysBasedOnRegion().iterator();
        while(iterator.hasNext()){
            HolidayModel holidayModel = iterator.next();
            Integer key = holidayModel.getYear();

            if(!maps.containsKey(key)){
                ArrayList<HolidayModel> x = new ArrayList<HolidayModel>();
                x.add(holidayModel);

                maps.put(key, x);
            } else {
                //already have it, add directly
                maps.get(key).add(holidayModel);
            }
        }

        for(Map.Entry<Integer, ArrayList<HolidayModel>> entry : maps.entrySet()) {
            YearHolidayModel yearHolidayModel = new YearHolidayModel(entry.getKey(), entry.getValue());
            yearHolidayModels.add(yearHolidayModel);
        }

        Collections.sort(yearHolidayModels, new Comparator<YearHolidayModel>() {
            @Override
            public int compare(YearHolidayModel o1, YearHolidayModel o2) {
                return (o1.getYear() - o2.getYear());
            }
        });

        return yearHolidayModels;
    }

    private boolean isHolidayForTheRegion(Holiday holiday){
        return holiday.getHolidayRegion().contains(configRepository.getRegion());
    }
}
