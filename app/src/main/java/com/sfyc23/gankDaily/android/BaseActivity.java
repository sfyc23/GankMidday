package com.sfyc23.gankDaily.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.sfyc23.gankDaily.R;
import com.sfyc23.gankDaily.base.utils.LogUtil;

import butterknife.ButterKnife;

/**
 * Created by leilei on 2016/8/16.
 * 基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected ProgressDialog mProgressDialog;
//    private static final boolean DEBUG_ACTIVITY_LIFECYCLE = BaseApplication.isDEBUG;

    private static final boolean DEBUG_ACTIVITY_LIFECYCLE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if (savedInstanceState != null) {
            finish();
            return;
        }
        logActivityLifecycle("onCreate");
        ActivityHelper.add(this);
        ActivityController.add(this);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initVariables();
        initViewsAndEvents(savedInstanceState);
        loadData();
    }

    /**
     * 第一步
     */
    protected abstract int getLayoutId();

    protected abstract void initVariables();

    protected abstract void initViewsAndEvents(Bundle savedInstanceState);

    protected abstract void loadData();


    public void showLoadingDialog() {
        showLoadingDialog(getString(R.string.common_loading));
    }

    /**
     * 显示加载进度条
     *
     * @param msg
     */
    public void showLoadingDialog(String msg) {
        showLoadingDialog(msg, true);
    }

    /**
     * 显示加载进度条
     *
     * @param msg
     */
    public void showLoadingDialog(String msg, boolean cancelFalg) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(
                    getTopParent((Activity) mContext));
        }
        mProgressDialog.setCanceledOnTouchOutside(cancelFalg);
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    /**
     * 隐藏加载的进度条
     */
    public void dismissLoadingDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    private Context getTopParent(Activity context) {
        Activity parent = context.getParent();
        while (parent != null) {
            context = parent;
            parent = context.getParent();
        }
        return context;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onDestroy()");
        }
        ActivityHelper.remove(this);
        ActivityController.remove(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//		StatService.onPause(this);
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onPause()");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//		StatService.onResume(this);
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onResume()");
        }
//		UserBehaviorViewHelper.onViewStart(getClass().getSimpleName());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onSaveInstanceState()");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onStart()");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onStop()");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onCreateOptionsMenu()");
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onPrepareOptionsMenu()");
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onOptionsItemSelected()");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Object getLastCustomNonConfigurationInstance() {
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("getLastCustomNonConfigurationInstance()");
        }
        return super.getLastCustomNonConfigurationInstance();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onRestoreInstanceState()");
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onNewIntent()");
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onConfigurationChanged()");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onKeyDown() : keyCode[" + keyCode + "]|event[" + event.getAction() + "]");
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onTouchEvent()");
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onTrackballEvent()");
        }
        return super.onTrackballEvent(event);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onContentChanged()");
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onAttachedToWindow()");
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onDetachedFromWindow()");
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onCreateContextMenu()");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onContextItemSelected()");
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onSearchRequested() {
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onSearchRequested()");
        }
        return super.onSearchRequested();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onActivityResult() requestCode=" + requestCode + " resultCode=" + resultCode);
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onAttachFragment()");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (DEBUG_ACTIVITY_LIFECYCLE) {
            logActivityLifecycle("onBackPressed()");
        }
    }

    //打印生命周期
    private void logActivityLifecycle(String message) {
        LogUtil.i(getClass().getSimpleName()+ ",  [Lifecycle] " + message);
//        LogUtil.i("activity_uimain-start"+ getClass().getSimpleName() + "[Lifecycle] " + message + " this: " + this);
    }
}
