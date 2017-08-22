package com.gst.okhttp.model;

import com.gst.okhttp.beans.NewsBean;

import java.util.List;

/**
 * Created by gst-pc on 2017/6/2.
 */

public interface OnLoadNewsListListener {

    void onSuccess(List<NewsBean> list);

    void onFailure(String msg, Exception e);

}
