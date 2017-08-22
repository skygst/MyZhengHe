package com.gst.frame.ui.img_test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gst.frame.R;
import com.gst.frame.ui.BaseActivity;

/**
 * Created by gst-pc on 2017/4/12.
 */

public class ImageViewDemoActivity extends BaseActivity implements View.OnClickListener {

    private Button btnPaowuxian;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_demo);

        init();
        setView();
    }

    private void init() {
        mContext = ImageViewDemoActivity.this;
    }

    private void setView() {
        btnPaowuxian = (Button) findViewById(R.id.btn_paowuxian);
        btnPaowuxian.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnPaowuxian) {
            gotoActivity(ImageParabolaActivity.class);
        }
    }

    private void gotoActivity(Class<?> classes) {
        startActivity(new Intent(mContext, classes));
    }

}
