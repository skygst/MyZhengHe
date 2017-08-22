package com.gst.frame.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * 时间工具类，可用于时间相关操作，如：
   getCurrentTimeInLong() 得到当前时间
   getTime(long timeInMillis, SimpleDateFormat dateFormat) 将long转换为固定格式时间字符串
 *
 */
public class TimeUtils {

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE    = new SimpleDateFormat("yyyy-MM-dd");

    private TimeUtils() {
        throw new AssertionError();
    }

    /**
     * long time to string
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }

    /**
     * @return 时间格式为 yyyy-mm-dd
     */
    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        String created = format(calendar.get(Calendar.YEAR)) + "-"
                + format(calendar.get(Calendar.MONTH) + 1) + "-"
                + format(calendar.get(Calendar.DAY_OF_MONTH));
        return created;
    }

    /**
     * 日期时间从1格式化为01 e.g.从2012/1/1可组合成2012/01/01
     *
     * @param x
     * @return
     */
    private String format(int x) {
        String s = "" + x;
        if (s.length() == 1)
            s = "0" + s;
        return s;
    }

    /**
     *  日期转换成秒数
     * @param dateTime
     * @return
     */
    public long timeInMillis(String dateTime) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateTime));
            System.out.println("时间转化后的毫秒数为："+c.getTimeInMillis());
            long tt = c.getTimeInMillis()/1000;
            return tt;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String dateFormat(String time) {
//		String s = "Thu, 20 Oct 2016 00:00:00 +0800";
        return processTimeFormat(time, "yyyy-MM-dd");
    }

    public String dateFormat2(String time) {
        return processTimeFormat(time, "yyyy/MM/dd");
    }

    public String dateFormat3(String time) {
        return processTimeFormat(time, "yyyy-MM-dd HH:mm:ss");
    }
    public String dateFormat4(String time) {
        return processTimeFormat(time, "yyyy.MM.dd");
    }

    public String processTimeFormat(String time, String type) {
        if(time == null || time.equals("")) return null;
        SimpleDateFormat sf = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z", Locale.ENGLISH);
        try {
            Date date = sf.parse(time);
            SimpleDateFormat sdf2 = new SimpleDateFormat(type);
            String birth = sdf2.format(date);
            return birth;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 根据时间戳秒数获取指定日期 */
    public static String getDateBySecondStr(String secondStr) {
        return getDate(Long.parseLong(secondStr + "000"));
    }

    /** 根据时间戳毫秒数获取指定日期 */
    public static String getDate(long milliseconds) {
        Date myDate = new Date(milliseconds);
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return sDateFormat.format(myDate);
    }

    public static String getSystemTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
        String created = df.format(new Date());
        return created;
    }

    /**
     * 计算两个日期之间相差的天数
     */
    public static int daysBetween(String effectiveDate) {
        try {
            String current = getSystemTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(current));

            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(effectiveDate));

            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
