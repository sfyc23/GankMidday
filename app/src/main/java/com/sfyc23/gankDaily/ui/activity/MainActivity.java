package com.sfyc23.gankDaily.ui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.sfyc23.gankDaily.R;
import com.sfyc23.gankDaily.android.ActivityController;
import com.sfyc23.gankDaily.android.ActivityHelper;
import com.sfyc23.gankDaily.android.BaseActivity;
import com.sfyc23.gankDaily.android.BaseFragment;
import com.sfyc23.gankDaily.base.utils.AnimUtils;
import com.sfyc23.gankDaily.ui.adapter.BottomViewPagerAdapter;
import com.sfyc23.gankDaily.ui.fragment.GankFragment;
import com.sfyc23.gankDaily.ui.fragment.GirlFragment;
import com.sfyc23.gankDaily.ui.fragment.VideoFragment;

import java.util.ArrayList;

import butterknife.BindView;
//import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * Created by leilei on 2016/8/16.
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private static final int RC_SEARCH = 0;

    @BindView(R.id.view_pager)
    AHBottomNavigationViewPager viewPager;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;

    Toolbar mToolbar;

    private BaseFragment currentFragment;
    private BottomViewPagerAdapter adapter;
    private int mBottomPosition = 0;

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
        bottomNavigation.setAccentColor(ContextCompat.getColor(mContext, R.color.md_red_500));
        bottomNavigation.setInactiveColor(ContextCompat.getColor(mContext, R.color.md_cyan_500));


        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(GankFragment.newInstance());
        fragments.add(GirlFragment.newInstance("福利"));
        fragments.add(VideoFragment.newInstance("休息视频"));

        viewPager.setOffscreenPageLimit(3);
        adapter = new BottomViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        currentFragment = adapter.getCurrentFragment();
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (mBottomPosition == position) {
                    return false;
                } else {
                    mBottomPosition = position;
                }
                viewPager.setCurrentItem(position, false);
                switch (position) {
                    case 0:
                        mToolbar.setTitle(R.string.main_bottom_gank);
                        animateToolbar();
                        break;
                    case 1:
                        mToolbar.setTitle(R.string.main_bottom_girl);
                        animateToolbar();
                        break;
                    case 2:
                        mToolbar.setTitle(R.string.main_bottom_video);
                        animateToolbar();
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

//        checkUpdate(false,false);
    }

    @Override
    public void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.main_bottom_gank);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.main_bottom_gank);
        animateToolbar();
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
            case R.id.menu_search:
                View searchMenuView = mToolbar.findViewById(R.id.menu_search);
                int[] loc = new int[2];
                searchMenuView.getLocationOnScreen(loc);
                startActivityForResult(SearchActivity.createStartIntent(this, loc[0], loc[0] +
                        (searchMenuView.getWidth() / 2)), RC_SEARCH, ActivityOptions
                        .makeSceneTransitionAnimation(this).toBundle());
                searchMenuView.setAlpha(0f);
                return true;
            default:
                break;
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

    private void animateToolbar() {
        // this is gross but toolbar doesn't expose it's children to animate them :(
        View t = mToolbar.getChildAt(0);
        if (t != null && t instanceof TextView) {
            final TextView title = (TextView) t;

            // fade in and space out the title.  Animating the letterSpacing performs horribly so
            // fake it by setting the desired letterSpacing then animating the scaleX ¯\_(ツ)_/¯
            title.setAlpha(0.6f);
            title.setScaleX(0.8f);
            title.setScaleY(0.8f);
            title.animate()
                    .alpha(1f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setStartDelay(300)
                    .setDuration(900)
                    .setInterpolator(AnimUtils.getFastOutSlowInInterpolator(this));
    /*        ObjectAnimator anim = ObjectAnimator.ofFloat(title, "new_title", 0.6f, 1.2f, 1.0f);

            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {

                    Float animFaction = (Float) valueAnimator.getAnimatedValue();
                    title.setScaleX(animFaction);
                    title.setScaleY(animFaction);
                }
            });
            anim.setDuration(900);
            anim.setStartDelay(300);*/
//            anim1.start();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RC_SEARCH:
                // reset the search icon which we hid
                View searchMenuView = mToolbar.findViewById(R.id.menu_search);
                if (searchMenuView != null) {
                    searchMenuView.setAlpha(1f);
                }
                break;


        }
    }
}
