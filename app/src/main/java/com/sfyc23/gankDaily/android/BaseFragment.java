package com.sfyc23.gankDaily.android;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.sfyc23.gankDaily.logic.rx.RxBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by leilei on 2016/8/16.
 */
public abstract class BaseFragment extends Fragment {

    protected FragmentActivity mContext;
    private View mView;
    private Unbinder unBinder;
    private Subscription mSubscription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        if (layoutId == 0)
            return null;
        mView = inflater.inflate(getLayoutId(), container, false);
        mContext = getActivity();
        mSubscription = RxBus.getInstance().toObserverable(String.class).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                handleRxMsg(s);
            }
        });
        unBinder = ButterKnife.bind(this, mView);
        initViews(savedInstanceState);
        getData();
        return mView;
    }

    protected abstract int getLayoutId();
    protected abstract void initViews(Bundle savedInstanceState);
    protected abstract void getData();

    protected View findViewById(int id) {
        if (mView == null)
            return null;
        return mView.findViewById(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBinder.unbind();
        if (mSubscription != null && mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
    }


    protected void handleRxMsg(String msg) {

    }



}