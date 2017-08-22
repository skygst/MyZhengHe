package com.gst.frame.http;

import android.content.Context;

import com.gst.frame.utils.MLog;

/**
 * Created by gst-pc on 2017/1/16.
 */

public class CommonInterfaces {

    public final static String share_baseurl = "http://education.ebodoo.com/api/";

    public String getUrlForGet(Context context, String objectPath, String paramPath) {
        String path = share_baseurl + objectPath + "?";
        String url = path + "access_token="
                + "b6a21a493765dc4d970fdf7984564e37980ba9ff";
//                + new AccessToken().getAccessToken(context);
        if(paramPath != null && !paramPath.equals("") && paramPath.length() > 0) {
            url = url + "&"+ paramPath;
        }
        System.out.println("getUrlForGet url :" + url);
        return url;
    }

    public static String getHttp(Context context, String objectPath, String paramPath) {
        String url = new CommonInterfaces().getUrlForGet(context, objectPath, paramPath);
        MLog.d("------url--->" + url);
        String result = new HttpGet().getData(url);
        MLog.d("------result--->" + result);
        return null;
    }

}
