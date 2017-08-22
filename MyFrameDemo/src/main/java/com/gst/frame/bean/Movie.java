package com.gst.frame.bean;

import java.util.List;

/**
 * Created by gst-pc on 2017/2/7.
 */

public class Movie<T> {

    private String count;
    private String start;
    private String total;
    private List<T> subjects;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<T> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<T> subjects) {
        this.subjects = subjects;
    }

//    class Subjects {
//        private String title;
//        private String year;
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public void setYear(String year) {
//            this.year = year;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public String getYear() {
//            return year;
//        }
//    }

}
