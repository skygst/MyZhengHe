package com.gst.frame.data;

import android.widget.RelativeLayout;

/**
 * Created by gst-pc on 2017/2/23.
 */

public class LayoutParameters {

    /*-----------------------视图位置及缩放--------------------------*/

    public static RelativeLayout.LayoutParams setViewPositionParams(int width, int height,
                                                                    int x, int y, float wBeisu, float hBeisu, int xoffset, int yoffset,
                                                                    float suoxiao) {

        RelativeLayout.LayoutParams params;

        params = new RelativeLayout.LayoutParams(Float.valueOf(
                width * wBeisu / suoxiao).intValue(), Float.valueOf(
                height * hBeisu / suoxiao).intValue());

        int intentTopValue = Float.valueOf(
                (y * hBeisu / suoxiao + yoffset * hBeisu / suoxiao)).intValue();
        params.topMargin = intentTopValue;

        int intentLeftValue = Float.valueOf(
                (x * wBeisu / suoxiao + xoffset * wBeisu / suoxiao)).intValue();
        params.leftMargin = intentLeftValue;
        return params;
    }

}
