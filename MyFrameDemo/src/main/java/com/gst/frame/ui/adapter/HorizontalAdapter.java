package com.gst.frame.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gst.frame.R;

import java.util.List;

/**
 * Created by gst-pc on 2017/2/9.
 */

public class HorizontalAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<String> mListString;
    private Context mContext;

    public HorizontalAdapter(Context context, List<String> listString) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mListString = listString;
    }

    @Override
    public int getCount() {
        return mListString.size();
    }

    @Override
    public Object getItem(int position) {
        return mListString.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.horizon_item_1, null);
            holder.rl1 = (RelativeLayout) convertView.findViewById(R.id.rl_1);
            holder.ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tvMore = (TextView) convertView.findViewById(R.id.tv_more);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(position == (mListString.size() - 1)) {
            holder.rl1.setVisibility(View.INVISIBLE);
        } else {
            holder.rl1.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    class ViewHolder {
        RelativeLayout rl1, rl2;
        ImageView ivPic;
        TextView tvTitle, tvMore;
    }

}
