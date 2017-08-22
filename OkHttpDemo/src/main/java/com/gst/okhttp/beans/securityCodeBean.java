package com.gst.okhttp.beans;

import java.io.Serializable;

/**
 * Created by gst-pc on 2017/6/12.
 */

public class securityCodeBean implements Serializable {

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    private String code;

}
