package com.gst.frame.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gst.frame.R;
import com.gst.frame.service.DownloadService;

/**
 *  版本升级（apk 下载）
 */
public class UpdataVersionActivity extends BaseActivity implements View.OnClickListener {

    private Button btnDownloadApk;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updata_version);

        init();
        setView();
    }

    private void init() {
        mContext = UpdataVersionActivity.this;
    }
    private void setView() {
        btnDownloadApk = (Button) findViewById(R.id.btn_download);

        btnDownloadApk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnDownloadApk) { // 下载apk
            String url = "https://download.bbpapp.com/raz/apk/RazEnglishAndroid20170120061718.apk";
            startService(new Intent(mContext, DownloadService.class).putExtra("url", url));
//            startService(new Intent(mContext, DownloadService2.class).putExtra("url", url));
        }
    }
}
