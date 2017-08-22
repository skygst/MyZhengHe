package com.gst.frame.ui.pic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gst.frame.R;
import com.gst.frame.biz.AnimationBiz;

/**
 *  动画效果(属性动画)
 */
public class AnimationActivity extends Activity implements View.OnClickListener {

    private Button btn1, btn2, btn3, btn4, btn5, btn6;
    private ImageView ivPic;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation);

        init();
        setView();
    }

    private void init() {
        mContext = AnimationActivity.this;
    }

    private void setView() {
        btn1 = (Button) findViewById(R.id.btn_1);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn4 = (Button) findViewById(R.id.btn_4);
        btn5 = (Button) findViewById(R.id.btn_5);
        btn6 = (Button) findViewById(R.id.btn_6);
        ivPic = (ImageView) findViewById(R.id.iv_pic);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btn1) { // 移动
            AnimationBiz.attributeMoveXAnimation(ivPic, 2000, 1200, false);
        } else if(v == btn2) { // 旋转
            AnimationBiz.attributeRotationAnimation(ivPic, 0, 360, 1000, true, 0, 0);
        } else if(v == btn3) { // 缩放
            AnimationBiz.attributeScale(ivPic);
        } else if(v == btn4) { // 渐变
            AnimationBiz.attributeAlpha(ivPic);
        } else if(v == btn5) { // 动画集合
            AnimationBiz.attributeSet(ivPic);
        } else if(v == btn6) { // 抛物线
            AnimationBiz.paowuxian(ivPic);
        }
    }
}
