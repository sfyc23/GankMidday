package com.sfyc23.gankDaily.base.utils;

/**
 * Created by leilei on 2016/8/22.
 */
public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() <= 0;
    }
}
