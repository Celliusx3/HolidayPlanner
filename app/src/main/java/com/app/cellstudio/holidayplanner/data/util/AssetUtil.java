package com.app.cellstudio.holidayplanner.data.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by coyan on 13/09/2018.
 */

public class AssetUtil {
    public static String loadFromAsset(Context context, String fileName) {
        String data = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            data = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return data;
    }
}