package com.study.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    public static final String DATE_FORMAT_TYPE_1 = "yyyyMMdd";
    public static final String DATE_FORMAT_TYPE_2 = "yyyy-MM-dd";
    public static final String DATE_FORMAT_TYPE_3 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_TYPE_4 = "yyyy年MM月dd日HH时mm分ss秒";

    private static String[] weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * 获取两个日期字符串之间的日期集合
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getBetweenDate(String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_TYPE_2);
        // 声明保存日期集合
        List<String> list = new ArrayList<String>();
        try {
            // 转化成日期类型
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);

            //用Calendar 进行日期比较判断
            Calendar calendar = Calendar.getInstance();
            while (startDate.getTime() <= endDate.getTime()) {
                // 把日期添加到集合
                list.add(sdf.format(startDate));
                // 设置日期
                calendar.setTime(startDate);
                //把日期增加一天
                calendar.add(Calendar.DATE, 1);
                // 获取增加后的日期
                startDate = calendar.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
