package com.sfyc23.gankDaily.ui.model;

import com.sfyc23.gankDaily.android.BaseFragment;

/**
 * Created by leilei on 2016/8/17.
 */
public class GankModel {

    private String title;
    private BaseFragment fragment;

    public GankModel(String title,BaseFragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

//    public GankModel(BaseFragment fragment, String title) {
//        this.fragment = fragment;
//        this.title = title;
//    }

    public BaseFragment getFragment() {
        return fragment;
    }

    public void setFragment(BaseFragment fragment) {
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
