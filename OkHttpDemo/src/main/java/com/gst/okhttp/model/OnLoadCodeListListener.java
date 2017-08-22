package com.gst.okhttp.model;

/**
 * Created by gst-pc on 2017/6/2.
 */

public interface OnLoadCodeListListener {

    void onSuccess(String result);

    void onFailure(String msg, Exception e);

}
