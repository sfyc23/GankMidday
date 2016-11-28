package com.sfyc23.gankDaily.android;

import android.content.Context;

import com.sfyc23.gankDaily.BuildConfig;
import com.sfyc23.gankDaily.base.utils.DbUtils;
import com.sfyc23.gankDaily.base.utils.LogUtil;
import com.sfyc23.gankDaily.logic.rx.retrofit.ObservableProvider;
import com.sfyc23.gankDaily.logic.widget.swipeback.ActivityLifecycleHelper;
import com.tencent.bugly.Bugly;

/**
 * Created by leilei on 2016/8/24.
 */
public class MyApplication extends BaseApplication {
    public static Context sContext;
    private ActivityLifecycleHelper mActivityLifecycleHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        super.isDEBUG = BuildConfig.DEBUG;
        LogUtil.setDebug(isDEBUG);

        ObservableProvider.getInstance();
//        CrashReport.initCrashReport(sContext, "36fa395465", true);
        Bugly.init(getApplicationContext(), "36fa395465", true);

        registerActivityLifecycleCallbacks(mActivityLifecycleHelper = new ActivityLifecycleHelper());
        DbUtils.init(getApplicationContext());

    }
    public ActivityLifecycleHelper getActivityLifecycleHelper() {
        return mActivityLifecycleHelper;
    }


}
