package com.gst.frame.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by gst-pc on 2017/11/3.
 */

public class MImageGetter implements Html.ImageGetter {
    Context c;
    TextView container;

    public MImageGetter(TextView text, Context c) {
        this.c = c;
        this.container = text;
    }

    public Drawable getDrawable(String source) {
        final LevelListDrawable drawable = new LevelListDrawable();
        Glide.with(c).load(source).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if(resource != null) {

                    int fontH = (int) ((25) * 1.5);
                    int height = fontH;
                    int width = (int) ((float) drawable.getIntrinsicWidth() / (float) drawable
                            .getIntrinsicHeight()) * fontH;
                    if (width == 0) {
                        width = drawable.getIntrinsicWidth();
                    }

                    BitmapDrawable bitmapDrawable = new BitmapDrawable(resource);
                    drawable.addLevel(1, 1, bitmapDrawable);
                    drawable.setBounds(0, 0, width,height);
//                    drawable.setBounds(0, 0, resource.getWidth(),resource.getHeight());
                    drawable.setLevel(1);
                    container.invalidate();
                    container.setText(container.getText());
                }
            }
        });
        return drawable;
    }

}