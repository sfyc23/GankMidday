package com.sfyc23.gankDaily.android;

import android.support.v7.widget.RecyclerView;

import com.sfyc23.gankDaily.base.utils.LogUtil;
import com.sfyc23.gankDaily.logic.rx.retrofit.ObservableProvider;
import com.sfyc23.gankDaily.logic.ui.adapter.CommonRvAdapter;

import java.util.List;

import rx.Subscriber;

/**
 * Created by leilei on 2016/8/22.
 */
public abstract class BaseListFragment<T> extends BaseFragment{

    protected static final int ACTION_REFRESH = 1;//刷新
    protected static final int ACTION_LOAD_MORE = 2;//加载更多
    protected static final int ACTION_PRE_LOAD = 3;//预加载数据

    protected CommonRvAdapter<T> mAdapter;

    protected int mCurrentAction = ACTION_REFRESH;
    protected int mCurrentPageIndex = 1;


    protected void loadAdapter() {
        mAdapter = setAdapter();
    }

    /**
     * load data(obtain data from local if no network)
     */
    protected void loadData() {
        final String reqUrl = getUrl(mCurrentPageIndex);
//        if (!NetworkUtils.isNetworkConnected(getMContext()) && mCurrentAction == ACTION_REFRESH) {//no network
//            loadDataFromLocal();
//            ToastUtils.getInstance().showToast(getString(R.string.no_network));
//
//        } else {
//            loadDataFromNetWork(reqUrl);
//        }
        loadDataFromNetWork(reqUrl);
    }

    private void loadDataFromNetWork(String reqUrl) {
        LogUtil.e("reqUrl = " + reqUrl);
        ObservableProvider.getDefault().loadString(reqUrl)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onDataErrorReceived();
                    }

                    @Override
                    public void onNext(String result) {
                        if (mCurrentAction == ACTION_REFRESH) {//store the first page data
//                            storeOfflineData(getUrl(1), result);
                        }
                        onDataSuccessReceived(result);
                    }
                });

    }
    protected void switchActionAndLoadData(int action) {
        mCurrentAction = action;
        switch (mCurrentAction) {
            case ACTION_REFRESH:
                mCurrentPageIndex = 1;
                loadData();
                break;
            case ACTION_LOAD_MORE:
                mCurrentPageIndex++;
                loadData();
                break;
            case ACTION_PRE_LOAD:
//                loadDataFromLocal();
                break;
        }

    }

    /**
     * the url of request
     *
     * @param mCurrentPageIndex
     * @return
     */
    protected abstract String getUrl(int mCurrentPageIndex);

    /**
     * Parse the response data
     *
     * @param result
     * @return
     */
    protected abstract List<T> parseData(String result);

    /**
     * obtain data occur error
     */
    protected abstract void onDataErrorReceived();

    /**
     * obtain data success
     *
     * @param result
     */
    protected abstract void onDataSuccessReceived(String result);

    /**
     * set RecyclerView's Adapter
     *
     * @return
     */
    protected abstract CommonRvAdapter<T> setAdapter();

    /**
     * set RecyclerView's LayoutManager
     *
     * @return
     */
    protected abstract RecyclerView.LayoutManager setLayoutManager();

    protected abstract void loadComplete();
}
