package com.gst.frame.bean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by gst-pc on 2017/2/7.
 */

public interface PhoneService {

    @GET("/apistore/mobilenumber/mobilenumber")
    Call<PhoneResult> getResult(@Header("apikey") String apikey, @Query("phone") String phone);

    @GET("/apistore/mobilenumber/mobilenumber")
    Call<PhoneResult> getResult2(@Header("apikey") String apikey, @Query("phone") String phone);

//    @GET("http://education.ebodoo.com/api/game/load?access_token=b6a21a493765dc4d970fdf7984564e37980ba9ff")
//    Call<String> getResult3();
    @GET("game/load")
    Call<String> getResult3(@Query("access_token") String access_token);

    @GET("http://oauth.bbpapp.com/raz/general/gift_package?access_token=e06e0a01e56906d9ba57dc043bbbabe20dc47588")
    Call<String> getResult4();

    @GET("https://api.douban.com/v2/movie/top250?start=0&count=10")
    Call<String> getResult5();

}
