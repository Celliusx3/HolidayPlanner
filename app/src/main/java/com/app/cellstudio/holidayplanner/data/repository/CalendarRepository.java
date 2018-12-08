package com.app.cellstudio.holidayplanner.data.repository;

import com.app.cellstudio.holidayplanner.data.entity.Holiday;

import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by coyan on 14/09/2018.
 */

public interface CalendarRepository {

    List<Holiday> getPublicHoliday();
    List<LocalDate> getParticularDayWithinRange(LocalDate startDate, LocalDate endDate, int dayOfWeek);
    LocalDate getTodayDate ();
    LocalDate getLastDate ();

}
