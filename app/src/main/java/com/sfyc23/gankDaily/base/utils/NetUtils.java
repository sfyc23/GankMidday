package com.sfyc23.gankDaily.base.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 判断是否有网络
 *
 * @author leilei
 * @ClassName: NetUtil
 * @date 2013-12-31 10:32:33
 */
public class NetUtils {
    public static final int NETWORN_NONE = 0;
    public static final int NETWORN_WIFI = 1;
    public static final int NETWORN_MOBILE = 2;


    /**
     * 判断当前网络是否可用。
     *
     * @param context
     * @return true 可用;false,不可用。
     */
    public static boolean isAvailable(Context context) {
        if(getNetworkState(context)==NETWORN_NONE){
            return false;
        }
        return true;
    }

    /**
     * @param context
     * @return int--0,没有网络; 1,wifi; 2,移动网络;
     * @Title: getNetworkState
     * @date 2013-12-26 12:12:11
     */
    public static int getNetworkState(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return NETWORN_WIFI;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return NETWORN_MOBILE;
            }
        } else {

        }
        return NETWORN_NONE;
/*		// Wifi
        State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		if (state == State.CONNECTED || state == State.CONNECTING) {
			return NETWORN_WIFI;
		}
		// 3G
		state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState();
		if (state == State.CONNECTED || state == State.CONNECTING) {
			return NETWORN_MOBILE;
		}
		return NETWORN_NONE;*/
    }



    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }
}

