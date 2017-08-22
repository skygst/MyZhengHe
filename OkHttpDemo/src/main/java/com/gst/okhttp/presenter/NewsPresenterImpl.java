package com.gst.okhttp.presenter;

import com.gst.okhttp.beans.NewsBean;
import com.gst.okhttp.commons.Urls;
import com.gst.okhttp.data.NewsJsonUtils;
import com.gst.okhttp.fragment.NewsFragment;
import com.gst.okhttp.model.NewsModel;
import com.gst.okhttp.model.NewsModelImpl;
import com.gst.okhttp.model.OnLoadDataListener;
import com.gst.okhttp.okhttp_view.NewsView;
import com.gst.okhttp.utils.LogUtils;

import java.util.List;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/18
 */
public class NewsPresenterImpl implements NewsPresenter, OnLoadDataListener {

    private static final String TAG = "NewsPresenterImpl";

    private NewsView mNewsView;
    private NewsModel mNewsModel;
    private int mType;

    public NewsPresenterImpl(NewsView newsView) {
        this.mNewsView = newsView;
        this.mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNews(final int type, final int pageIndex) {
        String url = getUrl(type, pageIndex);
        LogUtils.d(TAG, url);
        //只有第一页的或者刷新的时候才显示刷新进度条
        if(pageIndex == 0) {
            mNewsView.showProgress();
        }
        mType = type;
        mNewsModel.loadNews(url, this);
    }

    /**
     * 根据类别和页面索引创建url
     * @param type
     * @param pageIndex
     * @return
     */
    private String getUrl(int type, int pageIndex) {
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                sb.append(Urls.COMMON_URL).append(Urls.NBA_ID);
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                sb.append(Urls.COMMON_URL).append(Urls.CAR_ID);
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                sb.append(Urls.COMMON_URL).append(Urls.JOKE_ID);
                break;
            default:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
        }
        sb.append("/").append(pageIndex).append(Urls.END_URL);
        return sb.toString();
    }

    @Override
    public void onSuccess(String response) {
        LogUtils.d("------NewsPresenterImpl---onSuccess----response--->", response);
        long time1 = System.currentTimeMillis();
        List<NewsBean> list = NewsJsonUtils.readJsonNewsBeans(response, getID(mType));
        long time2 = System.currentTimeMillis();
        LogUtils.d("------NewsPresenterImpl--onSuccess-----time--->", "" + (time2 - time1));
        mNewsView.hideProgress();
        mNewsView.addNews(list);
    }

    /**
     * 获取ID
     * @param type
     * @return
     */
    private String getID(int type) {
        String id;
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                id = Urls.TOP_ID;
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                id = Urls.NBA_ID;
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                id = Urls.CAR_ID;
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                id = Urls.JOKE_ID;
                break;
            default:
                id = Urls.TOP_ID;
                break;
        }
        return id;
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsView.hideProgress();
        mNewsView.showLoadFailMsg();
    }
}
