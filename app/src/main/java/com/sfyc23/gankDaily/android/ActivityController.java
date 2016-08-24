package com.sfyc23.gankDaily.android;

import android.app.Activity;

import com.sfyc23.gankDaily.base.utils.LogUtil;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;


public class ActivityController {
    private static final String TAG = "ActivityController";
    private static ArrayList<WeakReference<Activity>> mActivities = new ArrayList<WeakReference<Activity>>();
    private static ReferenceQueue<Activity> mReleaseQueue = new ReferenceQueue<Activity>();

    public static void finishAll() {
        synchronized (mActivities) {
            for (WeakReference<Activity> wf : mActivities) {
                Activity a = wf.get();
                LogUtil.d(TAG, "finish: " + a.toString());
                if (a != null) {
                    a.finish();
                }
            }
            mActivities.clear();
        }
    }

    @SuppressWarnings("unchecked")
    private static void cleanCache() {
        WeakReference<Activity> ref;
        synchronized (mActivities) {
            while ((ref = (WeakReference<Activity>) mReleaseQueue.poll()) != null) {
                mActivities.remove(ref);
            }
        }
    }

    public static ArrayList<Activity> getActivitys() {
        cleanCache();

        ArrayList<Activity> results = new ArrayList<Activity>();
        synchronized (mActivities) {
            for (WeakReference<Activity> wf : mActivities) {
                if ((wf == null) || (wf.get() == null)) {
                    mActivities.remove(wf);
                } else {
                    results.add(wf.get());
                }
            }
        }
        return results;
    }

    public static boolean isExit(Activity activity) {
        cleanCache();

        synchronized (mActivities) {
            for (WeakReference<Activity> wf : mActivities) {
                if (activity == wf.get())
                    return true;
            }
        }
        return false;
    }

    public static void add(Activity activity) {
        cleanCache();

        synchronized (mActivities) {
            if (!isExit(activity)) {
                WeakReference<Activity> wf = new WeakReference<Activity>(activity, mReleaseQueue);
                mActivities.add(wf);
            }
        }
    }

    public static void remove(Activity activity) {
        cleanCache();

        synchronized (mActivities) {
            for (int i = 0; i < mActivities.size(); i++) {
                WeakReference<Activity> wf = mActivities.get(i);
                if (activity == wf.get()) {
                    mActivities.remove(wf);
                    return;
                }
            }
        }
    }

    public static Activity getTopActivity() {
        cleanCache();

        if (mActivities.size() > 0)
            return mActivities.get(mActivities.size() - 1).get();

        return null;
    }
}
