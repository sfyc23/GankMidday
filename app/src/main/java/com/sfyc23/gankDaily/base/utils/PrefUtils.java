package com.sfyc23.gankDaily.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.sfyc23.gankDaily.android.BaseApplication;
import com.sfyc23.gankDaily.base.config.Constants;

import java.util.ArrayList;

/**
 * Created by leilei on 2016/8/19.
 */
public class PrefUtils {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private static PrefUtils mPreferences;
    private final static String PRE_NAME = "Setting";


    public static final String HOME_CATEGORY = "HomeCategory";


    public PrefUtils(Context context) {
        mSharedPreferences = context.getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    private ArrayList<SharedPreferences.OnSharedPreferenceChangeListener> mListeners = new ArrayList<SharedPreferences.OnSharedPreferenceChangeListener>();

    public static PrefUtils getInstance() {
        return getPreferences(BaseApplication.getAppContext());
    }
    public synchronized boolean removeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        boolean ret = false;
        if (listener != null) {
            ret = mListeners.remove(listener);
        }
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
        return ret;
    }

    public synchronized boolean addListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        boolean ret = false;
        if (listener != null) {
            if (!mListeners.contains(listener)) {
                ret = mListeners.add(listener);
            }
            mSharedPreferences.registerOnSharedPreferenceChangeListener(listener);
        }
        return ret;
    }

    public static synchronized PrefUtils getPreferences(Context context) {
        if (mPreferences == null) {
            mPreferences = new PrefUtils(context.getApplicationContext());
        }
        return mPreferences;
    }


    public void delPreferencesListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public static void setString(String key, String value) {
        getInstance().setStringValue(key, value);
    }

    private void setStringValue(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public static String getString(String key) {
        return getInstance().getStringValue(key, Constants.NULL_STRING);
    }

    public static String getString(String key, String defaultValue) {
        return getInstance().getStringValue(key, defaultValue);
    }

    private String getStringValue(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public static void setInt(String key, int value) {
        getInstance().setIntValue(key, value);
    }

    private void setIntValue(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    public static int getInt(String key) {
        return getInstance().getIntValue(key, Constants.NULL_INT);
    }

    public static int getInt(String key, int defaultValue) {
        return getInstance().getIntValue(key, defaultValue);
    }

    private int getIntValue(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public static void setLong(String key, long value) {
        getInstance().setLongValue(key, value);
    }

    private void setLongValue(String key, long value) {
        mEditor.putLong(key, value);
        mEditor.apply();
    }

    public static Long getLong(String key) {
        return getInstance().getLongValue(key, Constants.NULL_LONG);
    }

    public static Long getLong(String key, Long defaultValue) {
        return getInstance().getLongValue(key, defaultValue);
    }

    private long getLongValue(String key, Long defaultValue) {
        return mSharedPreferences.getLong(key, defaultValue);
    }

    public static void setBoolean(String key, boolean value) {
        getInstance().setBooleanValue(key, value);
    }

    private void setBooleanValue(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.apply();
    }

    public static Boolean getBoolean(String key) {
        return getInstance().getBooleanValue(key, false);
    }

    public static boolean getBoolean(String key, boolean value) {
        return getInstance().getBooleanValue(key, value);
    }

    private boolean getBooleanValue(String key, Boolean value) {
        return mSharedPreferences.getBoolean(key, value);
    }

    public static void setFloat(String key, Float value) {
        getInstance().setDoubleValue(key, value);
    }

    private void setDoubleValue(String key, Float value) {
        mEditor.putFloat(key, value);
        mEditor.apply();
    }

    public static float getFloat(String key) {
        return getInstance().getFloatValue(key, Constants.NULL_FLOAT);
    }

    public static float getBoolean(String key, float value) {
        return getInstance().getFloatValue(key, value);
    }

    private float getFloatValue(String key, float value) {
        return mSharedPreferences.getFloat(key, value);
    }
}
