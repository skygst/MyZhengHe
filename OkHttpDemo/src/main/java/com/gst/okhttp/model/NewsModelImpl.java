package com.gst.okhttp.model;

import com.gst.okhttp.commons.Urls;
import com.gst.okhttp.http.HttpConnect;
import com.gst.okhttp.utils.LogUtils;

/**
 *  Description : 新闻业务处理类
 */
public class NewsModelImpl implements NewsModel {

    /**
     * 加载新闻列表
     * @param url
     * @param listener
     */
    @Override
    public void loadNews(String url, final OnLoadDataListener listener) {
        LogUtils.d("--------------- loadNews -----------------", "" + url);
        HttpConnect.getData(url, listener);

//        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
//            @Override
//            public void onSuccess(String response) {
//                List<NewsBean> newsBeanList = NewsJsonUtils.readJsonNewsBeans(response, getID(type));
//                listener.onSuccess(newsBeanList);
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                listener.onFailure("load news list failure.", e);
//            }
//        };
//        OkHttpUtils.get(url, loadNewsCallback);
    }

    /**
     * 加载新闻详情
     * @param docid
     * @param listener
     */
    @Override
    public void loadNewsDetail(final String docid, final OnLoadDataListener listener) {
        String url = getDetailUrl(docid);
        LogUtils.d("--------------- loadNewsDetail -----------------", "" + url);
        HttpConnect.getData(url, listener);

//        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
//            @Override
//            public void onSuccess(String response) {
//                NewsDetailBean newsDetailBean = NewsJsonUtils.readJsonNewsDetailBeans(response, docid);
//                listener.onSuccess(response);
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                listener.onFailure("load news detail info failure.", e);
//            }
//        };
//        OkHttpUtils.get(url, loadNewsCallback);
    }

//    /**
//     * 获取ID
//     * @param type
//     * @return
//     */
//    private String getID(int type) {
//        String id;
//        switch (type) {
//            case NewsFragment.NEWS_TYPE_TOP:
//                id = Urls.TOP_ID;
//                break;
//            case NewsFragment.NEWS_TYPE_NBA:
//                id = Urls.NBA_ID;
//                break;
//            case NewsFragment.NEWS_TYPE_CARS:
//                id = Urls.CAR_ID;
//                break;
//            case NewsFragment.NEWS_TYPE_JOKES:
//                id = Urls.JOKE_ID;
//                break;
//            default:
//                id = Urls.TOP_ID;
//                break;
//        }
//        return id;
//    }

    private String getDetailUrl(String docId) {
        StringBuffer sb = new StringBuffer(Urls.NEW_DETAIL);
        sb.append(docId).append(Urls.END_DETAIL_URL);
        return sb.toString();
    }

}
