package com.app.cellstudio.holidayplanner.data.repository;

import com.app.cellstudio.holidayplanner.data.entity.Page;
import com.app.cellstudio.holidayplanner.data.entity.Region;

import java.util.List;

/**
 * Created by coyan on 13/09/2018.
 */

public interface ConfigRepository {
    List<Page> getPages();

    Region getRegion();
    void setCurrentRegion(Region region);

    List<Integer> getNonWorkingDayInAWeek();
    void setNonWorkingDayInAWeek(List<Integer> nonWorkingDayInAWeek);
}
