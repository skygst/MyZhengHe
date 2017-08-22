package com.gst.frame.bean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gst-pc on 2017/2/7.
 */

public interface MovieService {

//    @GET("top250")
//    Observable<Movie<Movie.Subjects> getTopMovie(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Call<Movie<Subject>> getTopMovie(@Query("start") int start, @Query("count") int count);

}
