package com.gst.frame.bean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by gst-pc on 2017/2/7.
 */

public interface TvService {

//    @GET("game/load/")
//    @GET("http://education.ebodoo.com/api/game/load?access_token=b6a21a493765dc4d970fdf7984564e37980ba9ff")
    @GET("https://api.douban.com/v2/movie/top250?start=0&count=10")
    Call<TvInfo> getTvInfoList();
}
