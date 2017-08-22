package com.gst.zhenghe.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;

/**
 * Created by gst-pc on 2017/8/22.
 */

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public int screenWidth() {
        int mScreenWidth = 0;
        Display display = getWindowManager().getDefaultDisplay();
        mScreenWidth = display.getWidth();
        return mScreenWidth;
    }

}
