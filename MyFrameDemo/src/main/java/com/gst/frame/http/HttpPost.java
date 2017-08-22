package com.gst.frame.http;

import com.gst.frame.utils.JsonValidator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static java.lang.String.valueOf;


/**
 * Created by gst-pc on 2017/1/16.
 */

public class HttpPost {

    public static String postData(String url, HashMap<String , String> map) {
        OkHttpClient client = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
//        if(file != null){
//            // MediaType.parse() 里面是上传的文件类型。
//            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
//            String filename = file.getName();
//            // 参数分别为， 请求key ，文件名称 ， RequestBody
//            requestBody.addFormDataPart("headImage", file.getName(), body);
//        }
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : map.entrySet()) {
                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
        }

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody.build())
                .build();
        Response response = null;
        try {
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

}
