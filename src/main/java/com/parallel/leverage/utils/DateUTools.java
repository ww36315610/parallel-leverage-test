package com.parallel.leverage.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUTools {
    public static void main(String[] args) {
//获取当前时间精确到毫秒级的时间戳，例：1525849325942
        Long time = System.currentTimeMillis();
        System.out.println(timeStamp2Date("1659414989000"));


    }

    public static String timeStamp2Date(String time) {
        Long timeLong = Long.parseLong(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//要转换的时间格式
        Date date;
        try {
            date = sdf.parse(sdf.format(timeLong));
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
