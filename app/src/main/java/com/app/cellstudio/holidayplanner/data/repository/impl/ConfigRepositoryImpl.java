package com.app.cellstudio.holidayplanner.data.repository.impl;

import android.content.Context;

import com.app.cellstudio.holidayplanner.data.entity.Page;
import com.app.cellstudio.holidayplanner.data.entity.Region;
import com.app.cellstudio.holidayplanner.data.local.LocalRoutes;
import com.app.cellstudio.holidayplanner.data.pref.BasePref;
import com.app.cellstudio.holidayplanner.data.repository.ConfigRepository;
import com.app.cellstudio.holidayplanner.data.util.AssetUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.joda.time.LocalDate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by coyan on 13/09/2018.
 */

public class ConfigRepositoryImpl implements ConfigRepository {
    private final Context context;
    private final Gson gson;
    private final BasePref basePref;

    public ConfigRepositoryImpl(Context context, BasePref basePref) {
        this.context = context;
        this.gson = new GsonBuilder().create();
        this.basePref = basePref;
    }

    @Override
    public List<Page> getPages() {
        String page = AssetUtil.loadFromAsset(context, LocalRoutes.PAGE);
        Type pageListType = new TypeToken<ArrayList<Page>>(){}.getType();
        List<Page> pages = gson.fromJson(page, pageListType);
        return pages;
    }

    @Override
    public Region getRegion() {
        if (basePref.getRegion() == null) {
            return Region.SELANGOR;
        }
        return basePref.getRegion();
    }

    @Override
    public void setCurrentRegion(Region region) {
        basePref.setRegion(region);
    }

    @Override
    public List<Integer> getNonWorkingDayInAWeek() {
        return basePref.getNonWorkingDays();
    }

    @Override
    public void setNonWorkingDayInAWeek(List<Integer> localDates) {
        basePref.setNonWorkingDays(localDates);
    }
}
