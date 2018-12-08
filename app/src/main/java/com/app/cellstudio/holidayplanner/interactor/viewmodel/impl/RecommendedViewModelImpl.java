package com.app.cellstudio.holidayplanner.interactor.viewmodel.impl;

import com.app.cellstudio.holidayplanner.data.entity.Holiday;
import com.app.cellstudio.holidayplanner.data.repository.CalendarRepository;
import com.app.cellstudio.holidayplanner.data.repository.ConfigRepository;
import com.app.cellstudio.holidayplanner.interactor.model.BestHolidayWithAnnualLeaveModel;
import com.app.cellstudio.holidayplanner.interactor.model.HolidayModel;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.BaseViewModel;
import com.app.cellstudio.holidayplanner.interactor.viewmodel.RecommendedViewModel;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by coyan on 10/10/2018.
 */

public class RecommendedViewModelImpl extends BaseViewModel implements RecommendedViewModel {

    private CalendarRepository calendarRepository;
    private List<BestHolidayWithAnnualLeaveModel> bestHolidayWithAnnualLeaveModels;

    public RecommendedViewModelImpl(ConfigRepository configRepository, CalendarRepository calendarRepository, BaseSchedulerProvider scheduler) {
        super(configRepository, scheduler);
        this.calendarRepository = calendarRepository;
    }

    @Override
    public void initRecommendedViewModel() {
        this.initBestHolidayWithAnnualLeaves();
    }

    @Override
    public List<BestHolidayWithAnnualLeaveModel> getBestHolidayWithAnnualLeaveModels() {
        return bestHolidayWithAnnualLeaveModels;
    }

    private void initBestHolidayWithAnnualLeaves() {

        // Initialize
        List<LocalDate> allHolidays = getAllHolidaysIncludingSaturdayAndSundayForParticularRange();
        List<Integer> dateDifferences = getDateDifferencesBetweenPreviousAndNextInArray(allHolidays);

        // Get BestDays
        List<BestHolidayWithAnnualLeaveModel> bestHolidayWithAnnualLeaveModels = new ArrayList<>();

        for (int i = 0 ; i < allHolidays.size() - 1; i ++) {
            LocalDate startDate = allHolidays.get(i);
            LocalDate endDate = allHolidays.get(i);
            List<LocalDate> leavesNeeded = new ArrayList<>();
            for (int j = i + 1; j < allHolidays.size(); j ++){
                if (dateDifferences.get(j) >= 0 && dateDifferences.get(j) < 3){

                    endDate = allHolidays.get(j);

                    // Setup leaves needed
                    LocalDate tempDate = allHolidays.get(i);
                    while (tempDate.isBefore(endDate)) {
                        if (!allHolidays.contains(tempDate) && !leavesNeeded.contains(tempDate))
                            leavesNeeded.add(tempDate);
                        tempDate = tempDate.plusDays(1);
                    }

                } else{
                    if (!(startDate.equals(endDate))){
                        BestHolidayWithAnnualLeaveModel tempBestHoliday = new BestHolidayWithAnnualLeaveModel(
                                startDate, endDate, leavesNeeded);
                        bestHolidayWithAnnualLeaveModels.add(tempBestHoliday);
                    }
                    break;
                }
            }
        }

        // Filter out the day before current day is holiday ie 26/09/2018 - 01/10/2018 and 27/09/2018 - 01/10/2018
        List<BestHolidayWithAnnualLeaveModel> bestHolidayWithAnnualLeaveModelsFiltered = new CopyOnWriteArrayList<>();
        bestHolidayWithAnnualLeaveModelsFiltered.addAll(bestHolidayWithAnnualLeaveModels);
        for (int i = 0 ; i < bestHolidayWithAnnualLeaveModels.size(); i ++) {
            for (int j = i ; j < bestHolidayWithAnnualLeaveModels.size(); j ++) {
                if (bestHolidayWithAnnualLeaveModels.get(j).getStartDate().isAfter(bestHolidayWithAnnualLeaveModels.get(i).getStartDate()) &&
                        bestHolidayWithAnnualLeaveModels.get(j).getStartDate().isBefore(bestHolidayWithAnnualLeaveModels.get(i).getEndDate())) {
                    bestHolidayWithAnnualLeaveModelsFiltered.remove(bestHolidayWithAnnualLeaveModels.get(j));
                }
            }
        }

        // Remove Saturday and Sunday Holidays
        List<BestHolidayWithAnnualLeaveModel> bestHolidayWithAnnualLeaveModelsFilteredOutSaturdayAndSunday = new ArrayList<>();

        for (int i = 0 ; i < bestHolidayWithAnnualLeaveModelsFiltered.size(); i ++) {
            if (bestHolidayWithAnnualLeaveModelsFiltered.get(i).getLeaveDates()!= null &&
                    !bestHolidayWithAnnualLeaveModelsFiltered.get(i).getLeaveDates().isEmpty()){
                bestHolidayWithAnnualLeaveModelsFilteredOutSaturdayAndSunday.add(bestHolidayWithAnnualLeaveModelsFiltered.get(i));
            }
        }


        this.bestHolidayWithAnnualLeaveModels = bestHolidayWithAnnualLeaveModelsFilteredOutSaturdayAndSunday;
    }

