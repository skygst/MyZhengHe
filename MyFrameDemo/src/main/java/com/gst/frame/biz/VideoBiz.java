package com.gst.frame.biz;

import android.view.View;

import com.gst.frame.data.LayoutParameters;

/**
 * Created by gst-pc on 2017/2/23.
 */

public class VideoBiz {

    /* 设置视图的位置 */
    public void setViewPosition(View view, int i, int level_position[][], float scaleQPW, float scaleQPH, int xoffset, int yoffset, float suoxiao) {
        view.setLayoutParams(LayoutParameters.setViewPositionParams(
                level_position[i][0],
                level_position[i][1],
                level_position[i][2],
                level_position[i][3], scaleQPW, scaleQPH,
                xoffset, yoffset, suoxiao));
    }

}
