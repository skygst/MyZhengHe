package com.gst.frame.ui.tab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gst.frame.R;
import com.gst.frame.ui.theme_2.Theme2Activity;
import com.gst.frame.ui.theme_2.use_viewpager.UserViewPagerGenerateTabActivity;
import com.gst.frame.ui.theme_2.user_viewpage_fragment.UserViewPagerAndFragmentGenerateTabActivity;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
      Button btn1= (Button) findViewById(R.id.id_btn_1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, MainActivity.class));
            }
        });
        Button btn2= (Button) findViewById(R.id.id_btn_2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,Main2Activity.class));
            }
        });

        Button btn3= (Button) findViewById(R.id.id_btn_3);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,Main3Activity.class));
            }
        });

        Button btnTab1 = (Button) findViewById(R.id.id_theme_2_tab_1);
        btnTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 单纯使用Fragment实现Tab的方式
                startActivity(new Intent(StartActivity.this,Theme2Activity.class));
            }
        });

        Button btnTab2 = (Button) findViewById(R.id.id_theme_2_tab_2);
        btnTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 单纯使用ViewPager 实现Tab
                startActivity(new Intent(StartActivity.this,UserViewPagerGenerateTabActivity.class));
            }
        });

        Button btnTab3 = (Button) findViewById(R.id.id_theme_2_tab_3);
        btnTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 使用 ViewPager + Fragment 实现Tab
                startActivity(new Intent(StartActivity.this,UserViewPagerAndFragmentGenerateTabActivity.class));
            }
        });
    }
}
