package com.app.cellstudio.holidayplanner.data.pref.impls;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.cellstudio.holidayplanner.data.entity.Region;
import com.app.cellstudio.holidayplanner.data.pref.BasePref;
import com.app.cellstudio.holidayplanner.interactor.util.TextUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by coyan on 10/09/2018.
 */

public class BaseSharedPref implements BasePref {

    private static final String REGION = "REGION";
    private static final String NON_WORKING_DAY = "NON_WORKING_DAY";

    private final SharedPreferences preferences;
    private final Context context;

    public BaseSharedPref(Context context, SharedPreferences sharedPreferences) {
        this.context = context;
        this.preferences = sharedPreferences;
    }

    private String getString(String key) {
        return preferences.getString(key, "");
    }

    private void putString(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    @Override
    public Region getRegion() {
        return !TextUtil.isEmpty(getString(REGION)) ? Region.valueOf(getString(REGION)): null;
    }

    @Override
    public void setRegion(Region region) {
        putString(REGION, region.name());
    }

    @Override
    public List<Integer> getNonWorkingDays() {
        String str = getString(NON_WORKING_DAY);//you need to retrieve this string from shared preferences.

        Type type = new TypeToken<List<Integer>>() { }.getType();
        List<Integer> nonWorkingDays = new Gson().fromJson(str, type);

        return nonWorkingDays;
    }

    @Override
    public void setNonWorkingDays(List<Integer> nonWorkingDays) {
        String nonWorkingDaysInString = new Gson().toJson(nonWorkingDays);
        putString(NON_WORKING_DAY, nonWorkingDaysInString);
    }
}
