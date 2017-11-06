package com.gst.frame.ui.text;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gst.frame.R;
import com.gst.frame.ui.BaseActivity;

/**
 *
 * Created by gst-pc on 2017/11/3.
 *  TextView 相关的属性及方法
 *
 */
public class TextMainActivity extends BaseActivity implements View.OnClickListener {

    private Button btnTxtPic;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_text);

        init();
        setView();
    }

    private void init() {
        mContext = TextMainActivity.this;
    }
    private void setView() {
        btnTxtPic = (Button) findViewById(R.id.btn_txt_pic);

        btnTxtPic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnTxtPic) { // 图文混排
            gotoActivity(TextAndPicActivity.class);
        }
    }

    private void gotoActivity(Class<?> classes) {
        startActivity(new Intent(mContext, classes));
    }
}
