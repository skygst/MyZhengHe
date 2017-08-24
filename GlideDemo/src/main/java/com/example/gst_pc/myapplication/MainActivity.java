package com.example.gst_pc.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gst_pc.myapplication.observer.Person;
import com.example.gst_pc.myapplication.observer.XiaoXiSubject;

/**
 *  测试 观察者模式的使用
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private TextView tvSub;
    private Button btnTong, btnXi, btnFeng, btnNotify;
    private Person pTong, pXi, pFeng;
    private XiaoXiSubject mSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setView();
    }

    private void init() {
        mSubject = new XiaoXiSubject();
    }

    private void setView() {
        btnTong = (Button) findViewById(R.id.btn_tong);
        btnXi = (Button) findViewById(R.id.btn_xi);
        btnFeng = (Button) findViewById(R.id.btn_fegn);
        btnNotify = (Button) findViewById(R.id.btn_notify);
        tvSub = (TextView) findViewById(R.id.tv_sub);

        btnTong.setOnClickListener(this);
        btnXi.setOnClickListener(this);
        btnFeng.setOnClickListener(this);
        btnNotify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnTong) {
            if(pTong == null) {//注册观察者
                pTong = new Person("小同");
                mSubject.attach(pTong);
            }
        } else if(v == btnXi) {
            if(pXi == null) {//注册观察者
                pXi = new Person("小希");
                mSubject.attach(pXi);
            }
        } else if(v == btnFeng) {
            if(pFeng == null) {//注册观察者
                pFeng = new Person("小丰");
                mSubject.attach(pFeng);
            }
        } else if(v == btnNotify) {
            // 主题(被观察者)更新了内容，通知所有观察者
            tvSub.setText("");
            mSubject.nodify("小希更新了微博啦，赶快来看看吧~", tvSub);
        }
    }
}
