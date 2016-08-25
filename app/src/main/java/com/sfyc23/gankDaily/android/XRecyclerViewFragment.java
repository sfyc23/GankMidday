package com.sfyc23.gankDaily.android;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.sfyc23.gankDaily.R;
import com.sfyc23.gankDaily.base.utils.LogUtil;
import com.sfyc23.gankDaily.base.utils.StringUtils;
import com.sfyc23.gankDaily.logic.rx.retrofit.TransformUtils;
import com.sfyc23.gankDaily.logic.widget.StatusView;

import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by leilei on 2016/8/22.
 */
public abstract class XRecyclerViewFragment <T> extends BaseListFragment {

    private static String TAG = "XRecyclerViewFragment";

    @BindView(R.id.recyclerview)
    public XRecyclerView mRecyclerView;

    @BindView(R.id.status_view)
    public StatusView mStatusView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_xrecyclerview;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mAdapter = setAdapter();
        mRecyclerView.setLayoutManager(setLayoutManager());
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotatePulse);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                switchActionAndLoadData(ACTION_REFRESH);
            }

            @Override
            public void onLoadMore() {
                switchActionAndLoadData(ACTION_LOAD_MORE);
            }
        });

        mStatusView.setOnRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActionAndLoadData(ACTION_REFRESH);
            }
        });
        mRecyclerView.setLoadingMoreEnabled(isHaveLoadMore());
        isPrepared = true;
    }

    public boolean isHaveLoadMore() {
        return true;
    }

    @Override
    protected void getData() {
//        mStatusView.showLoading();
//        loadData();
    }

    @Override
    protected void lazyLoad() {
        mStatusView.showLoading();
        loadData();
    }

    @Override
    protected void onDataErrorReceived() {
        LogUtil.i(TAG, "onDataErrorReceived");
        mStatusView.showError();
        loadComplete();
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onDataSuccessReceived(final String result) {
//        LogUtil.i(TAG, "onDataSuccessReceived ="+result);
        LogUtil.Json(result);
        if (!StringUtils.isEmpty(result)) {
            Observable
                    .create(new Observable.OnSubscribe<List<T>>() {
                        @Override
                        public void call(Subscriber<? super List<T>> subscriber) {
                            List<T> list = parseData(result);
                            subscriber.onNext(list);
                            subscriber.onCompleted();
                        }
                    })
                    .compose(TransformUtils.<List<T>>defaultSchedulers())
                    .subscribe(new Subscriber<List<T>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            mStatusView.showError();
                        }

                        @Override
                        public void onNext(List<T> list) {
                            if (mCurrentAction == ACTION_REFRESH) {
                                mAdapter.replaceAll(list);
                            } else {
                                mAdapter.addAll(list);
                            }
                            mHasLoadedOnce = true;
                            if (mCurrentAction != ACTION_PRE_LOAD) loadComplete();
                            mStatusView.showContent();
                        }
                    });

        } else {
            if (!(mCurrentAction == ACTION_PRE_LOAD))
                onDataErrorReceived();
        }
    }

    @Override
    protected void loadComplete() {
        if (mCurrentAction == ACTION_REFRESH)
            mRecyclerView.refreshComplete();
        if (mCurrentAction == ACTION_LOAD_MORE)
            mRecyclerView.loadMoreComplete();
    }

    protected int getHeadViewCount() {
        return 3;
    }
}
