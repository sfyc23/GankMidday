package com.sfyc23.gankDaily.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.sfyc23.gankDaily.R;
import com.sfyc23.gankDaily.android.ActivityController;
import com.sfyc23.gankDaily.android.ActivityHelper;
import com.sfyc23.gankDaily.android.BaseActivity;
import com.sfyc23.gankDaily.android.BaseFragment;
import com.sfyc23.gankDaily.ui.adapter.BottomViewPagerAdapter;
import com.sfyc23.gankDaily.ui.fragment.GankFragment;
import com.sfyc23.gankDaily.ui.fragment.GirlFragment;
import com.sfyc23.gankDaily.ui.fragment.VideoFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by leilei on 2016/8/16.
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    
    @BindView(R.id.view_pager)
    AHBottomNavigationViewPager viewPager;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;

    Toolbar mToolbar;

    private BaseFragment currentFragment;
    private BottomViewPagerAdapter adapter;


    private ArrayList<AHBottomNavigationItem>
            bottomNavigationItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables() {
        Intent intent = new Intent();


    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("技术");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//决定左上角图标的右侧是否有向左的小箭头
        setSupportActionBar(mToolbar);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(
                R.string.main_bottom_gank,
                R.drawable.ic_main_gank,
                R.color.md_purple_100);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(
                R.string.main_bottom_girl,
                R.drawable.ic_main_girl,
                R.color.md_blue_200);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(
                R.string.main_bottom_video,
                R.drawable.ic_main_video,
                R.color.md_cyan_500);

        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);

        bottomNavigation.addItems(bottomNavigationItems);
//        bottomNavigation.setColored(true);
        bottomNavigation.setAccentColor(ContextCompat.getColor(mContext,R.color.md_red_500));
        bottomNavigation.setInactiveColor(ContextCompat.getColor(mContext,R.color.md_cyan_500));


        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(GankFragment.newInstance());
        fragments.add(GirlFragment.newInstance());
        fragments.add(VideoFragment.newInstance());

        viewPager.setOffscreenPageLimit(3);
        adapter = new BottomViewPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);

        currentFragment = adapter.getCurrentFragment();
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager.setCurrentItem(position, false);
                switch (position){
                    case 0:
                        mToolbar.setTitle(R.string.main_bottom_gank);
                        break;
                    case 1:
                        mToolbar.setTitle(R.string.main_bottom_girl);
                        break;
                    case 2:
                        mToolbar.setTitle(R.string.main_bottom_video);
                        break;
                    default:
                        break;
                }

                return true;
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected boolean supportSlideBack() {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_about:
                startActivity(new Intent(this, AboutMeActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    long mExitTime = 0;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Snackbar.make(bottomNavigation, "再按一下退出",
                    Snackbar.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
            ActivityHelper.exitAll();
            ActivityController.finishAll();
            finish();
        }
    }

}
