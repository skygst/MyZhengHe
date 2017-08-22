package com.gst.frame.utils;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.gst.frame.R;

/**
 * Created by gst-pc on 2017/2/13.
 */

public class AnimationUtil {

    /**
     *  上下抖动
     * @param context
     * @param view
     */
    public void setShakeDownAnimation(Context context, final View view) {
        final Animation shakeAnim = AnimationUtils.loadAnimation(context,
                R.anim.shake_y);
        view.startAnimation(shakeAnim);
        shakeAnim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                view.startAnimation(shakeAnim);
            }
        });
    }

    // 属性动画 (rotation:旋转   alpha：透明   translationX：移动    1. scaleY：缩放)
    public static void attributeRotationAnimation(View view, float fromDegrees, float toDegrees, int duration, int pivotx, int pivoty) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", fromDegrees, toDegrees);
        animator.setDuration(duration);
        animator.setRepeatCount(0);
        animator.start();
        view.setPivotX(pivotx);
        view.setPivotY(pivoty);
    }

    // 属性动画：渐变
    public static void startPropertyAnimAlpha(View view, int duration) {
        // 将直接把TextView这个view对象的透明度渐变。
        // 注意第二个参数："alpha"，指明了是透明度渐变属性动画
        // 透明度变化从0.1—>1 TextView对象经历1次透明度渐变
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", 0.1f, 1f);
        anim.setDuration(duration);// 动画持续时间
        anim.start();
    }

    // 横向移动
    public static void attributeMoveXAnimation(View view, int duration, int value, int value2) {
        ObjectAnimator animator = null;
        if(value2 != 0) {
            animator = ObjectAnimator.ofFloat(view, "translationX", Animation.RELATIVE_TO_SELF, value);
        } else {
            animator = ObjectAnimator.ofFloat(view, "translationX", Animation.RELATIVE_TO_SELF, value, 0);
        }
        animator.setDuration(duration);
        animator.setRepeatCount(0);
        animator.start();
    }

    // 纵向移动
    public static void attributeMoveYAnimation(View view, int duration, int toValue, boolean isReturn, int repeatCount) {
        ObjectAnimator animator = null;
        if(isReturn) {
            animator = ObjectAnimator.ofFloat(view, "translationY", Animation.RELATIVE_TO_SELF, toValue, 0);
        } else {
            animator = ObjectAnimator.ofFloat(view, "translationY", Animation.RELATIVE_TO_SELF, toValue);
        }
        animator.setDuration(duration);
        animator.setRepeatCount(repeatCount);
        animator.start();
    }

}
