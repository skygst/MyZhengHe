package com.gst.frame.bean;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by gst-pc on 2017/2/7.
 */

public class RetrofitWrapper {

    private static RetrofitWrapper instance;
    private Context mContext;
    private Retrofit retrofit;

    private RetrofitWrapper() {
        retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create()) //增加返回值为String的支持
                .addConverterFactory(GsonConverterFactory.create()) //增加返回值为Gson的支持(以实体类返回)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //增加返回值为Oservable<T>的支持
                .build();
    }

    public static RetrofitWrapper getInstance() {
        if (instance == null) {
            synchronized (RetrofitWrapper.class) {
                instance = new RetrofitWrapper();
            }
        }
        return instance;
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

    public class Constant {
//        public static final String BASE_URL = "http://japi.juhe.cn";
//        public static final String BASE_URL = "http://education.ebodoo.com/api/";
        public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    }

}
