package cn.com.ziquan.lib.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/11/15.
 */

public class DTUtil {

    public static final String FORMAT_yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss SSS";

    public static final String FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";

    public static final String FORMAT_yyyy_MM_dd_HH = "yyyy-MM-dd HH";

    public static final String FORMAT_yyyy_MM_dd = "yyyy-MM-dd";

    public static final String FORMAT_HH_mm_ss = "HH:mm:ss";

    public static final String FORMAT_HH_mm = "HH:mm";

    /**
     * 将时间格式化成yyyy_MM_dd_HH_mm_ss_SSS格式的字符串
     *
     * @param date
     * @return
     */
    public static String format_yyyy_MM_dd_HH_mm_ss_SSS(Date date) {
        return formatDate(date, FORMAT_yyyy_MM_dd_HH_mm_ss_SSS);
    }

    /**
     * 将时间格式化成yyyy_MM_dd_HH_mm_ss格式的字符串
     *
     * @param date
     * @return
     */
    public static String format_yyyy_MM_dd_HH_mm_ss(Date date) {
        return formatDate(date, FORMAT_yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 将时间格式化成yyyy_MM_dd_HH_mm格式的字符串
     *
     * @param date
     * @return
     */
    public static String format_yyyy_MM_dd_HH_mm(Date date) {
        return formatDate(date, FORMAT_yyyy_MM_dd_HH_mm);
    }

    /**
     * 将时间格式化成yyyy_MM_dd_HH格式的字符串
     *
     * @param date
     * @return
     */
    public static String format_yyyy_MM_dd_HH(Date date) {
        return formatDate(date, FORMAT_yyyy_MM_dd_HH);
    }

    /**
     * 将时间格式化成yyyy_MM_dd格式的字符串
     *
     * @param date
     * @return
     */
    public static String format_yyyy_MM_dd(Date date) {
        return formatDate(date, FORMAT_yyyy_MM_dd);
    }

    /**
     * 将时间格式化成HH_mm_ss格式的字符串
     *
     * @param date
     * @return
     */
    public static String getFORMAT_HH_mm_ss(Date date) {
        return formatDate(date, FORMAT_HH_mm_ss);
    }

    /**
     * 将时间格式化成HH_mm格式的字符串
     *
     * @param date
     * @return
     */
    public static String getFORMAT_HH_mm(Date date) {
        return formatDate(date, FORMAT_HH_mm);
    }

    /**
     * 格式化时间
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format) {
        String retString = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
            retString = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retString;
    }

    /**
     * 解析格式为yyyy_MM_dd_HH_mm_ss_SSS的字符串成Date对象
     *
     * @param strDate
     * @return
     */
    public static Date parse_yyyy_MM_dd_HH_mm_ss_SSS(String strDate) {
        return parseDate(strDate, FORMAT_yyyy_MM_dd_HH_mm_ss_SSS);
    }

    /**
     * 解析格式为yyyy_MM_dd_HH_mm_ss的字符串成Date对象
     *
     * @param strDate
     * @return
     */
    public static Date parse_yyyy_MM_dd_HH_mm_ss(String strDate) {
        return parseDate(strDate, FORMAT_yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 解析格式为yyyy_MM_dd_HH_mm的字符串成Date对象
     *
     * @param strDate
     * @return
     */
    public static Date parse_yyyy_MM_dd_HH_mm(String strDate) {
        return parseDate(strDate, FORMAT_yyyy_MM_dd_HH_mm);
    }

    /**
     * 解析格式为yyyy_MM_dd_HH的字符串成Date对象
     *
     * @param strDate
     * @return
     */
    public static Date parse_yyyy_MM_dd_HH(String strDate) {
        return parseDate(strDate, FORMAT_yyyy_MM_dd_HH);
    }

    /**
     * 解析格式为yyyy_MM_dd的字符串成Date对象
     *
     * @param strDate
     * @return
     */
    public static Date parse_yyyy_MM_dd(String strDate) {
        return parseDate(strDate, FORMAT_yyyy_MM_dd);
    }

    /**
     * 解析格式为HH_mm_ss的字符串成Date对象
     *
     * @param strDate
     * @return
     */
    public static Date parse_HH_mm_ss(String strDate) {
        return parseDate(strDate, FORMAT_HH_mm_ss);
    }

    /**
     * 解析格式为HH_mm的字符串成Date对象
     *
     * @param strDate
     * @return
     */
    public static Date parse_HH_mm(String strDate) {
        return parseDate(strDate, FORMAT_HH_mm);
    }

    /**
     * 解析指定格式的字符串成Date对象
     *
     * @param strDate
     * @param format
     * @return
     */
    public static Date parseDate(String strDate, String format) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        try {
            date = sdf.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获得时间戳
     *
     * @return
     */
    public static long getTimestamp() {
        return new Date().getTime() / 1000;
    }

    /**
     * 从时间戳得到Date对象
     *
     * @param timestamp
     * @return
     */
    public static Date getDateFromTimestamp(long timestamp) {
        return new Date(timestamp * 1000);
    }

    /**
     * 获得当前的年份
     *
     * @return
     */
    public static int getCurrentYear() {
        return Calendar.getInstance(Locale.getDefault()).get(Calendar.YEAR);
    }

    /**
     * 获得当前的月份
     *
     * @return
     */
    public static int getCurrentMonth() {
        return Calendar.getInstance(Locale.getDefault()).get(Calendar.MONTH);
    }

    /**
     * 获得当前的日期
     *
     * @return
     */
    public static int getCurrentDay() {
        return Calendar.getInstance(Locale.getDefault()).get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得当前是星期几
     *
     * @return
     */
    public static int getCurrentWeek() {
        return Calendar.getInstance(Locale.getDefault()).get(Calendar.WEEK_OF_MONTH);
    }
}
