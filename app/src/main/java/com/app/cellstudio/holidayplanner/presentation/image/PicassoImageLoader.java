package com.app.cellstudio.holidayplanner.presentation.image;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.widget.ImageView;

import com.app.cellstudio.holidayplanner.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by coyan on 13/09/2018.
 */

public class PicassoImageLoader implements ImageLoader {

    private Context context;

    @Override
    public void init(Context context) {
        if (context == null) {
            return;
        }
        this.context = context;

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.memoryCache(new LruCache(context));

        Picasso.setSingletonInstance(builder.build());
    }

    @Override
    public boolean isInitialized() {
        return context != null;
    }

    @Override
    public void displayImage(String uri, final ImageView imageView) {
        if (!isInitialized() || imageView == null) {
            return;
        }
        if (TextUtils.isEmpty(uri)) {
            uri = null;
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        Picasso.with(context)
                .load(uri)
                .fit()
//                .centerCrop()
                .noFade()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    }

                    @Override
                    public void onError() {
                    }
                });
    }

    @Override
    public void displayImageOffline(String uri, ImageView imageView) {
        if (!isInitialized() || imageView == null) {
            return;
        }
        if (TextUtils.isEmpty(uri)) {
            uri = null;
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        String finalUri = uri;
        Picasso.with(context)
                .load(uri)
                .fit()
                .networkPolicy(NetworkPolicy.OFFLINE)
                .noFade()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    }

                    @Override
                    public void onError() {
                        Picasso.with(context)
                                .load(finalUri)
                                .fit()
                                .noFade()
                                .into(imageView, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                    }

                                    @Override
                                    public void onError() {

                                    }
                                });
                    }
                });
    }

    @Override
    public void displayRawImage(String uri, final ImageView imageView, ImageView.ScaleType scaleType) {
        if (!isInitialized() || imageView == null) {
            return;
        }
        if (TextUtils.isEmpty(uri)) {
            uri = null;
        }

        Picasso.with(context)
                .load(uri)
                .noFade()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        imageView.setScaleType(scaleType);//def=fitCentre
                    }

                    @Override
                    public void onError() {
                    }
                });
    }

    @Override
    public void displayImage(String uri, ImageView imageView, @DrawableRes int resError) {
        if (!isInitialized() || imageView == null) {
            return;
        }
        if (TextUtils.isEmpty(uri)) {
            uri = null;
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Picasso.with(context)
                .load(uri)
                .fit()
                .centerCrop()
                .noFade()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }

                    @Override
                    public void onError() {
                        Picasso.with(context).load(resError).fit().centerCrop().noFade().into(imageView);
                    }
                });
    }

    @Override
    public void loadImage(String uri, final ImageLoadingListener listener) {
        if (!isInitialized()) {
            return;
        }
        if (TextUtils.isEmpty(uri)) {
            uri = null;
        }
        final RequestCreator requestCreator = Picasso.with(context).load(uri);
        requestCreator.fetch(new Callback() {
            @Override
            public void onSuccess() {
                Observable.just(requestCreator).subscribeOn(Schedulers.io()).subscribe(new Consumer<RequestCreator>() {
                    @Override
                    public void accept(@NonNull RequestCreator o) throws Exception {
                        try {
                            if (listener != null) {
                                listener.onSuccess(o.get());
                            }
                        } catch (IOException e) {
                            if (listener != null) {
                                listener.onFailure();
                            }
                        }
                    }
                }, throwable -> {
                    if (listener != null) {
                        listener.onFailure();
                    }
                });
            }

            @Override
            public void onError() {
                if (listener != null) {
                    listener.onFailure();
                }
            }
        });
    }
}
