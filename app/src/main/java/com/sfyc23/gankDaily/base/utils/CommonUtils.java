package com.sfyc23.gankDaily.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import com.sfyc23.gankDaily.android.BaseApplication;
import com.sfyc23.gankDaily.base.config.Constants;

import java.text.DecimalFormat;
import java.util.AbstractCollection;

public class CommonUtils {
	/**
	 * true  正常
	 * false 为空
	 * @param obj
	 * @return
     */
	public static boolean isValid(Object obj) {
		if (obj == null)
			return false;

		if (obj instanceof Long) {
			return (((Long) obj).intValue()) != Constants.NULL_INT;
		}

		if (obj instanceof Integer) {
			return ((Integer) obj) != Constants.NULL_INT;
		}

		if (obj instanceof Float) {
			return Math.abs(((Float) obj) - Constants.NULL_FLOAT) > 1e-5;
		}

		if (obj instanceof Double) {
			return Math.abs(((Double) obj) - Constants.NULL_DOUBLE) > 1e-5;
		}

		if (obj instanceof String) {
			String target = (String) obj;
			return (!target.trim().equalsIgnoreCase(""))
					&& (!target.equalsIgnoreCase(Constants.NULL_STRING));
		}

		if (obj instanceof Boolean) {
			return obj != null;
		}

		if (obj instanceof AbstractCollection<?>) {
			return ((AbstractCollection<?>) obj).size() != 0;
		}
		return true;
	}

	private static long mLastClickTime = Constants.NULL_LONG;

	/**
	 *  if (!CommonUtils.clickValid()) return;
	 *  判断点击的间隔
	 * @return
     */
	public synchronized static boolean clickValid() {
		if (!CommonUtils.isValid(mLastClickTime)) {
			mLastClickTime = System.currentTimeMillis();
			return true;
		}

		if (System.currentTimeMillis() - mLastClickTime < Constants.CLICK_INTERVAL_TIME) {
			return false;
		}

		mLastClickTime = System.currentTimeMillis();
		return true;
	}

	private static long mLasClickLongTime = Constants.NULL_LONG;

	public synchronized static boolean clickValidWithLongTime() {
		if (!CommonUtils.isValid(mLasClickLongTime)) {
			mLasClickLongTime = System.currentTimeMillis();
			return true;
		}

		if (System.currentTimeMillis() - mLasClickLongTime < Constants.CLICK_INTERVAL_LONG_TIME) {
			return false;
		}

		mLasClickLongTime = System.currentTimeMillis();
		return true;
	}



	/**
	 * 判断是否为图片地址
	 * @param url 图片的url
	 * @return	true，是。false不是的。
     */
	public static boolean isValidPictureUrl(String url) {
		if (!isValid(url)) {
			return false;
		}

		if (url.startsWith("http://") && url.split("/").length < 4) {
			return false;
		}

		//TODO TietieFileSystem(catch中的一个类没有加上)

		if (!url.startsWith("http://") &&
				!url.startsWith("https://") &&
				!url.startsWith("drawable://") &&
				!url.startsWith("assets") &&
				!url.startsWith("file://") ) {
			return false;
		}

		if (url.endsWith("/"))
			return false;

		return true;
	}



	public static String getVersionName() {
		PackageInfo packageInfo = CommonUtils.getApplicationInfo(BaseApplication.getAppContext());
		return packageInfo.versionName;
	}

	public static int getVersionCode() {
		PackageInfo packageInfo = CommonUtils.getApplicationInfo(BaseApplication.getAppContext());
		return packageInfo.versionCode;
	}

	public static PackageInfo getApplicationInfo(Context context) {
		PackageInfo info = null;
		try {
			info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

		return info;
	}

	/**
	 * show the soft keyboard if the soft keyboard is hiding.
	 *
	 * @param view
	 * @param delayedTime the delay millisecond
	 */
	public static void showSoftKeyboard(final View view, long delayedTime) {
		view.postDelayed(new Runnable() {
			@Override
			public void run() {
				InputMethodManager inputManager = (InputMethodManager)
						view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
				inputManager.showSoftInput(view, 0);
			}
		}, delayedTime);
	}

	public static void showSoftKeyboard(final View view) {
		showSoftKeyboard(view, 500);
	}

	/**
	 * hide the soft keyboard if the soft keyboard is showing.
	 *
	 * @param context the context.
	 */
	public static void hideSoftKeyboard(Context context) {
		if ((context != null) && (((Activity) context).getCurrentFocus() != null)) {
			((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(((Activity) context)
					.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
	public static void hideSoftKeyboard(@NonNull View view) {
		Context context = view.getContext();
		hideSoftKeyboard(context,view);
	}

	public static void hideSoftKeyboard(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
}
