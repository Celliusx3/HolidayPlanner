package com.app.cellstudio.holidayplanner.presentation.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

/**
 * Created by coyan on 13/09/2018.
 */

public interface ImageLoader {
    void init(Context context);
    boolean isInitialized();
    void displayImage(String uri, ImageView imageView);
    void displayImageOffline(String uri, ImageView imageView);
    void displayRawImage(String uri, final ImageView imageView, ImageView.ScaleType scaleType);
    void displayImage(String uri, ImageView imageView, @DrawableRes int resError);
    void loadImage(String uri, ImageLoadingListener listener);

    interface ImageLoadingListener {
        void onSuccess(Bitmap loadedImage);
        void onFailure();
    }
}