    private List<LocalDate> getAllHolidaysIncludingSaturdayAndSundayForParticularRange() {
        // Init array
        List<LocalDate> allHolidays = new ArrayList<>();
        LocalDate startingDate = calendarRepository.getTodayDate();
        LocalDate endingDate = calendarRepository.getLastDate();

        // Get All Public Holidays
        for (HolidayModel holidayModel : getHolidaysBasedOnRegion()) {
            if (holidayModel.getDate().isAfter(startingDate) && holidayModel.getDate().isBefore(endingDate))
                allHolidays.add(holidayModel.getDate());
        }

        // Get All Non Working Days
        allHolidays.addAll(getNonWorkingDays(startingDate, endingDate));

        // Remove Multiple Same Date
        allHolidays = new ArrayList<>(new HashSet<>(allHolidays));

        Collections.sort(allHolidays);
        return allHolidays;
    }

    private List<HolidayModel> getHolidaysBasedOnRegion() {

        List<HolidayModel> holidayModelsBasedOnRegion = new ArrayList<>();

        for (Holiday holiday : calendarRepository.getPublicHoliday()) {
            if (isHolidayForTheRegion(holiday)) {
                HolidayModel holidayModel = HolidayModel.create(holiday, calendarRepository.getTodayDate());
                holidayModelsBasedOnRegion.add(holidayModel);
            }
        }

        return holidayModelsBasedOnRegion;
    }

    private boolean isHolidayForTheRegion(Holiday holiday) {
        return holiday.getHolidayRegion().contains(configRepository.getRegion());
    }

    private List<Integer> getDateDifferencesBetweenPreviousAndNextInArray(List<LocalDate> allHolidays) {
        List<Integer> dateDifferences = new ArrayList<>();
        List<LocalDate> allHolidaysWithOneExtraStart = new ArrayList<>();

        allHolidaysWithOneExtraStart.add(allHolidays.get(0));
        allHolidaysWithOneExtraStart.addAll(allHolidays);

        for (int i = 0; i < allHolidays.size(); i++) {
            dateDifferences.add(Days.daysBetween(allHolidaysWithOneExtraStart.get(i),
                    allHolidays.get(i)).getDays() - 1);
        }

        return dateDifferences;

    }

    private List<LocalDate> getNonWorkingDays(LocalDate startingDate, LocalDate endingDate){
        List<LocalDate> nonWorkingDays = new ArrayList<>();
        List<Integer> nonWorkingDaysInAWeek = configRepository.getNonWorkingDayInAWeek();
        if (nonWorkingDaysInAWeek!= null && !nonWorkingDaysInAWeek.isEmpty()){
            for (Integer dayInAWeek : nonWorkingDaysInAWeek) {
                nonWorkingDays.addAll(calendarRepository.getParticularDayWithinRange
                        (startingDate, endingDate, dayInAWeek));
            }
        }
        return nonWorkingDays;
    }
}
