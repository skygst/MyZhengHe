package com.gst.frame.ui.theme_1;

import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gst.frame.R;
import com.gst.frame.ui.BaseActivity;
import com.gst.frame.ui.adapter.HorizontalAdapter;
import com.gst.frame.utils.GlideUtil;
import com.gst.frame.utils.MLog;

import java.util.ArrayList;
import java.util.List;

/**
 *  主题1 （ScrollView嵌套HorizontalScrollView）
 */
public class Theme1Activity extends BaseActivity {

    private ImageView ivPic;
    private HorizontalScrollView hlView;
    private TextView tvTxt;
    private HorizontalAdapter adapter;
    private List<String> mListString;
    private LinearLayout llAddView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_1);

        MLog.d("------ onCreate ----------");
        init();
        setView();
    }

    private void init() {
        mListString = new ArrayList<String>();
        mListString.add("1");
        mListString.add("2");
        mListString.add("3");
        mListString.add("4");
        mListString.add("5");
        mListString.add("6");
        mListString.add("7");
        mListString.add("8");
        mListString.add("9");
        mListString.add("10");
        mListString.add("11");
        mListString.add("12");
    }

    private void setView() {
        ivPic = (ImageView) findViewById(R.id.iv_network);
        hlView = (HorizontalScrollView) findViewById(R.id.hl_scrollview);
        llAddView = (LinearLayout) findViewById(R.id.ll_add_view);
        tvTxt = (TextView) findViewById(R.id.tv_txt);
        addView(mListString);
//        listView = (ListView) findViewById(R.id.list_view_hl);
//        adapter = new HorizontalAdapter(mContext, mListString);
//        listView.setAdapter(adapter);
        tvTxt.setText("有木有两个铁球同时落地的感觉~~对，我应该搞两个球~~ps:物理公式要是错了，就当没看见哈\n" +
                "\n" +
                "自定义TypeEvaluator传入的泛型可以根据自己的需求，自己设计个Bean。\n" +
                "\n" +
                "好了，我们已经分别讲解了ValueAnimator和ObjectAnimator实现动画；二者区别；如何利用部分API，自己更新属性实现效果；自定义TypeEvaluator实现我们的需求；但是我们并没有讲如何设计插值，其实我觉得把，这个插值默认的那一串实现类够用了~~很少，会自己去设计个超级变态的~嗯~所以：略。" +
                "有木有两个铁球同时落地的感觉~~对，我应该搞两个球~~ps:物理公式要是错了，就当没看见哈\n" +
                "\n" +
                "自定义TypeEvaluator传入的泛型可以根据自己的需求，自己设计个Bean。\n" +
                "\n" +
                "好了，我们已经分别讲解了ValueAnimator和ObjectAnimator实现动画；二者区别；如何利用部分API，自己更新属性实现效果；自定义TypeEvaluator实现我们的需求；但是我们并没有讲如何设计插值，其实我觉得把，这个插值默认的那一串实现类够用了~~很少，会自己去设计个超级变态的~嗯~所以：略。");
        showView();
    }

    private void showView() {
        String picUrl = "https://img.bbpapp.com/raz/book_cover/level_c_1.png";
        picUrl = "http://img.bbpapp.com/uploads/carousels/small/50be92d90b6e804d351f3388c5a3a262.jpeg";
        GlideUtil.loadImg(picUrl, ivPic);
    }

    private void addView(List<String> listString) {
        if(listString != null && listString.size() > 0) {
            for (int i = 0; i < listString.size(); i++) {
                View productsView = View.inflate(mContext, R.layout.horizon_item_1, null);
                RelativeLayout rl1 = (RelativeLayout) productsView.findViewById(R.id.rl_1);
                ImageView ivPic = (ImageView) productsView.findViewById(R.id.iv_pic);
                TextView tvTitle = (TextView) productsView.findViewById(R.id.tv_title);
                TextView tvMore = (TextView) productsView.findViewById(R.id.tv_more);
                if(i == (listString.size() - 1)) {
                    rl1.setVisibility(View.INVISIBLE);
                    tvMore.setVisibility(View.VISIBLE);
                } else {
                    rl1.setVisibility(View.VISIBLE);
                    tvMore.setVisibility(View.GONE);
                }
                llAddView.addView(productsView);
            }
        }
    }

}
