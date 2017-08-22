package com.gst.frame.base;

import android.content.Context;

import com.gst.frame.http.CommonConstant;
import com.gst.frame.http.CommonInterfaces;
import com.gst.frame.http.HttpPost;
import com.gst.frame.utils.Constant;

import java.util.HashMap;

/**
 * Created by gst-pc on 2017/1/16.
 */

public class GameLoader {

    public String getGameLoader(Context context) {
        return CommonInterfaces.getHttp(context, CommonConstant.gameLoad, "");
    }

    // 获取验证码
    public static void securityCodeInfo(Context context, String phone) {
        String url = CommonConstant.share_baseurl + CommonConstant.oauthCode;;
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("phone", phone));
//        params.add(new BasicNameValuePair("client_id", Constant.client_id));
        HashMap<String , String> map = new HashMap<String , String>();
        map.put("phone", phone);
        map.put("client_id", Constant.client_id);
        String result = new HttpPost().postData(url, map);
        System.out.println("------securityCodeInfo----result-------:" + result);
//        return parseSecurityCode(result);
    }

}
