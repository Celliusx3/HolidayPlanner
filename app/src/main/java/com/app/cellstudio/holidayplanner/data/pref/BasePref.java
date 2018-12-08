package com.app.cellstudio.holidayplanner.data.pref;

import com.app.cellstudio.holidayplanner.data.entity.Region;

import java.util.List;

/**
 * Created by coyan on 10/09/2018.
 */

public interface BasePref {
    Region getRegion();
    void setRegion(Region region);

    List<Integer> getNonWorkingDays();
    void setNonWorkingDays(List<Integer> nonWorkingDays);
}
