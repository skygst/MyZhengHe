package com.gst.zhenghe.mvp;

import android.os.Bundle;

import com.gst.zhenghe.R;
import com.gst.zhenghe.ui.BaseActivity;

/**
 * MVP模式的使用
 */
public class MvpDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_mvp);
    }
}
