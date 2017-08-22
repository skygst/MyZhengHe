package com.gst.frame.biz;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gst.frame.R;

/**
 * Created by gst-pc on 2016/11/22.
 */

public class AnimationBiz {

//    public static void starAnimation(ImageView ivAnimation, boolean isOneShot) {
//        AnimationDrawable animationDrawable = (AnimationDrawable) ivAnimation.getDrawable();
//        animationDrawable.setOneShot(isOneShot);
//        animationDrawable.start();
//    }

    // 属性动画 (rotation:旋转   alpha：透明   translationX：移动    1. scaleY：缩放)
    public static void attributeRotationAnimation(View view, float fromDegrees, float toDegrees, int duration, boolean isCenter, int pivotx, int pivoty) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", fromDegrees, toDegrees);
        animator.setDuration(duration);
        animator.setRepeatCount(10);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();
        if(!isCenter) {
            view.setPivotX(pivotx);
            view.setPivotY(pivoty);
        }
    }

    // 横向移动
    public static void attributeMoveXAnimation(View view, int duration, int value, boolean isReturn) {
        ObjectAnimator animator = null;
        if(!isReturn) {
            animator = ObjectAnimator.ofFloat(view, "translationX", Animation.RELATIVE_TO_SELF, value);
        } else {
            animator = ObjectAnimator.ofFloat(view, "translationX", Animation.RELATIVE_TO_SELF, value, 0);
        }
        animator.setDuration(duration);
        animator.setRepeatCount(-1);
//        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();
    }

    // 纵向移动
    public static void attributeMoveYAnimation(View view, int duration, int fromValue, int toValue) {
        ObjectAnimator animator = null;
        animator = ObjectAnimator.ofFloat(view, "translationY", fromValue, toValue);
//        if(value2 != 0) {
//            animator = ObjectAnimator.ofFloat(view, "translationY", Animation.RELATIVE_TO_SELF, value);
//        } else {
//            animator = ObjectAnimator.ofFloat(view, "translationY", Animation.RELATIVE_TO_SELF, value, 0);
//        }
        animator.setDuration(duration);
        animator.setRepeatCount(0);
        animator.start();
    }

    // 缩放
    public static void attributeScale(View view) {
        /*ObjectAnimator animator = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.5f, 1f);
        animator.setDuration(1000);
        animator.setRepeatCount(-1);
        animator.start();
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.5f, 1f);
        animator2.setDuration(1000);
        animator2.setRepeatCount(-1);
        animator2.start();*/

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.5f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.5f, 1f);
        final ObjectAnimator scale = ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY);

//        final ValueAnimator translation = ValueAnimator.ofFloat(0f, 300f);
        ObjectAnimator translation = ObjectAnimator.ofFloat(view, "translationX", Animation.RELATIVE_TO_SELF, 300f);
        translation.setDuration(1500);
        //执行放大动画
        scale.setDuration(1500);
        scale.start();
        translation.start();
//        view.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v)
//            {
//                //执行放大动画
//                scale.start();
//                translation.start();
//            }
//        });
    }

    // 渐变
    public static void attributeAlpha(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f);
        animator.setDuration(5000);
        animator.start();
    }

    // 复合动画(动画的集合)
    public static void attributeSet(View view) {
        //anim1实现TextView的旋转动画
//        Animator anim1 = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
//        anim1.setDuration(2000);
        //anim2和anim3TextView的平移动画
        Animator anim2 = ObjectAnimator.ofFloat(view, "translationX", 0f, 1000f);
        anim2.setDuration(2000);
//        Animator anim3 = ObjectAnimator.ofFloat(view, "translationY", 0f, 400f);
//        anim3.setDuration(3000);
        //anim4实现TextView的伸缩动画
        Animator anim4 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 2.5f);
        anim4.setDuration(2000);
        Animator anim5 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 2.5f);
        anim5.setDuration(2000);

        //第一种方式
        AnimatorSet animatorSet = new AnimatorSet();
        // animatorSet.playSequentially()该方法将anim1、anim2以及anim4按顺序串联起来放到了animatorSet中，
        // 这样首先会让动画anim1执行，anim1执行完成后，会依次执行动画anim2，执行完anim2之后会执行动画anim3
//        animatorSet.playSequentially(anim2, anim4);
        //animatorSet.playTogether()，保证了anim2和anim3同时执行，即动画anim1完成之后会同时运行anim2和anim3
