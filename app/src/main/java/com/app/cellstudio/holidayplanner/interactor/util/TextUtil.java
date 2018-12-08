package com.app.cellstudio.holidayplanner.interactor.util;

import android.support.annotation.Nullable;

/**
 * Created by coyan on 16/09/2018.
 */

public class TextUtil {

    public static boolean isEmpty(@Nullable CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }

    public static String firstLetterUppercase(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public static boolean isEmptyIncludeSpaceCheck(@Nullable CharSequence str) {
        return isEmpty(str) || isEmpty(str.toString().trim());
    }

}

