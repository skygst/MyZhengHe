package com.gst.zhenghe.mvp.model;

/**
 * Created by gst-pc on 2017/6/2.
 */
public interface NewsModel {

    void loadNews(String url, OnLoadDataListener listener);

    void loadNewsDetail(String docid, OnLoadDataListener listener);

}
