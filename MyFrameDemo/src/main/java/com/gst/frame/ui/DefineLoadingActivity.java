package com.gst.frame.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gst.frame.R;
import com.gst.frame.biz.AnimationBiz;
import com.gst.frame.biz.VideoBiz;
import com.gst.frame.custom.ClickImageView;
import com.gst.frame.data.FixedPosition;
import com.gst.frame.utils.MLog;

import java.io.File;

/**
 * Created by gst-pc on 2017/2/23.
 */
public class DefineLoadingActivity extends BaseActivity {

    private RelativeLayout rlLayout;
    private Context mConatext;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private VideoBiz biz;
    private ImageView ivPic, ivTxt, ivBulb;
    private ProgressBar progress;
//    private SeekBar seekBar;
    private Button btnClick;
    private ClickImageView ivCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.define_loading);

        init();
        setView();
    }

    private void init() {
        mContext = DefineLoadingActivity.this;
        biz = new VideoBiz();
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
    }

    private void setView() {
        rlLayout = (RelativeLayout) findViewById(R.id.id_ll_tab_bg);
        btnClick = (Button) findViewById(R.id.btn_click);
        ivPic = (ImageView) findViewById(R.id.iv_pic);
        ivTxt = (ImageView) findViewById(R.id.iv_txt);
        ivBulb = (ImageView) findViewById(R.id.iv_bulb);
        progress = (ProgressBar) findViewById(R.id.my_progress);
//        seekBar = (SeekBar) findViewById(R.id.seekbar);
//        rlLayout.setBackgroundColor(Color.parseColor("#6387C7"));
        rlLayout.setBackgroundColor(Color.WHITE);
        ivCancel = (ClickImageView) findViewById(R.id.iv_cancel);
        ivCancel.setVisibility(View.GONE);

//        seekBar.setProgress(100);

        setViewPosition(ivPic, 0);
        setViewPosition(ivTxt, 1);
        setViewPosition(progress, 2);
        setViewPosition(ivBulb, 3);

        new AnimationBiz().startAnimationView(ivPic);

//        progress.setMaxCount(100);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i <= 100; i++) {
//                    Message msg = new Message();
//                    msg.arg1 = i;
//                    msg.what = 0x01;
//                    handler.sendMessage(msg);
//                    try {
//                        //每隔0.1秒进度前进1
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//        verifyStoragePermissions(DefineLoadingActivity.this);
        boolean status = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        MLog.d("-----status----->" + status);
        boolean canWrite = new File(Environment.getExternalStorageDirectory().getAbsolutePath()).canWrite();
        MLog.d("-----canWrite----->" + canWrite);
//        MLog.d("-----canWrite---2-->" + new File(commonPath).canWrite());
        Toast.makeText(mContext, "是否可以写：" + canWrite, Toast.LENGTH_LONG).show();
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commonPath = Environment.getExternalStorageDirectory()
                        + "/raz_english/";
                String url = "http://download.bbpapp.com/raz_maps/" + "letters.zip";
                MLog.d("-----commonPath----->" + commonPath);
                startActivity(new Intent(mContext, DialogDownActivity.class)
                .putExtra("url", url).putExtra("commonPath", commonPath).putExtra("createFileName", "letters"));
            }
        });
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0x01) {
                MLog.d("-----msg.arg1----><>" + msg.arg1);
                progress.setProgress(msg.arg1);
            }
        }
    };

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.loading_position,
                scaleQPW, scaleQPH, 0, 0, 1.0f);
    }

}
