package com.gst.okhttp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gst.okhttp.fragment.NewsFragment;
import com.gst.okhttp.okhttp_view.MainView;
import com.gst.okhttp.presenter.MainPresenter;
import com.gst.okhttp.presenter.MainPresenterImpl;
import com.gst.okhttp.utils.LogUtils;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private MainPresenter mMainPresenter; // 侧拉菜单滑出来的那一部分属于NavigationView
    private Context mContext;
    private CircleImageView ivHeadPortrait; // 头像

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        View headerView = mNavigationView.getHeaderView(0);
        ivHeadPortrait = (CircleImageView) headerView.findViewById(R.id.profile_image);
        setupDrawerContent(mNavigationView);

        mMainPresenter = new MainPresenterImpl(this);
        LogUtils.d("-----------onCreate--------", "mainAc");
        switch2News();
        ivHeadPortrait.setOnClickListener(this);
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        mMainPresenter.switchNavigation(menuItem.getItemId());
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        Toast.makeText(mContext, "点击了" + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
    }

    @Override
    public void switch2News() {
        LogUtils.d("-----------switch2News--------", "switch2News");
        // replace 是先remove掉相同id的所有fragment，然后在add当前的这个fragment。
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new NewsFragment()).commit();
        mToolbar.setTitle(R.string.navigation_news);
    }

    @Override
    public void switch2Images() {
//        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new ImageFragment()).commit();
//        mToolbar.setTitle(R.string.navigation_images);
    }

    @Override
    public void switch2Weather() {
//        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new WeatherFragment()).commit();
//        mToolbar.setTitle(R.string.navigation_weather);
    }

    @Override
    public void switch2About() {
//        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new AboutFragment()).commit();
//        mToolbar.setTitle(R.string.navigation_about);
    }

    @Override
    public void onClick(View v) {
        if(v == ivHeadPortrait) { // 点击头像
            // TODO 测试 branch
            System.out.println("------------ 点击头像 ---------------");
            Toast.makeText(mContext, "点击了头像", Toast.LENGTH_SHORT).show();
        }
    }
}
