package com.app.cellstudio.holidayplanner.interactor.model

import com.app.cellstudio.holidayplanner.data.entity.Holiday
import com.app.cellstudio.holidayplanner.data.entity.Region
import com.app.cellstudio.holidayplanner.interactor.util.TimeUtil
import org.joda.time.LocalDate

/**
 * Created by coyan on 16/09/2018.
 */

open class HolidayModel {

    var date: LocalDate? = null
    var year: Int ?= 0
    var month: Int ?= 0
    var day: Int ?= 0
    var dateToDisplay: String ?= ""
    var dayInAWeek: String ?= ""
    var remainingDaysFromNow: String ?= ""
    var title: String? = ""
    var region: List<Region> ?= null

    constructor( date: String?, title: String?, region: List<Region> ?, todayDate: LocalDate) {
        var items = date?.split("/")
        this.dateToDisplay = date
        if (items!!.size >= 2) {
            this.date = LocalDate(Integer.parseInt(items.get(2)), Integer.parseInt(items.get(1)),
                    Integer.parseInt(items.get(0)))
            this.year = Integer.parseInt(items.get(2))
            this.month = Integer.parseInt(items.get(1))
            this.day = Integer.parseInt(items.get(0))
            this.dayInAWeek = TimeUtil.getDayOfWeek(this.date)
            this.remainingDaysFromNow = TimeUtil.getDaysBetween(todayDate, this.date)
        }
        this.title = title
        this.region = region
    }

    companion object {
        @JvmStatic fun create(holiday: Holiday, todayDate: LocalDate): HolidayModel {
            return HolidayModel(holiday.holidayDate, holiday.holidayTitle, holiday.holidayRegion, todayDate)
        }
    }
}