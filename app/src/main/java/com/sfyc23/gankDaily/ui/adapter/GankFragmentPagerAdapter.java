package com.sfyc23.gankDaily.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.sfyc23.gankDaily.android.BaseFragment;
import com.sfyc23.gankDaily.logic.network.Apis;
import com.sfyc23.gankDaily.ui.fragment.CategoryListFragment;
import com.sfyc23.gankDaily.ui.model.GankModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leilei on 2016/8/17.
 */
public class GankFragmentPagerAdapter extends FragmentPagerAdapter {
//    private List<GankModel> gankModels = new ArrayList<>();
//    private BaseFragment currentFragment;
    private List<String> titles = new ArrayList<>();

    public GankFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        titles = Apis.getGanHuoCateGory();
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        String title = titles.get(position);
//        if (!"ALL".equals(title)) {
//            fragment = CategoryListFragment.newInstance(title);
//        }
        fragment = CategoryListFragment.newInstance(title);
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }


//    /**
//     * Get the current fragment
//     */
//    public BaseFragment getCurrentFragment() {
//        return currentFragment;
//    }
}
