package com.gst.okhttp.model;

/**
 * Created by gst-pc on 2017/6/13.
 */

public interface OnLoadDataListener {

    void onSuccess(String response);

    void onFailure(String msg, Exception e);

}
