package com.example.gst_pc.myapplication.observer;

import android.widget.TextView;

/**
 * 抽象观察者（Observer）接口
 */
public interface Observer {

    /**
     * 更新接口
     * @param state    更新的状态
     */
    public void update(String state, TextView tv);

}
