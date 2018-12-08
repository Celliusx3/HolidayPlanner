package com.app.cellstudio.holidayplanner.interactor.model

import org.joda.time.Days
import org.joda.time.LocalDate

/**
 * Created by coyan on 09/10/2018.
 */

open class BestHolidayWithAnnualLeaveModel {

    var startDate: LocalDate ?= null
    var endDate: LocalDate ?= null
    var leaveDates: List<LocalDate> ?= null

    constructor( startDate: LocalDate, endDate: LocalDate, leaveDates: List<LocalDate>) {
        this.startDate = startDate
        this.endDate = endDate
        this.leaveDates = leaveDates
    }

    fun getNumberOfLeavesNeeded(): Int {
        return leaveDates?.size ?: 0
    }

    fun getNumberOfHolidays(): Int {
        return Days.daysBetween(startDate, endDate).days + 1
    }

    fun getLeaveDatesInString(): String {
        var leaveDatesInString = ""
        for ((index, value) in leaveDates!!.withIndex()){
            leaveDatesInString += if (index >= leaveDates!!.size - 1){
                value.toString()
            } else {
                value.toString() + ", "
            }
        }

        return leaveDatesInString;
    }

    fun getDurationInString(): String {
        return startDate.toString() + " - " + endDate.toString();
    }
}