package com.app.cellstudio.holidayplanner.data.repository.impl;

import android.content.Context;

import com.app.cellstudio.holidayplanner.data.entity.Holiday;
import com.app.cellstudio.holidayplanner.data.local.LocalRoutes;
import com.app.cellstudio.holidayplanner.data.repository.CalendarRepository;
import com.app.cellstudio.holidayplanner.data.util.AssetUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.joda.time.LocalDate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by coyan on 14/09/2018.
 */

public class CalendarRepositoryImpl implements CalendarRepository {
    private final Context context;
    private final Gson gson;

    public CalendarRepositoryImpl(Context context) {
        this.context = context;
        this.gson = new GsonBuilder().create();
    }

    @Override
    public List<Holiday> getPublicHoliday() {
        String holiday = AssetUtil.loadFromAsset(context, LocalRoutes.HOLIDAY);
        Type holidayListType = new TypeToken<ArrayList<Holiday>>(){}.getType();
        return gson.fromJson(holiday, holidayListType);
    }

    @Override
    public List<LocalDate> getParticularDayWithinRange(LocalDate startDate, LocalDate endDate, int dayOfWeek) {
        List<LocalDate> particularDays = new ArrayList<>();

        LocalDate usedDate = startDate.withDayOfWeek(dayOfWeek);
        while (usedDate.isBefore(endDate)){
            particularDays.add(usedDate);
            usedDate = usedDate.plusWeeks(1);
        }
        return particularDays;
    }

    @Override
    public LocalDate getTodayDate() {
        return LocalDate.now();
    }

    @Override
    public LocalDate getLastDate() {
        List<Holiday> holidays = getPublicHoliday();
        Holiday holiday = holidays.get(holidays.size() - 1);
        String[] yearMonthDay = holiday.getHolidayDate().split("/");
        return new LocalDate(Integer.parseInt(yearMonthDay[2]), 12, 31);
    }

}
