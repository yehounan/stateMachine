package com.yezi.statemachinedemo.business.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description:
 * @Author: yezi
 * @Date: 2020/6/19 14:05
 */
public class Test {


    public static void main(String[] args) {
        String format = format(LocalDateTime.now(), "yyyy-MM-dd hh:mm:ss");
        System.out.println(format);
    }



    public static String format(LocalDateTime time, String fmt) {
        if (time == null) {
            return null;
        }
        return time.format(DateTimeFormatter.ofPattern(fmt));
    }
}
