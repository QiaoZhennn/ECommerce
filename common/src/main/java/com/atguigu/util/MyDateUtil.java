package com.atguigu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 加密相关的时间工具类
 */
public class MyDateUtil {

    public static void main(String[] args) {
        //获取3天之后的时间
        Calendar c=Calendar.getInstance();
        c.add(Calendar.DATE,3);
        System.out.println(c.getTime());
    }

    /**
     * @param i：当前时间提前i天，传入负数，退后i天，传入正数
     * @return
     */
    public static Date getMyFlowDate(int i) {
        Calendar c=Calendar.getInstance();
        c.add(Calendar.DATE,i);
        return c.getTime();
    }

    public static String getPasswordDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        String format = simpleDateFormat.format(new Date());
        return format;
    }
}
