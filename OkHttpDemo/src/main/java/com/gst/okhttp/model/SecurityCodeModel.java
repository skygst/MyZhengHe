package com.gst.okhttp.model;

/**
 * Created by gst-pc on 2017/6/2.
 */

public interface SecurityCodeModel {

    void securityCode(String url, String phone, String clientId, OnLoadDataListener listener);

}
