package com.sfyc23.gankDaily.base.utils;

import com.orhanobut.logger.Logger;

/**
 * Created by leilei on 2016/8/22.
 */
public class LogUtil {

    public static boolean debugable = true;

    /**
     * 设置是否开启日志
     *
     * @param debugable
     */
    public static void setDebug(boolean debugable) {
        LogUtil.debugable = debugable;
    }

    public static void e(String str) {
        if(debugable){
            Logger.e(str);
        }
    }

    public static void d(String str) {
        if(debugable){
            Logger.d(str);
        }
    }

    public static void i(String str) {
        if(debugable){
            Logger.i(str);
        }
    }

    public static void v(String str) {
        if(debugable){
            Logger.v(str);
        }
    }


    public static void e(String tag, String str) {
        if(debugable){
            Logger.e(tag + "  :  " + str);
        }
    }

    public static void d(String tag, String str) {
        if(debugable){
            Logger.d(tag + "  :  " + str);
        }
    }

    public static void i(String tag, String str) {
        if(debugable){
            Logger.i(tag + "  :  " + str);
        }
    }

    public static void v(String tag, String str) {
        if(debugable){
            Logger.v(tag + "  :  " + str);
        }
    }

    public static void Json(String str) {
        if(debugable){
            Logger.json(str);
        }
    }
}
