package com.gst.frame.ui.pic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gst.frame.R;
import com.gst.frame.utils.GlideUtil;

/**
 *  Glide 显示图片
 */
public class ImageViewShowActivity extends Activity {

    private ImageView ivNetWork, ivLocation, ivGif;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic);

        init();
        setView();
    }

    private void init() {
        mContext = ImageViewShowActivity.this;
    }

    private void setView() {
        ivNetWork = (ImageView) findViewById(R.id.iv_network);
        ivLocation = (ImageView) findViewById(R.id.iv_location);
        ivGif = (ImageView) findViewById(R.id.iv_gif);

        showView();
    }

    private void showView() {
        String picUrl = "https://img.bbpapp.com/raz/book_cover/level_c_1.png";
        GlideUtil.loadImg(picUrl, ivNetWork);
        GlideUtil.loadImg(R.drawable.basic_level_15, ivLocation);

        Glide.with(mContext).load(R.drawable.he_runs_gif).asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivGif);
    }
}
