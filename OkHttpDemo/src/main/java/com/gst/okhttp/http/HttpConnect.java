package com.gst.okhttp.http;

import com.gst.okhttp.model.OnLoadDataListener;
import com.gst.okhttp.utils.LogUtils;
import com.gst.okhttp.utils.OkHttpUtils;

import java.util.List;

/**
 * Created by gst-pc on 2017/6/13.
 */

public class HttpConnect {

    // OkHttp封装 get接口
    public static void getData(String url, final OnLoadDataListener listener) {
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
//                List<NewsBean> newsBeanList = NewsJsonUtils.readJsonNewsBeans(response, getID(type));
                LogUtils.d("-------getData---response--11--->", response);
                listener.onSuccess(response);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news list failure.", e);
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);
    }

    // OkHttp封装 post接口
    public static void postData(String url, List<OkHttpUtils.Param> params, final OnLoadDataListener listener) {
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                LogUtils.d("---------HttpConnect ----postData------ response------------>", response);
                // TODO 可以对此进行解析
                listener.onSuccess(response);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news list failure.", e);
            }
        };
        OkHttpUtils.post(url, loadNewsCallback, params);
    }

}
