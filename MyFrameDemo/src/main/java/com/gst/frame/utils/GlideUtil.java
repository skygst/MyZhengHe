package com.gst.frame.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

/**
 *  load()是加载目标资源:
 *  Uri uri;
 *  String uriString;
 *  File file;
 *  Integer resourceId;
 *  byte[] model;
 *  String model;
 *
 *
 * Glide的为此封装
 */
public final class GlideUtil {

    /**
     * 加载网络图片到imageview
     *
     * @param imgUrl
     * @param imageView
     */
    public static void loadImg(String imgUrl, ImageView imageView) {
        MLog.d("------------ loadImg -------------------");
        Context context = imageView.getContext();
        Glide.with(context)
                .load(imgUrl)
//                .dontAnimate() //取消淡入淡出效果
                .crossFade()//增加图片显示时候的淡入淡出动画
                .fitCenter() //裁剪技术,缩放图像让图像都测量出来等于或小于 ImageView 的边界范围,该图像将会完全显示，但可能不会填满整个 ImageView。
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
    //                DiskCacheStrategy.NONE：不缓存
    //                DiskCacheStrategy.SOURCE：缓存原始图片
    //                DiskCacheStrategy.RESULT：缓存压缩过的结果图片
    //                DiskCacheStrategy.ALL：两个都缓存

//                .override(720, 500) // 设置图片大小
//                .thumbnail(0.1f) // 加载缩略图
//                .centerCrop() // 长的一边撑满
//                .fitCenter() // 短的一边撑满
//                .bitmapTransform(new GlideCircleTransform(context))
                .transform(new DefineBigPicTransform(context))
//                .transform(new CircleTransform(context)) // 圆形
//                .transform(new GlideRoundTransform(context)) // 圆角
//                .crossFade() // 淡入淡出效果
                .into(imageView);
    }

    /**
     * 从资源文件中加载图片
     *
     * @param imgRes
     * @param imageView
     */
    public static void loadImg(int imgRes, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(imgRes)
//                .crossFade() // 淡入淡出效果
                .dontAnimate() // 取消淡入淡出效果
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }

    public static void loadWithoutPlaceholder(int imgRes, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(imgRes)
                .dontAnimate()
                .into(imageView);
    }


    /**
     * 加载圆形图片到imageview
     *
     * @param imgUrl
     * @param imageView
     */
    public static void loadRoundImg(String imgUrl, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(imgUrl)
//                .placeholder(R.drawable.ic_avater_placeholder)
                .dontAnimate()
                .centerCrop()
                .transform(new GlideCircleTransform(context))
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }

    public static void loadRoundImg(File file, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(file)
                // .placeholder(R.drawable.ic_avater_placeholder)
                .dontAnimate()
                .centerCrop()
                .transform(new GlideCircleTransform(context))
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }

    public static void loadRoundImg(Uri uri, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(uri)
                //.placeholder(R.drawable.ic_avater_placeholder)
                .dontAnimate()
                .centerCrop()
                .transform(new GlideCircleTransform(context))
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }

    public static void loadImgUrlWithoutCrop(String imgUrl, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(imgUrl)
                .dontAnimate()
                //.placeholder(R.drawable.bg_placeholder)
                .into(imageView);
    }

    static class DefineBigPicTransform extends BitmapTransformation {

        int mScreenWidth = 1080;

        public DefineBigPicTransform(Context context) {
            super(context);
            MLog.d("---------- DefineBigPicTransform ------------------");
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            MLog.d("---------- transform ------------------");
            // 根据屏幕的宽高来显示图片
            int bmpWidth = toTransform.getWidth();
            int bmpHeight = toTransform.getHeight();
            int resultHeight = (Integer) (bmpWidth * 210 / 320);
            int h1 = (Integer) (bmpHeight / 3);
            int h2 = (Integer) (resultHeight / 2);
            MLog.d("-----h1----->" + h1);
            MLog.d("-----h2----->" + h2);
            if (h1 >= h2) {
                toTransform = Bitmap.createBitmap(toTransform, 0, (h1 - h2),
                        bmpWidth, resultHeight);
                int hh = (Integer) (mScreenWidth * resultHeight / bmpWidth);
                MLog.d("-----hh----->" + hh);
                toTransform = Bitmap.createScaledBitmap(toTransform,
                        mScreenWidth, hh, false);
//                ImageView iv = (ImageView) view;
//                iv.setImageBitmap(toTransform);
            } else {
                int screenHeight = (Integer) (mScreenWidth * bmpHeight / bmpWidth);
                MLog.d("-----screenHeight----->" + screenHeight);
                toTransform = readBitmap(toTransform);
                toTransform = Bitmap.createScaledBitmap(toTransform,
                        mScreenWidth, screenHeight, false);
//                ImageView iv = (ImageView) view;
//                iv.setImageBitmap(toTransform);
            }
            return toTransform;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }

    public static Bitmap readBitmap(Bitmap bmp) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;// 表示16位位图 565代表对应三原色占的位数
        opt.inInputShareable = true;
        opt.inPurgeable = true;// 设置图片可以被回收
        InputStream is = Bitmap2InputStream(bmp, 100);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    // 将Bitmap转换成InputStream
    public static InputStream Bitmap2InputStream(Bitmap bm, int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, quality, baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        return is;
    }

    static class GlideCircleTransform extends BitmapTransformation {

        public GlideCircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            MLog.d("---------GlideCircleTransform---transform------------------");
            return circleCrop(pool, toTransform);
        }

        private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            MLog.d("---------GlideCircleTransform---transform--------circleCrop----------------------");
            if (source == null) return null;
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }

    // 显示本地视频
    public void playLocationVideo(Context context, ImageView imageView) {
        String filePath = "/storage/emulated/0/Pictures/example_video.mp4";
        Glide.with(context)
                .load( Uri.fromFile(new File(filePath)))
                .into(imageView);
    }

    // 图片加载缓存（内存缓存） .skipMemoryCache(true).
    public void loadCacheImage(String url, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .skipMemoryCache(true)// （内存缓存）
//                .diskCacheStrategy(DiskCacheStrategy.NONE); // 图片加载缓存（磁盘缓存）
                .into(imageView);
    }

    // 设置缩略图 .thumbnail() 传入float参数作为其大小的倍数。
    public void loadThumbnailImage(String url, ImageView imageView) {
        Context context = imageView.getContext();
        // 加载网络图片
        Glide.with(context)
                .load(url)
                .thumbnail(0.1f) //加载缩略图  为原图的十分之一
                .into(imageView);
    }

}
