package com.gst.frame.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;

import com.gst.frame.utils.MLog;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by gst-pc on 2017/2/4.
 */

public class BaseActivity extends Activity {

    public int width, height;
    public float density;
    public Context mContext;
//    public String commonPath;
    public static String sdcard_path = "";
    public String ext_path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        }

        mContext = BaseActivity.this;

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels; // 屏幕宽度（像素）
        height = metric.heightPixels; // 屏幕高度（像素）
        density = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）


//        if (sdcard_path.equals("")) {
//            sdcard_path = getExtSdcard();
//            ext_path = Environment.getExternalStorageDirectory() + "";
//            commonPath = Environment.getExternalStorageDirectory()
//                    + "/raz_english/";
//
//            try {
//                File destDir = new File(commonPath);
//
//                if (!sdcard_path.equals(ext_path)) {
//                    if (!destDir.exists()) {
//                        commonPath = sdcard_path + "/raz_english/";
//                        File destDir2 = new File(commonPath);
//                        if (!destDir2.exists()) {
//                            destDir2.mkdirs();
//                        }
//                    } else {
//                        sdcard_path = ext_path;
//                    }
//                } else {
//                    if (!destDir.exists()) {
//                        destDir.mkdirs();
//                    }
//                }
//
//                File destDir3 = new File(commonPath + "/.nomedia");
//                if (!destDir3.exists())
//                    new File(destDir, ".nomedia").createNewFile();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        } else {
////            commonPath = Constant.sdcard_path + "/raz_english/";
//        }
    }

    public static String getExtSdcard(){
        String cmd = "cat /proc/mounts";
        Runtime run = Runtime.getRuntime();// 返回与当前 Java 应用程序相关的运行时对象
        BufferedInputStream in = null;
        BufferedReader inBr = null;
        try {
            Process p = run.exec(cmd);// 启动另一个进程来执行命令
            in = new BufferedInputStream(p.getInputStream());
            inBr = new BufferedReader(new InputStreamReader(in));

            String lineStr;
            while ((lineStr = inBr.readLine()) != null) {
                // 获得命令执行后在控制台的输出信息
                MLog.d("-----CommonUtil:getSDCardPath--->" + lineStr);
                if (lineStr.contains("sdcard")
                        && lineStr.contains(".android_secure")) {
                    String[] strArray = lineStr.split(" ");
                    if (strArray != null && strArray.length >= 5) {
                        String result = strArray[1].replace("/.android_secure",
                                "");
                        return result;
                    }
                }
                // 检查命令是否执行失败。
                if (p.waitFor() != 0 && p.exitValue() == 1) {
                    // p.exitValue()==0表示正常结束，1：非正常结束
                    MLog.d("-----CommonUtil:getSDCardPath命令执行失败!");
                }
            }
        } catch (Exception e) {
            MLog.d("---CommonUtil:getSDCardPath--->" +  e.toString());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (inBr != null) {
                    inBr.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return Environment.getExternalStorageDirectory().getPath();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setKeepScreenOn(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!getTopActivityByClassName().contains("com.ebodoo.raz")) {
            pauseAlermData();
        }
    }

    public String getTopActivityByClassName() {
        ActivityManager manager = (ActivityManager) getApplicationContext()
                .getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        if (runningTaskInfos != null && runningTaskInfos.size()>0) {
            return (runningTaskInfos.get(0).topActivity).getClassName();
        } else {
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     *
     * @param context
     * @return 平板返回 True，手机返回 False
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public void pauseAlermData() {
    }

    /**
     * 判断某个服务是否正在运行的方法
     *
     * @param mContext
     * @param serviceName是包名
     *            +服务的类名
     * @return true代表正在运行，false代表服务没有正在运行
     */
    public boolean isServiceWork(Context mContext, String serviceName) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = myAM
                .getRunningServices(Integer.MAX_VALUE);
        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(serviceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }

}
