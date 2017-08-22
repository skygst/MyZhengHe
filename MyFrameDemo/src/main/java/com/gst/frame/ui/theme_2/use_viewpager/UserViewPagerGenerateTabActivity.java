package com.gst.frame.ui.theme_2.use_viewpager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.gst.frame.R;
import com.gst.frame.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 *  单纯使用ViewPager 实现Tab
 */
public class UserViewPagerGenerateTabActivity extends BaseActivity implements View.OnClickListener {

    //声明ViewPager
    private ViewPager mViewpager;

    //声明四个Tab
    private LinearLayout mTabWeixin;
    private LinearLayout mTabFrd;
    private LinearLayout mTabAddress;
    private LinearLayout mTabSetting;

    //声明四个ImageButton
    private ImageButton mWeixinImg;
    private ImageButton mFrdImg;
    private ImageButton mAddressImg;
    private ImageButton mSettingImg;

    //声明ViewPager的适配器
    private PagerAdapter mAdpater;
    //用于装载四个Tab的List
    private List<View> mTabs = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉TitleBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tab_viewpage_main);
        initViews();//初始化控件
        initDatas();//初始化数据
        initEvents();//初始化事件

    }

    private void initEvents() {
        //设置四个Tab的点击事件
        mTabWeixin.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
        mTabAddress.setOnClickListener(this);
        mTabSetting.setOnClickListener(this);

        //添加ViewPager的切换Tab的监听事件
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //获取ViewPager的当前Tab
                int currentItem = mViewpager.getCurrentItem();
                //将所以的ImageButton设置成灰色
                resetImgs();
                //将当前Tab对应的ImageButton设置成绿色
                switch (currentItem) {
                    case 0:
                        mWeixinImg.setImageResource(R.mipmap.tab_weixin_pressed);
                        break;
                    case 1:
                        mFrdImg.setImageResource(R.mipmap.tab_find_frd_pressed);
                        break;
                    case 2:
                        mAddressImg.setImageResource(R.mipmap.tab_address_pressed);
                        break;
                    case 3:
                        mSettingImg.setImageResource(R.mipmap.tab_settings_pressed);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initDatas() {
        //初始化ViewPager的适配器
        mAdpater = new PagerAdapter() {
            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mTabs.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mTabs.get(position));
            }
        };
        //设置ViewPager的适配器
        mViewpager.setAdapter(mAdpater);
    }

    //初始化控件
    private void initViews() {
        mViewpager = (ViewPager) findViewById(R.id.id_viewpager);

        mTabWeixin = (LinearLayout) findViewById(R.id.id_tab_weixin);
        mTabFrd = (LinearLayout) findViewById(R.id.id_tab_frd);
        mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
        mTabSetting = (LinearLayout) findViewById(R.id.id_tab_setting);

        mWeixinImg = (ImageButton) findViewById(R.id.id_tab_weixin_img);
        mFrdImg = (ImageButton) findViewById(R.id.id_tab_frd_img);
        mAddressImg = (ImageButton) findViewById(R.id.id_tab_address_img);
        mSettingImg = (ImageButton) findViewById(R.id.id_tab_setting_img);

        //获取到四个Tab
        LayoutInflater inflater = LayoutInflater.from(this);
        View tab1 = inflater.inflate(R.layout.tab1, null);
        View tab2 = inflater.inflate(R.layout.tab2, null);
        View tab3 = inflater.inflate(R.layout.tab3, null);
        View tab4 = inflater.inflate(R.layout.tab4, null);

        //将四个Tab添加到集合中
        mTabs.add(tab1);
        mTabs.add(tab2);
        mTabs.add(tab3);
        mTabs.add(tab4);

    }

    @Override
    public void onClick(View v) {
        //先将四个ImageButton都设置成灰色
        resetImgs();
        switch (v.getId()) {
            case R.id.id_tab_weixin:
                //设置viewPager的当前Tab
                mViewpager.setCurrentItem(0);
                //将当前Tab对应的ImageButton设置成绿色
                mWeixinImg.setImageResource(R.mipmap.tab_weixin_pressed);
                break;
            case R.id.id_tab_frd:
                mViewpager.setCurrentItem(1);
                mFrdImg.setImageResource(R.mipmap.tab_find_frd_pressed);
                break;
            case R.id.id_tab_address:
                mViewpager.setCurrentItem(2);
                mAddressImg.setImageResource(R.mipmap.tab_address_pressed);
                break;
            case R.id.id_tab_setting:
                mViewpager.setCurrentItem(3);
                mSettingImg.setImageResource(R.mipmap.tab_settings_pressed);
                break;
        }
    }

    //将四个ImageButton设置成灰色
    private void resetImgs () {
        mWeixinImg.setImageResource(R.mipmap.tab_weixin_normal);
        mFrdImg.setImageResource(R.mipmap.tab_find_frd_normal);
        mAddressImg.setImageResource(R.mipmap.tab_address_normal);
        mSettingImg.setImageResource(R.mipmap.tab_settings_normal);
    }

}
