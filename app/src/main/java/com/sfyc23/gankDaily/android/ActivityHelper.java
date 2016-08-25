package com.sfyc23.gankDaily.android;

import android.app.Activity;

import com.sfyc23.gankDaily.base.utils.LogUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ActivityHelper {
	private static final String TAG = "ActivityHelper";

	static ArrayList<WeakReference<Activity>> mActivities = new ArrayList<WeakReference<Activity>>();

	public static void exitAll() {
		for (WeakReference<Activity> wf : mActivities) {
			Activity a = wf.get();
			if(a != null){
				LogUtil.d(TAG, "finish: " + a.toString());
				a.finish();
			}
		}
		mActivities.clear();
	}
	
// --Commented out by Inspection START (2014/10/16 9:52):
//	/**
//	 * 获取最后一个activity
//	 * */
//	public static WeakReference<Activity> getLastActivity(){
//		if(mActivities == null || mActivities.isEmpty()){
//			return null;
//		}
//
//		int size = mActivities.size();
//		try {
//			return mActivities.get(size-1);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//
//	}
// --Commented out by Inspection STOP (2014/10/16 9:52)

	private static WeakReference<Activity> getActivity(Activity activity){
		if(mActivities == null || mActivities.isEmpty()){
			return null;
		}
		
		for (WeakReference<Activity> wf : mActivities) {
			Activity a = wf.get();
			if(a != null && a == activity){
				LogUtil.d(TAG, "getActivity: " + a.toString());
				return wf;
			}
		}
		
		return null;
	}

	public static void add(Activity activity) {
		if (getActivity(activity) == null) {
			LogUtil.d(TAG, "add: " + activity.toString());
			WeakReference<Activity> wf = new WeakReference<Activity>(activity);
			mActivities.add(wf);
		}

	}
	
	public static void remove(Activity activity) {
		WeakReference<Activity> wf = getActivity(activity);
		if (wf != null) {
			LogUtil.d(TAG, "remove: " + activity.toString());
			mActivities.remove(wf);
		}
	}
}
