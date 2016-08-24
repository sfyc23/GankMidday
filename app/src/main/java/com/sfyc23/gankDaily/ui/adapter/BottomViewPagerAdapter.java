package com.sfyc23.gankDaily.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.sfyc23.gankDaily.android.BaseFragment;

import java.util.ArrayList;

/**
 * Created by leilei on 2016/8/16.
 */
public class BottomViewPagerAdapter extends FragmentPagerAdapter {


    private ArrayList<BaseFragment> fragments = new ArrayList<>();
    private BaseFragment currentFragment;

    public BottomViewPagerAdapter(FragmentManager fm,ArrayList<BaseFragment> fragments ) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = ((BaseFragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }


    /**
     * Get the current fragment
     */
    public BaseFragment getCurrentFragment() {
        return currentFragment;
    }
}
