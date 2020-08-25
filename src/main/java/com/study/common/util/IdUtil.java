package com.study.common.util;

import java.sql.Timestamp;

public class IdUtil {

    /**
     * 根据时间生成id
     *
     * @param table
     * @return
     */
    public static String getNextID(String table) {
        String id = "";
        if (table.length() > 4) table = table.substring(0, 4);
        int year = new Timestamp(System.currentTimeMillis()).getYear();
        int month = (new Timestamp(System.currentTimeMillis()).getMonth() + 1);
        int day = new Timestamp(System.currentTimeMillis()).getDate();
        int hours = new Timestamp(System.currentTimeMillis()).getHours();
        int minte = new Timestamp(System.currentTimeMillis()).getMinutes();
        long time = new Timestamp(System.currentTimeMillis()).getTime();

        String num = String.valueOf(year).substring(1) + String.valueOf(month) + String.valueOf(day)
                + String.valueOf(hours) + String.valueOf(minte) + String.valueOf(time).substring(8, 13);
        id = table + num;
        return id;
    }
}
