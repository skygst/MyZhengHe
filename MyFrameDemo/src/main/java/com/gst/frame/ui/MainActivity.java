package com.gst.frame.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.gst.frame.R;
import com.gst.frame.ui.http.HttpActivity;
import com.gst.frame.ui.img_test.ImageViewDemoActivity;
import com.gst.frame.ui.pic.AnimationActivity;
import com.gst.frame.ui.pic.ImageViewShowActivity;
import com.gst.frame.ui.theme_1.Theme1Activity;
import com.gst.frame.ui.theme_2.Theme2Activity;

public class MainActivity extends ProgressAnimationBaseActivity implements View.OnClickListener {

    private Button btnPic, btnOkHttp, btnAnimation, btnDialog, btnDefineView, btnUpdataVersion,
                btnTheme1, btnTheme2, btnImg;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setView();
    }

    private void init() {
        mContext = MainActivity.this;
    }

    private void setView() {
        setTopView("正在加载中，请稍后...");
        btnPic = (Button) findViewById(R.id.btn_pic);
        btnOkHttp = (Button) findViewById(R.id.btn_ok_http);
        btnAnimation = (Button) findViewById(R.id.btn_animation);
        btnDialog = (Button) findViewById(R.id.btn_dialog);
        btnDefineView = (Button) findViewById(R.id.btn_define_view);
        btnUpdataVersion = (Button) findViewById(R.id.btn_updata_version);
        btnTheme1 = (Button) findViewById(R.id.btn_theme_1);
        btnTheme2 = (Button) findViewById(R.id.btn_theme_2);
        btnImg = (Button) findViewById(R.id.btn_img);

        btnPic.setOnClickListener(this);
        btnOkHttp.setOnClickListener(this);
        btnAnimation.setOnClickListener(this);
        btnDialog.setOnClickListener(this);
        btnDefineView.setOnClickListener(this);
        btnUpdataVersion.setOnClickListener(this);
        btnTheme1.setOnClickListener(this);
        btnTheme2.setOnClickListener(this);
        btnImg.setOnClickListener(this);
        startLoadingAnimation();
        threadAnimation();
    }

    // 开始加载动画
    private void startLoadingAnimation() {
        rlDefineProgress.setVisibility(View.VISIBLE);
        startAnimationView();
    }

    // 结束加载动画
    private void finishLoadingAnimation() {
        rlDefineProgress.clearAnimation();
        rlDefineProgress.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if(v == btnPic) { // Glide 图片显示
            gotoActivity(ImageViewShowActivity.class);
        } else if(v == btnOkHttp) { // OKHttp的使用
            gotoActivity(HttpActivity.class);
        } else if(v == btnAnimation) { // 动画效果
            gotoActivity(AnimationActivity.class);
        } else if(v == btnDialog) { // dialog
//            gotoActivity(HttpActivity.class);
        } else if(v == btnDefineView) { // 自定义View
            gotoActivity(DefineViewActivity.class);
        } else if(v == btnUpdataVersion) { // 版本升级
            gotoActivity(UpdataVersionActivity.class);
        } else if(v == btnTheme1) { // 主题1
            gotoActivity(Theme1Activity.class);
        } else if(v == btnTheme2) { // 主题2
            gotoActivity(Theme2Activity.class);
//            gotoActivity(UserViewPagerGenerateTabActivity.class);
//            gotoActivity(UserViewPagerAndFragmentGenerateTabActivity.class);
//            gotoActivity(StartActivity.class);
        } else if(v == btnImg) { // ImageVIew一些demo
            gotoActivity(ImageViewDemoActivity.class);
        }
    }

    private void gotoActivity(Class<?> classes) {
        startActivity(new Intent(mContext, classes));
    }

    private void threadAnimation() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    handler.sendMessage(handler.obtainMessage(0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    finishLoadingAnimation();
                    break;
            }
        }
    };


}
