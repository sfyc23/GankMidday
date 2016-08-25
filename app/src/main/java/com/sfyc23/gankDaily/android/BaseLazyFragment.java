package com.sfyc23.gankDaily.android;

/**
 * Created by leilei on 2016/8/25.
 */
public abstract class BaseLazyFragment extends BaseFragment {
    /*Fragment当前状态是否可见 */
    protected boolean isVisible;
    /** 标志位，标志已经初始化完成 */
    protected boolean isPrepared;
    /** 是否已被加载过一次，第二次就不再去请求数据了 */
    protected boolean mHasLoadedOnce = false;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /** 当Fragment视图可见时 */
    protected void onVisible() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        lazyLoad();
    }

    /*** 当Fragment视图不可见时 */
    protected void onInvisible() {

    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();
}
