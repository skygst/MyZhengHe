package com.gst.okhttp.model;

import com.gst.okhttp.beans.securityCodeBean;

/**
 * Created by gst-pc on 2017/6/12.
 */

public interface OnLoadsecurityCodeListener {

    void onSuccess(securityCodeBean newsDetailBean);

    void onFailure(String msg, Exception e);

}
