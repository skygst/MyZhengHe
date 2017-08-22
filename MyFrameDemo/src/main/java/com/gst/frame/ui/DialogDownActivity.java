package com.gst.frame.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gst.frame.R;
import com.gst.frame.biz.AnimationBiz;
import com.gst.frame.biz.VideoBiz;
import com.gst.frame.custom.ClickImageView;
import com.gst.frame.data.FixedPosition;
import com.gst.frame.tools.Tools;
import com.gst.frame.utils.FileUtils;
import com.gst.frame.utils.MLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * Created by gst-pc on 2017/2/27.
 */
public class DialogDownActivity extends BaseActivity implements View.OnClickListener {

    private Context mConatext;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private VideoBiz biz;
    private RelativeLayout rlBg;
    private ImageView ivPic, ivTxt, ivBulb;
    private TextView tvDes;
    private ProgressBar progressBar;
    private ClickImageView ivCancel;
    private String url, commonPath;
    private URL mUrl;
    private File mFile;
    private double contentLength;
    private int mProgress = 0;
    private long len;
    private boolean isFinish = false;
    private String createFileName = "", createFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.define_loading);

        init();
        setView();
    }

    private void init() {
        mContext = DialogDownActivity.this;
        biz = new VideoBiz();
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);

        try {
            url = getIntent().getExtras().getString("url");
            commonPath = getIntent().getExtras().getString("commonPath");
            createFileName = getIntent().getExtras().getString("createFileName");
            mUrl = new URL(url);
            String fileName = new File(mUrl.getFile()).getName();
            mFile = new File(commonPath, fileName);
            MLog.d("----->commonPath=" + commonPath + ", name=" + fileName + ",mUrl.getFile()="
                    + mUrl.getFile());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void setView() {
        rlBg = (RelativeLayout) findViewById(R.id.rl_bg);
        ivPic = (ImageView) findViewById(R.id.iv_pic);
        ivTxt = (ImageView) findViewById(R.id.iv_txt);
        ivBulb = (ImageView) findViewById(R.id.iv_bulb);
        progressBar = (ProgressBar) findViewById(R.id.my_progress);
        ivCancel = (ClickImageView) findViewById(R.id.iv_cancel);
        tvDes = (TextView) findViewById(R.id.tv_des);

        tvDes.setText("20个精彩国家主题游戏，64个游戏关卡，80个单词，学习26个拼读组合规律");
        setViewPosition(ivPic, 0);
        setViewPosition(ivTxt, 1);
        setViewPosition(progressBar, 2);
        setViewPosition(ivBulb, 3);
        setViewPosition(rlBg, 4);
        setViewPosition(ivCancel, 5);
        progressBar.setProgress(0);

        rlBg.setBackgroundResource(R.drawable.down_load_bg);

        new AnimationBiz().startAnimationView(ivPic);
        new DownLoadTask().execute();
        ivCancel.setOnClickListener(this);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.loading_dialog_position,
                scaleQPW, scaleQPH, 0, 0, 1.0f);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isFinish = false;
    }

    @Override
    public void onClick(View v) {
        if(v == ivCancel) {
            finish();
        }
    }

    public class DownLoadTask extends AsyncTask<Void, Integer, Long> {
        private ProgressReportingOutputStream mOutputStream;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Long doInBackground(Void... params) {
            MLog.d("-------------doInBackground-----------------------");
            return download();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            try{
                double i = values[0];
                double j = i / contentLength;
                int progress = (int) (i / contentLength * 100);
                MLog.d("-------------onProgressUpdate-----------progress------------" + progress);
                if (progress > 0) {
                    progressBar.setProgress(progress);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Long result) {
            MLog.d("-------------onPostExecute-----------去解压------------");
//            String path = Constant.sdcard_path +"/raz_english/" + gameName + "/";
            createFilePath = commonPath + createFileName + "/";
            File file = new File(createFilePath);
            MLog.d("----解压后的文件路径----createFilePath---->" + createFilePath);
            // 判断文件夹是否存在，如果不存在创建
            if (!file.exists()) {
                file.mkdirs();
            }
            // TODO 去解压
            if(result == 0 ||len  == mProgress){
                //下载完成：统计
                //解压缩
                if((commonPath + mFile.getName()).contains(".zip")){
                    ZipTask task = new ZipTask();
                    task.execute();
                }
            } else {
                //下载错误：统计
//                Statistics.downloadRes(mContext, "下载错误", downloadName);
            }
        }

        private final class ProgressReportingOutputStream extends FileOutputStream {
            public ProgressReportingOutputStream(File file)
                    throws FileNotFoundException {
                super(file);
            }

            @Override
            public void write(byte[] buffer, int byteOffset, int byteCount)
                    throws IOException {
                super.write(buffer, byteOffset, byteCount);
                mProgress += byteCount;
                publishProgress(mProgress);
            }

        }

        private long download() {
            URLConnection connection = null;
            int bytesCopied = 0;
            try {
                MLog.d("----------- download() ------------------");
                connection = mUrl.openConnection();
                contentLength = connection.getContentLength();
                MLog.d("----------- download() --------mFile----------" +mFile);
                MLog.d("----------- download() --------contentLength----------" +contentLength);
                MLog.d("----------- download() --------mFile.length()----------" +mFile.length());
                if (mFile.exists() && contentLength == mFile.length()) {
                    MLog.d("-----file " + mFile.getName() + " already exits!!");
                    // TODO 已经存在，则删除
                    mFile.delete();
//                    return 0l;
                }
                File sd = Environment.getExternalStorageDirectory();
                boolean can_write = sd.canWrite();

                MLog.d("------------ ProgressReportingOutputStream -------can_write---------" + can_write);
                mOutputStream = new ProgressReportingOutputStream(mFile);
                // 初始化进度条
                publishProgress(0, (int) contentLength);

                // 用缓冲buffer拷贝数据，并返回拷贝数据的长度
                bytesCopied = FileUtils.copy(connection.getInputStream(), mOutputStream, DownLoadTask.this);
                // 判断拷贝数据的长度是否=数据包的原长度，相等就拷贝成功
                if (bytesCopied != contentLength && contentLength != -1) {
//                Log.e(TAG, "Download incomplete bytesCopied=" + bytesCopied
//                        + ", length" + contentLength);
                }
                mOutputStream.close();
                len = bytesCopied;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bytesCopied;
        }
    }

    // 去解压
    public class ZipTask extends AsyncTask<Void, Integer, Long> {
//        private DownLoadTask.ProgressReportingOutputStream mOutputStream;
        private File mOutput = null;
        private long extrasize;
        private double size;
        private long orgsize;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ivTxt.setImageResource(R.drawable.loading_txt_decompression);
            mOutput = new File(createFilePath);
            progressBar.setProgress(0);
            mProgress = 0;
        }

        @Override
        protected Long doInBackground(Void... params) {
            MLog.d("---------解压----doInBackground-----------------------");
            return unzip();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            try{
                double i = values[0];
                double j = i / contentLength;
                int progress = (int) (i / size * 100);
                MLog.d("---------解压----onProgressUpdate-------------progress----------" + progress);
                progressBar.setProgress(progress);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Long result) {
            //解压缩完成之后，立即删除
            MLog.d("-------解压完成------去删除---------");
            if(new File(commonPath + mFile.getName()) !=null){
                new Tools().deleteFile(new File(commonPath + mFile.getName()));
            }
            MLog.d("-------解压完成------11111-------");
//            resumeDate();
//            if(!StringBiz.isEmpty(gameTitle) && gameTitle.equals("letters")) {
//                MLog.d("----------- 弹框解压完成，然后去静默下载 -------------------");
//                EventBus.getDefault().post(new DownLettersEvent(true, true));
//            }
            if (isCancelled()){
                MLog.d("-------cancel=true");
                finish();
                return;
            }
            MLog.d("----解压----orgsize=" + orgsize+"------extrasize="+extrasize+"------result="+result);
            if(orgsize == extrasize){
                //解压完成：统计
                MLog.d("-------解压完成---------------");
                finish();
                //eventbus 发送下载完成的事件
//                EventBus.getDefault().post(new DownloadEvent(true));

//                if(Constant.languageType == Constant.LANGUAGE_CHINESE){
//                    Toast.makeText(mcontext, "下载完成", Toast.LENGTH_LONG).show();
//                }else{
//                    Toast.makeText(mcontext, "The download is complete", Toast.LENGTH_LONG).show();
//                }
//                if(mFile.getName().contains("around"))
//                    CommonSharePreferences.setDownloadAroundPackage(mContext, zip_name.replace(".zip", ""));

                //SharePreferCommon.setGameStatus(mContext, gameid);
            } else {
                //解压失败：统计
//                Statistics.downloadRes(mContext, "解压失败", downloadName);
            }
//            Constant.download_finish = true;
        }

    private long unzip() {
        long extractedSize = 0L;
        Enumeration<ZipEntry> entries;
        ZipFile zip = null;
        try {
            zip = new ZipFile(new File(commonPath + mFile.getName()));
            long uncompressedSize = getOriginalSize(zip);
            publishProgress(0, (int) uncompressedSize);

            entries = (Enumeration<ZipEntry>) zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (entry.isDirectory()) {
                    continue;
                }
                File destination = new File(mOutput, entry.getName());
                if (!destination.getParentFile().exists()) {
                    MLog.d("-----make=" + destination.getParentFile().getAbsolutePath());
                    destination.getParentFile().mkdirs();
                }
                ProgressReportingOutputStream outStream = new ProgressReportingOutputStream(
                        destination);
                extractedSize += FileUtils.copy(zip.getInputStream(entry), outStream, ZipTask.this);
                outStream.close();
            }
        } catch (ZipException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(zip != null){
                    zip.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        extrasize = extractedSize;
        return extractedSize;
    }

        private final class ProgressReportingOutputStream extends FileOutputStream {
            public ProgressReportingOutputStream(File file)
                    throws FileNotFoundException {
                super(file);
            }

            @Override
            public void write(byte[] buffer, int byteOffset, int byteCount)
                    throws IOException {
                super.write(buffer, byteOffset, byteCount);
                mProgress += byteCount;
                publishProgress(mProgress);
            }
        }

    private long getOriginalSize(ZipFile file) {
        Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) file.entries();
        long originalSize = 0l;
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (entry.getSize() >= 0) {
                originalSize += entry.getSize();
            }
        }
        orgsize = originalSize;
        size = orgsize;
        return originalSize;
    }

    }

    @Override
    protected void onPause() {
        super.onPause();
        isFinish = true;
    }
}
