package com.gst.frame.service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import com.gst.frame.utils.MLog;
import com.gst.frame.utils.StringUtils;

import java.io.File;

/**
 * Created by gst-pc on 2017/2/8.
 */

public class DownloadService2 extends Service {

    public static final String DOWNLOAD_FOLDER_NAME = "Trinea";
    public static final String DOWNLOAD_FILE_NAME = "babyplan.apk";

    private DownloadManager downloadManager;

//    private String DOWN_APK_URL = "http://219.238.2.164/apk.r1.market.hiapk.com/" +
//            "data/upload/apkres/2016/11_10/11/com.ss.android.article.news_115902.apk?" +
//            "wsiphost=local";
    private String DOWN_APK_URL = "";

    private long downloadId;
    private DownloadFinish downloadFinish;


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MLog.d("------------- onCreate ---启动服务-----------");
        Log.e("TAG", "onCreate() 启动服务");
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

        //下载之前先移除上一个 不然会导致 多次下载不成功问题
//        long id = (long) SPUtils.get(DownloadService2.this, SPUtils.KEY, (long) 0);
        long id = 0;
        if (id != 0) {
            downloadManager.remove(id);
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MLog.d("------------- onStartCommand --------------");
        if(intent != null) {
            DOWN_APK_URL = intent.getStringExtra("url");
        }
        if(!StringUtils.isEmpty(DOWN_APK_URL)) {
            initData();
            downloadFinish = new DownloadFinish();
            //动态注册广播接收器
            registerReceiver(downloadFinish, new IntentFilter(
                    DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void initData() {
        Log.e("TAG", "initData() 执行了~");

        //判断文件是否存在 不存在则创建
        File folder = new File(DOWNLOAD_FOLDER_NAME);
        if (!folder.exists() || !folder.isDirectory()) {
            folder.mkdirs();
        }

        //设置下载的URL
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse(DOWN_APK_URL));
        request.setDestinationInExternalPublicDir(DOWNLOAD_FOLDER_NAME,
                DOWNLOAD_FILE_NAME);
        //设置样式 貌似必须用 getString() 如果不用 下载完毕后会显示 下载的路径
        //request.setTitle(getString(R.string.download_notification_title));
        request.setTitle("下载标题XX");
        request.setDescription("自定义随便填");

        //判断开关状态
//        Boolean isOpen = (Boolean) SPUtils.get(this, SPUtils.WIFI_DOWNLOAD_SWITCH, false);
        Boolean isOpen = false;
        //自动下载开关是否打开 如果打开
        if (isOpen) {
            Log.e("TAG", "下载完才显示");
            /**
             * 在下载过程中通知栏会一直显示该下载的Notification，在下载完成后该Notification会继续显示，直到用户点击该
             * Notification或者消除该Notification。
             */
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);
        } else {
            MLog.d("---------initData---- 正在下载时显示 --------------");
            Log.e("TAG", "正在下载时显示");
            /**
             * 在下载过程中通知栏会一直显示该下载的Notification，在下载完成后该Notification会继续显示，
             * 直到用户点击该Notification或者消除该Notification。
             */
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }

        //是否显示下载用户接口
        request.setVisibleInDownloadsUi(false);
        //request.setMimeType("application/cn.trinea.download.file");
        request.setMimeType("application/vnd.android.package-archive");//设置此Type不然点击通知栏无法安装

        downloadId = downloadManager.enqueue(request);
//        SPUtils.put(this, SPUtils.KEY, downloadId);

    }

    /**
     * 接受下载完成的广播
     */
    class DownloadFinish extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("TAG", "DownloadFinish 广播接受完毕");
            //此ID为下载完成的ID
            long completeDownloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            MLog.d("---------onReceive---- 广播接受完毕 --downloadId---" + downloadId + "------completeDownloadId---" + completeDownloadId);
            //如果完成的ID 于 我们下载的ID 一致则表示下载完成
            if (downloadId == completeDownloadId) {
                Log.e("TAG", "DownloadFinish downloadId == completeDownloadId");
                //安装apk
                String apkFilePath = new StringBuilder(Environment
                        .getExternalStorageDirectory().getAbsolutePath())
                        .append(File.separator)
                        .append(DOWNLOAD_FOLDER_NAME)
                        .append(File.separator).append(DOWNLOAD_FILE_NAME)
                        .toString();
                install(context, apkFilePath);
            }

        }
    }

    /**
     * 安装APK
     *
     * @param context
     * @param filePath
     */
    private void install(Context context, String filePath) {
        Log.e("TAG", "install() 安装");
        Intent i = new Intent(Intent.ACTION_VIEW);
        File file = new File(filePath);
        if (file != null && file.length() > 0 && file.exists() && file.isFile()) {
            i.setDataAndType(Uri.parse("file://" + filePath),
                    "application/vnd.android.package-archive");
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }

        //停止服务
        stopSelf();
    }


    //打印看看有没有停止服务 带完善的地方 下载完成以后通知栏应该移除掉啊 还没弄不知道行不行 这里只能用户手动移除
    //而且也没有做用户点击前台服务就启动安装 以前做过貌似报错 记不清了
    @Override
    public void onDestroy() {
        Log.e("TAG", "onDestroy()");
        super.onDestroy();

        if (downloadFinish != null) {
            unregisterReceiver(downloadFinish);
        }
    }

}
