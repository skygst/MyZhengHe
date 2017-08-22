package com.gst.okhttp.model;

import com.gst.okhttp.beans.NewsDetailBean;

/**
 * Created by gst-pc on 2017/6/2.
 */

public interface OnLoadNewsDetailListener {

    void onSuccess(NewsDetailBean newsDetailBean);

    void onFailure(String msg, Exception e);

}
