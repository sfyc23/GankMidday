package com.sfyc23.gankDaily.android;

import android.app.Application;
import android.content.Context;

/**
 * Created by leilei on 2016/8/19.
 */
public class BaseApplication extends Application {
    private static Context mAppContext;
    public static boolean isDEBUG = false;

    public BaseApplication(){
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化全局Application
        mAppContext = this;
    }
    public static Context getAppContext() {
        return mAppContext;
    }

    public static void gc() {
        // TODO Auto-generated method stub
    }
}
