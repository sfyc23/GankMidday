package com.sfyc23.gankDaily.base.utils;

import com.orhanobut.logger.Logger;

/**
 * Created by leilei on 2016/8/22.
 */
public class LogUtil {

    public static void e(String str) {
        Logger.e(str);
    }

    public static void d(String str) {
        Logger.d(str);
    }

    public static void i(String str) {
        Logger.i(str);
    }

    public static void v(String str) {
        Logger.v(str);
    }


    public static void e(String tag, String str) {
        Logger.e(tag + "  :  " + str);
    }

    public static void d(String tag, String str) {
        Logger.d(tag + "  :  " + str);
    }

    public static void i(String tag, String str) {
        Logger.i(tag + "  :  " + str);
    }

    public static void v(String tag, String str) {
        Logger.v(tag + "  :  " + str);
    }
}
