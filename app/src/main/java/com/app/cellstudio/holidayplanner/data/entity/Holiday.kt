package com.app.cellstudio.holidayplanner.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by coyan on 12/10/2018.
 */

open class Holiday {
    val holidayDate: String? = ""
    val holidayTitle: String? = ""
    @SerializedName("holidayRegion")
    val holidayRegion: List<Region> ?= null
}