package com.sfyc23.gankDaily.logic.network;

import android.text.TextUtils;

import com.sfyc23.gankDaily.android.BaseApplication;
import com.sfyc23.gankDaily.base.utils.CommonUtils;
import com.sfyc23.gankDaily.base.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by _SOLID
 * Date:2016/5/16
 * Time:16:13
 */
public class Apis {

    public static String[] GanHuoCateGory = new String[]{"all", "Android", "iOS", "拓展资源", "前端", "瞎推荐"};

    public static List<String> getGanHuoCateGory() {

        List<String> list = new ArrayList<>();
        String prefCategory = PrefUtils.getString(PrefUtils.HOME_CATEGORY);
        if (!CommonUtils.isValid(prefCategory)) {
            for (int i = 0; i < GanHuoCateGory.length; i++) {
                list.add(GanHuoCateGory[i]);
            }
        } else {
            String[] str = prefCategory.split("\\|");
            for (int i = 0; i < str.length; i++) {
                list.add(str[i]);
            }
        }
        return list;
    }

    public static class Urls {

        public static String GanHuoBaseUrl = "http://www.gank.io/api/";
        /**
         * 获取发布过干货的日期
         */
        public static String GanHuoDates = "http://www.gank.io/api/day/history";

        /**
         * http://gank.io/api/data/福利/10/1
         * <p>
         * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
         */
        public static String GanHuoData = GanHuoBaseUrl + "data/";

        /**
         * 每日数据： http://gank.io/api/day/年/月/日
         */
        public static String GanHuoDataByDay = GanHuoBaseUrl + "day/";


        /**
         * 随机图片
         */
        public static String RandomPicture = "http://lelouchcrgallery.tk/rand";
    }
}
