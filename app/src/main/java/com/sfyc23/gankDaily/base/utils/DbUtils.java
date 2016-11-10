package com.sfyc23.gankDaily.base.utils;

import android.content.Context;
import android.os.Environment;

import com.litesuits.orm.LiteOrm;

public class DbUtils {
    private static volatile LiteOrm liteOrm;
    public final static String dbName = "GankDaily.db";
//	public static final String dbName = Environment
//			.getExternalStorageDirectory().getAbsolutePath()
//			+ "/GankDaily.db";

    public static Context mContext;

    public static void init(Context context) {
        mContext = context;
        liteOrm = createDb();
    }

    public static LiteOrm createDb() {
        return LiteOrm.newCascadeInstance(mContext, dbName);
    }

    public static LiteOrm getLiteOrm() {
        if(liteOrm == null){
            synchronized (DbUtils.class){
                if (liteOrm == null) {
                    return createDb();
                }
            }
        }
        return liteOrm;
    }
}

