package com.example.gst_pc.myapplication.observer;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 *  具体主题 类
 */
public class XiaoXiSubject implements Subject {

    private List<Observer> list = new ArrayList<Observer>();

    /**
     * 注册观察者对象
     * @param observer    观察者对象
     */
    public void attach(Observer observer) {
        list.add(observer);
        System.out.println("------ Attached an observer ------------");
    }

    /**
     * 删除观察者对象
     * @param observer    观察者对象
     */
    public void detach(Observer observer) {
        list.remove(observer);
    }

    /**
     * 通知所有注册的观察者对象
     */
    public void nodify(String newState, TextView tv) {
        for(Observer observer : list) {
            observer.update(newState, tv);
        }
    }

}
