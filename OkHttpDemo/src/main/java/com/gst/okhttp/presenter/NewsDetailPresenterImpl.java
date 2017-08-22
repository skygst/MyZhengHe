package com.gst.okhttp.presenter;

import android.content.Context;

import com.gst.okhttp.beans.NewsDetailBean;
import com.gst.okhttp.data.NewsJsonUtils;
import com.gst.okhttp.model.NewsModel;
import com.gst.okhttp.model.NewsModelImpl;
import com.gst.okhttp.model.OnLoadDataListener;
import com.gst.okhttp.okhttp_view.NewsDetailView;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 2015/12/21
 */
public class NewsDetailPresenterImpl implements NewsDetailPresenter, OnLoadDataListener {

    private Context mContent;
    private NewsDetailView mNewsDetailView;
    private NewsModel mNewsModel;
    private String mDocId;

    public NewsDetailPresenterImpl(Context mContent, NewsDetailView mNewsDetailView) {
        this.mContent = mContent;
        this.mNewsDetailView = mNewsDetailView;
        mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNewsDetail(final String docId) {
        mNewsDetailView.showProgress();
        mDocId = docId;
        mNewsModel.loadNewsDetail(docId, this);
    }

    @Override
    public void onSuccess(String response) {
        if (response != null) {
            NewsDetailBean newsDetailBean = NewsJsonUtils.readJsonNewsDetailBeans(response, mDocId);
            mNewsDetailView.showNewsDetialContent(newsDetailBean.getBody());
        }
        mNewsDetailView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsDetailView.hideProgress();
    }
}
