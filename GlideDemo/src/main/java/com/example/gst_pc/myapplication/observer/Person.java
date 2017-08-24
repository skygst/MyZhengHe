package com.example.gst_pc.myapplication.observer;

import android.widget.TextView;

/**
 * 具体观察者（Person）类
 */
public class Person implements Observer {

    private String name;
    public Person(String name) {
        this.name = name;
    }

    @Override
    public void update(String msg, TextView tv) {
        tv.setText(tv.getText()+name+":"+ msg +"\n");
    }
}
