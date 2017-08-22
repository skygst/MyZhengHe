package com.gst.frame.ui.http;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gst.frame.R;
import com.gst.frame.base.GameLoader;
import com.gst.frame.bean.Movie;
import com.gst.frame.bean.MovieService;
import com.gst.frame.bean.PhoneService;
import com.gst.frame.bean.RetrofitWrapper;
import com.gst.frame.bean.Subject;
import com.gst.frame.utils.MLog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gst-pc on 2017/1/16.
 */
public class HttpActivity extends Activity implements View.OnClickListener {

    private Button btnGet, btnPost, btnRetrofit;
    private Context mContext;
    private long time1, time2, time3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_main);

        init();
        setView();
    }

    private void init() {
        mContext = HttpActivity.this;
    }

    private void setView() {
        btnGet = (Button) findViewById(R.id.btn_get);
        btnPost = (Button) findViewById(R.id.btn_post);
        btnRetrofit = (Button) findViewById(R.id.btn_retrofit);

        btnGet.setOnClickListener(this);
        btnPost.setOnClickListener(this);
        btnRetrofit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnGet) {
            time1 = System.currentTimeMillis();
            getData();
            query2();
        } else if(v == btnPost) {
            postData();
        } else if(v == btnRetrofit) { // Retrofit与服务区交互
//            query();
        }
    }

    private void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new GameLoader().getGameLoader(mContext);
                time2 = System.currentTimeMillis();
                MLog.d("---(time2-time1)--->" + (time2 - time1));
            }
        }).start();
    }
    private void postData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new GameLoader().securityCodeInfo(mContext, "13917239556");
            }
        }).start();
    }

    private void query2() {
        MovieService tvMovieService = RetrofitWrapper.getInstance().create(MovieService.class);
        Call<Movie<Subject>> call = tvMovieService.getTopMovie(0, 10);
//        Call<TvInfo> call = tvService.getTvInfoList(
//                "b6a21a493765dc4d970fdf7984564e37980ba9ff"
//        );
//        Call<TvInfo> call = tvService.getTvInfoList(
//                "1", "8a7204eb63ba0bab009b338025f42df2"
//        );
        call.enqueue(
                new Callback<Movie<Subject>>() {

                    @Override
                    public void onResponse(Call<Movie<Subject>> call, Response<Movie<Subject>> response) {
                        MLog.d("---query2--->获取成功 message :" + response.message());
                        MLog.d("---query2--->获取成功toString :" + response.toString());
                        MLog.d("---query2--->isSuccessful :" + response.isSuccessful());
                        MLog.d("---query2--->获取成功 body :" + response.body());
                        //这里显示了列表中的第一条的内容，所以get(0)
//                        Toast.makeText(
//                                HttpActivity.this,
//                                tv.getResult().get(0).getChannelName(),
//                                Toast.LENGTH_LONG
//                        ).show();
//                        Log.i(TAG,response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Movie<Subject>> call, Throwable t) {
                        MLog.d("---query2--->获取失败-->" + t.getMessage());
                        Toast.makeText(HttpActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    private void query() {
        PhoneService phoneService = RetrofitWrapper.getInstance().create(PhoneService.class); // 创建一个接口
        String token = "b6a21a493765dc4d970fdf7984564e37980ba9ff";
//        Call<String> phoneResultCall = phoneService.getResult3(token);
        Call<String> phoneResultCall = phoneService.getResult4();

//        TvService tvService = RetrofitWrapper.getInstance().create(TvService.class);
//        Call<String> phoneResultCall = tvService.getTvInfoList();
        phoneResultCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                MLog.d("---(time3-time1)--->获取成功");
                time3 = System.currentTimeMillis();
                MLog.d("---(time3-time1)--->" + (time3 - time1));
                MLog.d("---response.toString--->" + response.toString());
                MLog.d("---response.isSuccessful--->" + response.isSuccessful());
                MLog.d("---response.body.toString--->" + response.body());
                MLog.d("---response.code--->" + response.code());
//                MLog.d("---response.body.toString--->" + response.body().toString());

                MLog.d("---call.toString--->" + call.toString());

                if (response.isSuccessful()) {
                    Log.e("yuzhentao", "获取成功");
//                    PhoneResult phoneResult = response.body();
//                    if (phoneResult != null)
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MLog.d("---(time3-time1)--->获取失败--->" + t.getMessage());
                MLog.d("---(time3-time1)--->获取失败--hashCode->" + t.hashCode());
                MLog.d("---(time3-time1)--->获取失败--toString->" + t.toString());
                MLog.d("---(time3-time1)--->获取失败--getStackTrace->" + t.getStackTrace());

                Log.e("yuzhentao", "获取失败");
            }
        });
    }

}
