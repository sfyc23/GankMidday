package com.sfyc23.gankDaily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.sfyc23.gankDaily.R;
import com.sfyc23.gankDaily.android.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by leilei on 2016/8/24.
 */
public class PictureActivity extends BaseActivity {

    public static final String EXTRA_IMAGE_URL = "image_url";
    public static final String EXTRA_IMAGE_TITLE = "image_title";
    public static final String TRANSIT_PIC = "picture";

    String mImageUrl, mImageTitle;
    @BindView(R.id.iv_picture)
    ImageView mIvPicture;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @butterknife.BindView(R.id.appbar_layout)
    android.support.design.widget.AppBarLayout mAppBar;
    protected boolean mIsHidden = false;

    PhotoViewAttacher mPhotoViewAttacher;

    public static Intent newIntent(Context context, String url, String desc) {
        Intent intent = new Intent(context, PictureActivity.class);
        intent.putExtra(PictureActivity.EXTRA_IMAGE_URL, url);
        intent.putExtra(PictureActivity.EXTRA_IMAGE_TITLE, desc);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture;
    }

    @Override
    protected void initVariables() {
        mImageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        mImageTitle = getIntent().getStringExtra(EXTRA_IMAGE_TITLE);
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

        ViewCompat.setTransitionName(mIvPicture, TRANSIT_PIC);
        Picasso.with(this).load(mImageUrl).into(mIvPicture);
//        setAppBarAlpha(0.7f);
//        mAppBar.setAlpha(0.7f);
//        setTitle(mImageTitle);
        setupPhotoAttacher();

        mToolbar.setTitle("图片");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);//决定左上角的图标是否可以点击
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);//决定左上角图标的右侧是否有向左的小箭头
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupPhotoAttacher() {
        mPhotoViewAttacher = new PhotoViewAttacher(mIvPicture);
        mPhotoViewAttacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float v, float v1) {
                hideOrShowToolbar();
            }
        });
        // @formatter:off

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected boolean supportSlideBack() {
        return false;
    }

    protected void hideOrShowToolbar() {
        mAppBar.animate()
                .translationY(mIsHidden ? 0 : -mAppBar.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsHidden = !mIsHidden;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPhotoViewAttacher.cleanup();
    }
}
