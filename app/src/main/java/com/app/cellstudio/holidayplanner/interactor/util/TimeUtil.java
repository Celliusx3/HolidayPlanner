package com.app.cellstudio.holidayplanner.interactor.util;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

/**
 * Created by coyan on 01/10/2018.
 */

public class TimeUtil {

    public static String getDayOfWeek(LocalDate localDate) {
        return DateTimeFormat.forPattern("EEEE").print(localDate);
    }

    public static String getDaysBetween(LocalDate firstDate, LocalDate secondDate){
        Days days = Days.daysBetween(firstDate, secondDate);
        return days.getDays() + " Days";
    }
}
