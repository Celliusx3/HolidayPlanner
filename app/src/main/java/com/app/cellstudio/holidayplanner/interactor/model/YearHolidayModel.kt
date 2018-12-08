package com.app.cellstudio.holidayplanner.interactor.model

/**
 * Created by coyan on 17/09/2018.
 */

open class YearHolidayModel {

    var year: Int ?= 0
    var holidayList: List<HolidayModel> ?= null

    constructor( year: Int?, holidayList: List<HolidayModel>?) {
        this.year = year
        this.holidayList = holidayList
    }
}