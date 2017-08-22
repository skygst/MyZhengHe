package com.gst.okhttp.model;

import com.gst.okhttp.http.HttpConnect;
import com.gst.okhttp.utils.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gst-pc on 2017/6/2.
 */

public class SecurityCodeModelImpl implements SecurityCodeModel {

    @Override
    public void securityCode(String url, String phone, String clientId, final OnLoadDataListener listener) {
        url = "http://oauth.bbpapp.com/oauth2/authcode?";
        List<OkHttpUtils.Param> params = new ArrayList<OkHttpUtils.Param>();
        params.add(new OkHttpUtils.Param("phone", phone));
        params.add(new OkHttpUtils.Param("client_id", clientId));
        HttpConnect.postData(url, params, listener);

//        LogUtils.d("----------------securityCode----------------------", "YYYYYYYYYYYYYY");
//        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
//            @Override
//            public void onSuccess(String response) {
//                LogUtils.d("---------SecurityCodeModelImpl ---------- response------------>", response);
//                // TODO 可以对此进行解析
////                listener.onSuccess(newsBeanList);
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                listener.onFailure("load news list failure.", e);
//            }
//        };
//        OkHttpUtils.post(url, loadNewsCallback, params);
    }
}
