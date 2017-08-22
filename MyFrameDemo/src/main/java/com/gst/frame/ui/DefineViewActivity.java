package com.gst.frame.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gst.frame.R;
import com.gst.frame.utils.DPIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gst-pc on 2017/2/6.
 */

public class DefineViewActivity extends BaseActivity {

    private LinearLayout llLayout;
    private TextView tv;
    private List<String> listString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.define_view);

        initView();
        setView();
    }

    private void initView() {
        listString = new ArrayList<String>();
        String[] str = new String[] {
                "益智早教", "经典动画", "父母课堂"
        };
        for(int i=0; i<str.length; i++) {
            listString.add(str[i]);
        }
    }
    private void setView() {
        llLayout = (LinearLayout) findViewById(R.id.ll_type);
        
        addView();
    }

    private void addView() {

        for (int i=0; i <listString.size(); i++) {
            final int index = i;
            llLayout = (LinearLayout) findViewById(R.id.ll_type);
            tv = new TextView(mContext);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.leftMargin = 30;
            tv.setLayoutParams(lp);
            tv.setTextColor(Color.GRAY);
            tv.setBackgroundColor(-1);
            tv.setPadding(DPIUtil.dip2px(mContext, 10),
                    DPIUtil.dip2px(mContext, 0), DPIUtil.dip2px(mContext, 10),
                    DPIUtil.dip2px(mContext, 0));
            tv.setTextSize(16);
            tv.setGravity(Gravity.CENTER);
            tv.setText(listString.get(i));
            if (i == 0) {
                tv.setBackgroundResource(R.drawable.bg_booktype);
                tv.setTextColor(Color.rgb(255, 255, 255));
            }
            llLayout.addView(tv);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setViewByForeach();
                    v.setBackgroundResource(R.drawable.bg_booktype);
                    ((TextView) v).setTextColor(Color.rgb(255, 255, 255));
                }

                void setViewByForeach() {
                    int childCount = llLayout.getChildCount();
                    for (int j = 0; j < childCount; j++) {
                        TextView textview = (TextView) llLayout.getChildAt(j);
                        textview.setBackgroundColor(-1);
                        textview.setTextColor(Color.GRAY);
                    }
                }
            });
        }

    }
}
