package com.app.cellstudio.holidayplanner.interactor.model

import com.app.cellstudio.holidayplanner.data.entity.Page
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by coyan on 13/09/2018.
 */

@PaperParcel
open class PageModel : PaperParcelable {

    var title: String? = ""
    var icon: String? = ""

    constructor( title: String?, icon: String?) {
        this.title = title
        this.icon = icon
    }

    companion object {
        @JvmField val CREATOR = PaperParcelPageModel.CREATOR

        @JvmStatic fun create(page : Page): PageModel {
            var model = PageModel(page.title, page.icon)
            return model
        }
    }
}