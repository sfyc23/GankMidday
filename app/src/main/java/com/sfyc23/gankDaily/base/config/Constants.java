package com.sfyc23.gankDaily.base.config;

/**
 * Created by leilei on 2016/8/19.
 */
public class Constants {
    /**
     * null字符串, 用于处理server返回异常字符串, 3.4.0
     */
//    public static final String NULL_STRING = "+T-23==53-X7-+YuRG";
    public static final String NULL_STRING = "";
    public static final int NULL_INT = -1;
    public static final long NULL_LONG = -1;
    public static final float NULL_FLOAT = -1;
    public static final double NULL_DOUBLE = -1;
    public static final int DEFAULT_INT = 0;


    //定义两次click间隔必须的间隔时间（单位是毫秒）
    public static final int CLICK_INTERVAL_TIME = 1000;
    //再加一个时间限制为2秒的，机器卡的时候CLICK_INTERVAL_TIME,不够用
    public static final int CLICK_INTERVAL_LONG_TIME = 2000;
}
