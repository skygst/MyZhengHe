package com.gst.frame.http;

import com.gst.frame.utils.JsonValidator;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by gst-pc on 2017/1/16.
 */
public class HttpGet {

    public static String getData(String url) {
//        url = "http://oauth.bbpapp.com/raz/cards/scancard_load?access_token=35bd2b213a2d521a0bf9a2f48fd3adc068de885a";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            // 同步处理
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
                if(!new JsonValidator().validate(result))
                    return null;
                return result;
            } else {
//                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getData2(String url) {
//        url = "http://oauth.bbpapp.com/raz/cards/scancard_load?access_token=35bd2b213a2d521a0bf9a2f48fd3adc068de885a";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        //            Call call = client.newCall(request);
        // 异步处理
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
//            response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                String result = response.body().string();
//                if(!new JsonValidator().validate(result))
//                    return null;
//                return result;
//            } else {
//            }
        return null;
    }
}
