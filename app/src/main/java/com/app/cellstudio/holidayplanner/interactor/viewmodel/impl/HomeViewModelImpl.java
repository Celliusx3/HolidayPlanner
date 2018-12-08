package com.app.cellstudio.holidayplanner.interactor.viewmodel.impl;

import com.app.cellstudio.holidayplanner.data.entity.Holiday;
import com.app.cellstudio.holidayplanner.data.repository.CalendarRepository;
import com.app.cellstudio.holidayplanner.data.repository.ConfigRepository;
import com.app.cellstudio.holidayplanner.interactor.model.HolidayModel;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.BaseViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.HomeViewModel;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coyan on 14/09/2018.
 */

public class HomeViewModelImpl extends BaseViewModel implements HomeViewModel {

    private CalendarRepository calendarRepository;
    private List<HolidayModel> holidayModelsBasedOnRegion = new ArrayList<>();
    private HolidayModel todayHoliday;
    private List<HolidayModel> upcomingHolidays = new ArrayList<>();

    public HomeViewModelImpl(ConfigRepository configRepository, CalendarRepository calendarRepository,
                             BaseSchedulerProvider schedulerProvider) {
        super(configRepository, schedulerProvider);
        this.calendarRepository = calendarRepository;

    }

    @Override
    public void initHomeViewModel() {
        this.initHolidayModelsBasedOnRegion();
        this.initTodayHoliday();
        this.initUpcomingHolidays(5);
    }

    @Override
    public HolidayModel getTodayHolidayModel() {
        return todayHoliday;
    }

    @Override
    public List<HolidayModel> getUpcomingHolidayModels() {
        if (upcomingHolidays == null || upcomingHolidays.size() <= 0)
            upcomingHolidays.add(new HolidayModel("", "No Upcoming Holiday", null, calendarRepository.getTodayDate()));
        return upcomingHolidays;
    }

    private void initTodayHoliday() {
        LocalDate todayDate = calendarRepository.getTodayDate();
        for (HolidayModel holidayModel : holidayModelsBasedOnRegion) {
            if (holidayModel.getDate().equals(todayDate)) {
                this.todayHoliday = holidayModel;
                return;
            }
        }
        this.todayHoliday = new HolidayModel(todayDate.toString(), "No Holiday Today", null, todayDate);
    }

    private void initUpcomingHolidays(int length) {
        for (HolidayModel holidayModel : holidayModelsBasedOnRegion) {
            if (this.upcomingHolidays.size() > length)
                return;
            if (holidayModel.getDate().isAfter(calendarRepository.getTodayDate()))
                this.upcomingHolidays.add(holidayModel);
        }
    }

    private void initHolidayModelsBasedOnRegion() {
        for (Holiday holiday : calendarRepository.getPublicHoliday()) {
            if (isHolidayForTheRegion(holiday)) {
                HolidayModel holidayModel = HolidayModel.create(holiday, calendarRepository.getTodayDate());
                holidayModelsBasedOnRegion.add(holidayModel);
            }
        }
    }


    private boolean isHolidayForTheRegion(Holiday holiday) {
        return holiday.getHolidayRegion().contains(configRepository.getRegion());
    }
}