package com.gst.okhttp.presenter;

import com.gst.okhttp.model.OnLoadDataListener;
import com.gst.okhttp.model.SecurityCodeModel;
import com.gst.okhttp.model.SecurityCodeModelImpl;
import com.gst.okhttp.utils.LogUtils;

/**
 * Created by gst-pc on 2017/6/2.
 */
public class SecurityCodePresenterImpl implements SecurityCodePresenter, OnLoadDataListener {

    private SecurityCodeModel mModel;

    public SecurityCodePresenterImpl() {
        LogUtils.d("--------SecurityCodePresenterImpl---------", "XXXXXXXXXXX");
        mModel = new SecurityCodeModelImpl();
    }

    @Override
    public void getCode(String phone, String clientId) {
        String url = "http://oauth.bbpapp.com/oauth2/authcode?";
        LogUtils.d("----------url------>", url);
        //只有第一页的或者刷新的时候才显示刷新进度条
        mModel.securityCode(url, "13917239556", "5e4399d6fed53b077c76", this);
    }

    @Override
    public void onSuccess(String result) {
        LogUtils.d("-------SecurityCodePresenterImpl-----onSuccess---------", "0000000000" + result);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        LogUtils.d("-------SecurityCodePresenterImpl-----onSuccess---------", "11111111111" + e.getStackTrace());
    }
}
