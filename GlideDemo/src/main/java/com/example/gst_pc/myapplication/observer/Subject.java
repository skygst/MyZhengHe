package com.example.gst_pc.myapplication.observer;

import android.widget.TextView;

/**
 *  抽象主题 lei
 */
public interface Subject {

    public void attach(Observer observer);

    /**
     * 删除观察者对象
     * @param observer    观察者对象
     */
    public void detach(Observer observer);

    /**
     * 通知所有注册的观察者对象
     */
    public void nodify(String newState, TextView tv);

}