//        animatorSet.playTogether(anim2, anim3);
        animatorSet.playTogether(anim2, anim4, anim5);
        animatorSet.start();

        //第二种方式
        /*AnimatorSet anim23 = new AnimatorSet();
        anim23.playTogether(anim2, anim3);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(anim1, anim23, anim4);
        animatorSet.start();*/

        //第三种方式
        /*AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(anim1).before(anim2);
        animatorSet.play(anim2).with(anim3);
        animatorSet.play(anim4).after(anim2);
        animatorSet.start();*/
    }

    // 抛物线动画
    public static void paowuxian(final View view) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>()
        {
            // fraction = t / duration
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue)
            {
//                Log.e(TAG, fraction * 3 + "");
                // x方向200px/s ，则y方向0.5 * 10 * t
                PointF point = new PointF();
                point.x = 400 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });

        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                PointF point = (PointF) animation.getAnimatedValue();
                view.setX(point.x);
                view.setY(point.y);
            }
        });
    }

    public void startAnimationView(ImageView iv) {
        AnimationDrawable animationDrawable = (AnimationDrawable) iv.getDrawable();
        animationDrawable.setOneShot(false);
        animationDrawable.start();
    }

    /**
     *  ★★★★★把商品添加到购物车的动画效果★★★★★
     * @param context
     * @param rlLayout : 背景Layout
     * @param iv : 抛物线的起点位置
     * @param ivStar : 抛物线的终点位置
     * @param tvStarNum : 显示星星数TextView
     */
    public void addCart(Context context, final RelativeLayout rlLayout, ImageView iv, ImageView ivStar, final TextView tvStarNum) {
        final float[] mCurrentPosition = new float[2];
//      一、创造出执行动画的主题---imageview
        //代码new一个imageview，图片资源是上面的imageview的图片
        // (这个图片就是执行动画的图片，从开始位置出发，经过一个抛物线（贝塞尔曲线），移动到购物车里)
        final ImageView goods = new ImageView(context);
//        goods.setImageDrawable(iv.getDrawable());
        Drawable draw1 = context.getResources().getDrawable(R.drawable.game_star);
        goods.setImageDrawable(draw1);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        rlLayout.addView(goods, params);

//        二、计算动画开始/结束点的坐标的准备工作
        //得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
        int[] parentLocation = new int[2];
        rlLayout.getLocationInWindow(parentLocation);

        //得到商品图片的坐标（用于计算动画开始的坐标）
        int startLoc[] = new int[2];
        iv.getLocationInWindow(startLoc);

        //得到购物车图片的坐标(用于计算动画结束后的坐标)
        int endLoc[] = new int[2];
        ivStar.getLocationInWindow(endLoc);


//        三、正式开始计算动画开始/结束的坐标
        //开始掉落的商品的起始点：商品起始点-父布局起始点+该商品图片的一半
        float startX = startLoc[0] - parentLocation[0] + iv.getWidth() / 2;
        float startY = startLoc[1] - parentLocation[1] + iv.getHeight() / 2;

        //商品掉落后的终点坐标：购物车起始点-父布局起始点+购物车图片的1/5
        float toX = endLoc[0] - parentLocation[0] + ivStar.getWidth() / 5;
        float toY = endLoc[1] - parentLocation[1];

//        四、计算中间动画的插值坐标（贝塞尔曲线）（其实就是用贝塞尔曲线来完成起终点的过程）
        //开始绘制贝塞尔曲线
        Path path = new Path();
        //移动到起始点（贝塞尔曲线的起点）
        path.moveTo(startX, startY);
        //使用二次萨贝尔曲线：注意第一个起始坐标越大，贝塞尔曲线的横向距离就会越大，一般按照下面的式子取即可
        path.quadTo((startX + toX) / 2, startY, toX, toY);
        //mPathMeasure用来计算贝塞尔曲线的曲线长度和贝塞尔曲线中间插值的坐标，
        // 如果是true，path会形成一个闭环
        final PathMeasure mPathMeasure = new PathMeasure(path, false);

        //★★★属性动画实现（从0到贝塞尔曲线的长度之间进行插值计算，获取中间过程的距离值）
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(1000);
        // 匀速线性插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 当插值计算进行时，获取中间的每个值，
                // 这里这个值是中间过程中的曲线长度（下面根据这个值来得出中间点的坐标值）
                float value = (Float) animation.getAnimatedValue();
                // ★★★★★获取当前点坐标封装到mCurrentPosition
                // boolean getPosTan(float distance, float[] pos, float[] tan) ：
                // 传入一个距离distance(0<=distance<=getLength())，然后会计算当前距
                // 离的坐标点和切线，pos会自动填充上坐标，这个方法很重要。
                mPathMeasure.getPosTan(value, mCurrentPosition, null);//mCurrentPosition此时就是中间距离点的坐标值
                // 移动的商品图片（动画图片）的坐标设置为该中间点的坐标
                goods.setTranslationX(mCurrentPosition[0]);
                goods.setTranslationY(mCurrentPosition[1]);
            }
        });
//      五、 开始执行动画
        valueAnimator.start();

//      六、动画结束后的处理
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            //当动画结束后：
            @Override
            public void onAnimationEnd(Animator animation) {
                // 购物车的数量加1
//                i++;
//                count.setText(String.valueOf(i));
                // 把移动的图片imageview从父布局里移除
                String value = tvStarNum.getText().toString();
                if(!StringBiz.isEmpty(value)) {
                    int num = Integer.valueOf(value).intValue();
                    num += 10;
                    tvStarNum.setText("" + num);
                }
                rlLayout.removeView(goods);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

}
