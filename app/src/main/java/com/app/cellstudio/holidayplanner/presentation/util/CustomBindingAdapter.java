package com.app.cellstudio.holidayplanner.presentation.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.app.cellstudio.holidayplanner.R;
import com.app.cellstudio.holidayplanner.presentation.image.BaseImageLoader;
import com.app.cellstudio.holidayplanner.presentation.image.model.Icon;

/**
 * Created by coyan on 13/09/2018.
 */


public class CustomBindingAdapter {

    @BindingAdapter({"visible"})
    public static void setVisible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter({"invisible"})
    public static void setInvisible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }

    @BindingAdapter("android:src")
    public static void setImageUri(ImageView view, String imageUri) {
        BaseImageLoader.getInstance().displayImage(imageUri, view);
    }

    @BindingAdapter("srcOffline")
    public static void setImageUriOffline(ImageView view, String imageUri) {
        BaseImageLoader.getInstance().displayImageOffline(imageUri, view);
    }

    @BindingAdapter("android:src")
    public static void setImageDrawable(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }

    @BindingAdapter("android:src")
    public static void setIcon(ImageView imageView, String iconName){
        Icon icon = Icon.fromString(iconName);
        int resource;
        switch (icon){
            case HOME:
                resource = R.drawable.ic_home_24dp;
                break;
            case SETTINGS:
                resource = R.drawable.ic_settings_24dp;
                break;
            case CREDITS:
                resource = R.drawable.ic_credit_24dp;
                break;
            case CALENDAR:
                resource = R.drawable.ic_date_range_24dp;
                break;
            case LISTING:
                resource = R.drawable.ic_list_24dp;
                break;
            case STAR:
                resource = R.drawable.ic_star_24dp;
                break;
            default:
                resource = R.drawable.ic_unknown_24dp;
                break;
        }
        imageView.setImageResource(resource);
    }

    @BindingAdapter("src_rawImage")
    public static void setImageResourceFitCenter(ImageView imageView, String resource){
        BaseImageLoader.getInstance().displayRawImage(resource, imageView, imageView.getScaleType());
    }

    @BindingAdapter("textWatcher")
    public static void bindTextWatcher(View view, TextWatcher textWatcher) {
        if (view instanceof EditText) {
            ((EditText) view).addTextChangedListener(textWatcher);
        }
    }
}
