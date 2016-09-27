package com.sfyc23.gankDaily.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sfyc23.gankDaily.BuildConfig;
import com.sfyc23.gankDaily.R;
import com.sfyc23.gankDaily.android.BaseActivity;

import butterknife.BindView;


/**
 * Created by leilei on 2016/8/24.
 */
public class AboutMeActivity extends BaseActivity {

    @BindView(R.id.tv_about_version)
    TextView mTvVersion;
    @BindView(R.id.toolbar_about)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar_about)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.appbar_about)
    AppBarLayout mAppbar;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        mTvVersion.setText("Version " + BuildConfig.VERSION_NAME);
        mCollapsingToolbarLayout.setTitle(getString(R.string.app_name));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected boolean supportSlideBack() {
        return true;
    }
}
