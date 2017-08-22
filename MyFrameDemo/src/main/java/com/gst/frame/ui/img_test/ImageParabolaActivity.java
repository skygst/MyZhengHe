package com.gst.frame.ui.img_test;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gst.frame.R;
import com.gst.frame.base.ParabolaAlgorithm;
import com.gst.frame.biz.VideoBiz;
import com.gst.frame.data.FixedPosition;
import com.gst.frame.ui.BaseActivity;

/**
 *  图片抛物线运动
 *
 *   注意：accelerated(加速)，decelerated(减速),repeated(重复),bounced(弹跳)等。
 */
public class ImageParabolaActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivPic, ivFire1, ivFire2;
    private Context mContext;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parabola);

        init();
        setView();
    }

    private void init() {
        mContext = ImageParabolaActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
    }

    private void setView() {
        ivPic = (ImageView) findViewById(R.id.iv_pic);
        ivFire1 = (ImageView) findViewById(R.id.iv_fire_1);
        ivFire2 = (ImageView) findViewById(R.id.iv_fire_2);

        ivPic.setOnClickListener(this);
        setViewPosition(ivFire1, 0);
        setViewPosition(ivFire2, 0);
        setViewPosition(ivPic, 1 );
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.paowuxian_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    @Override
    public void onClick(View v) {
        if(v == ivPic) {
            String[] string1 = new String[] {"0", "0"};
            String[] string2 = new String[] {"300", "600"};
            String[] string3 = new String[] {"600", "0"};

            final float[][] points = { { Float.valueOf(string1[0]), Float.valueOf(string1[1]) },
                    { Float.valueOf(string2[0]), Float.valueOf(string2[1]) },
                    { Float.valueOf(string3[0]), Float.valueOf(string3[1]) } };
            float[] value = ParabolaAlgorithm.calculate(points);
            a = value[0];
            b = value[1];
            c = value[2];
            count = (int)(points[2][0] - points[0][0]);
            System.out.println("-----a---1-->" + a);
            System.out.println("-----a----2->" + a);
            System.out.println("-----count----->" + count);
            ivPic.setImageResource(R.drawable.lailai_2);
            startAnimation(ivPic);
        }
    }

    //分300步进行移动动画
//    int count = 120;
    int count = 300;
    /**
     * 要start 动画的那张图片的ImageView
     * @param imageView
     */
    private void startAnimation(final ImageView imageView) {

        Keyframe[] keyframes = new Keyframe[count];
        final float keyStep = 1f / (float) count;
        float key = keyStep;
        for (int i = 0; i < count; ++i) {
            keyframes[i] = Keyframe.ofFloat(key, i + 1);
            key += keyStep;
        }

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofKeyframe("translationX", keyframes);
        key = keyStep;
        System.out.println("-----key----->" + key);
        for (int i = 0; i < count; ++i) {
            keyframes[i] = Keyframe.ofFloat(key, -getY(i + 1));
            key += keyStep;
        }

        PropertyValuesHolder pvhY = PropertyValuesHolder.ofKeyframe("translationY", keyframes);
        //ObjectAnimator anim = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        PropertyValuesHolder rotaiton = PropertyValuesHolder.ofFloat("rotation", 0f, 360f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(imageView, pvhY, pvhX, rotaiton).setDuration(500);
//        yxBouncer.setInterpolator(new BounceInterpolator());
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ivPic.setImageResource(R.drawable.lailai_1);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        objectAnimator.start();
    }

//    final float a = -1f / 75f;

    float a = 0f, b = 0f, c = 0f;
    /**
     * 这里是根据三个坐标点{（0,0），（300,0），（150,300）}计算出来的抛物线方程
     *
     * @param x
     * @return
     */
    private float getY(float x) {
        float yValue = a * x * x + 4 * x;
        System.out.println("-----yValue----->" + yValue);
        yValue = yValue * 0.2f;
        return yValue;
    }
}
