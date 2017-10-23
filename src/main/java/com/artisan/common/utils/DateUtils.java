package com.artisan.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 * @author Leeyom Wang
 * @date 2017年10月19日 12:05
 */
public class DateUtils {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 将日期转化为指定格式的字符串
     * @param date    需要转化的日期
     * @param pattern 转化规则
     * @return 指定格式的日期字符串
     */
    public static String getString(Date date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 将日期字符串转换为指定格式的日期对象
     * @param strDate 日期字符串
     * @param format  转化规则
     * @return 指定格式的日期对象
     */
    public static Date getDate(String strDate, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Can't parse " + strDate + " using " + format);
        }
    }

    /**
     * 获取两个日期对象之间相差的天数
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return 相差天数
     */
    public static int getDays(Date beginDate, Date endDate) {
        int days = -1;
        long beginMillisecond = beginDate.getTime();
        long endMillisecond = endDate.getTime();
        long millisecondForDay = 24 * 60 * 60 * 1000;
        days = (int) ((endMillisecond - beginMillisecond) / millisecondForDay);
        return days;
    }

    /**
     * 获得两个日期对象之间相差的小时数
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return 相差小时
     */
    public static long getTimes(Date beginDate, Date endDate) {
        long time = -1;
        long beginMillisecond = beginDate.getTime();
        long endMillisecond = endDate.getTime();
        long millisecondForDay = 60 * 60 * 1000;
        time = (long) ((endMillisecond - beginMillisecond) / millisecondForDay);
        return time;
    }

    /**
     * 指定的日期加上或者减去指定的天数
     * @param date 日期
     * @param day  天数
     * @return 修改后的日期
     */
    public static Date addDays(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 判断当前时间是上午还是下午
     * @param date 日期
     * @return 上午or下午
     */
    public static String getAmOrPm(Date date) {
        String str = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int num = calendar.get(Calendar.AM_PM);
        if (num == 0) {
            str = "上午";
        } else if (num == 1) {
            str = "下午";
        }
        return str;
    }

    /**
     * 获取指定时间的星期数
     * @param date 日期
     * @return 例如：星期一
     */
    public static String getStrWeek(Date date) {
        String strWeek = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekNum = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekNum == 1) {
            strWeek = "星期天";
        } else if (weekNum == 2) {
            strWeek = "星期一";
        } else if (weekNum == 3) {
            strWeek = "星期二";
        } else if (weekNum == 4) {
            strWeek = "星期三";
        } else if (weekNum == 5) {
            strWeek = "星期四";
        } else if (weekNum == 6) {
            strWeek = "星期五";
        } else if (weekNum == 7) {
            strWeek = "星期六";
        }
        return strWeek;
    }

    /**
     * 获取指定时间的星期数
     * @param date
     * @return 例如：1
     */
    public static int getIntWeek(Date date) {
        int intWeek = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekNum = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekNum == 1) {
            intWeek = 7;
        } else if (weekNum == 2) {
            intWeek = 1;
        } else if (weekNum == 3) {
            intWeek = 2;
        } else if (weekNum == 4) {
            intWeek = 3;
        } else if (weekNum == 5) {
            intWeek = 4;
        } else if (weekNum == 6) {
            intWeek = 5;
        } else if (weekNum == 7) {
            intWeek = 6;
        }
        return intWeek;
    }

    /**
     * 获取当前时间的下个星期一
     * @return 下个星期一的Date对象
     */
    public static Date getNextMonday() {
        Date date = new Date();
        int w = getIntWeek(date);
        if (w == 1) {
            return addDays(date, 7);
        }
        if (w == 2) {
            return addDays(date, 6);
        }
        if (w == 3) {
            return addDays(date, 5);
        }
        if (w == 4) {
            return addDays(date, 4);
        }
        if (w == 5) {
            return addDays(date, 3);
        }
        if (w == 6) {
            return addDays(date, 2);
        }
        return null;
    }

    /**
     * 获取当月第一天
     * @return
     */
    public static String getFirstDayOfMonth() {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1 号
        str = sdf.format(lastDate.getTime());
        return str;
    }

    /**
     * 获取上月第一天
     * @return
     */
    public static String getPreviousMonthFirst() {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1 号
        lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1 号
        // lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天
        str = sdf.format(lastDate.getTime());
        return str;
    }

    /**
     * 获得上月最后一天的日期
     * @return
     */
    public static String getPreviousMonthEnd() {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.MONTH, -1);// 减一个月
        lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
        str = sdf.format(lastDate.getTime());
        return str;
    }

    /**
     * 获取给定的两个日期之间的所有的Date
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 日期结果集
     * @throws Exception 开始时间大于结束时间的异常
     */
    public static List<Date> dateSplit(Date startDate, Date endDate) throws Exception {
        if (!startDate.before(endDate))
            throw new Exception("开始时间应该在结束时间之后");
        Long spi = endDate.getTime() - startDate.getTime();
        Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数

        List<Date> dateList = new ArrayList<Date>();
        dateList.add(endDate);
        for (int i = 1; i <= step; i++) {
            dateList.add(new Date(dateList.get(i - 1).getTime() - (24 * 60 * 60 * 1000)));// 比上一天减一
        }
        return dateList;
    }
}
