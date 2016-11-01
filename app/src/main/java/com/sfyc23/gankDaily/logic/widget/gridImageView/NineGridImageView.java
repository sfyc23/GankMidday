package com.sfyc23.gankDaily.logic.widget.gridImageView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sfyc23.gankDaily.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 原地址：GitHub: https://github.com/laobie
 */
public class NineGridImageView<T> extends ViewGroup {

    public final static int STYLE_GRID = 0;     // 宫格布局
//    public final static int STYLE_FILL = 1;     // 全填充布局

    private int mRowCount;       // 行数
    private int mColumnCount;    // 列数

    private int mMaxSize;        // 最大图片数
    //    private int mShowStyle;     // 显示风格
    private int mGap;           // 宫格间距
    private int mSingleImgSize; // 单张图片时的尺寸
//    private int mGridSize;   // 宫格大小,即图片大小

    private int mGridSizeWidth;   // 宫格大小,即图片大小
    private int mGridSizeHeight;   // 宫格大小,即图片大小

    private int mNumColumns = 3;//一页显示多少个
    //宽高比
    private float mSizeScale = 1.0f;

    private List<ImageView> mImageViewList = new ArrayList<>();
    private List<T> mImgDataList;

    private NineGridImageViewAdapter<T> mAdapter;

    public NineGridImageView(Context context) {
        this(context, null);
    }

    public NineGridImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NineGridImageView);
        this.mGap = (int) typedArray.getDimension(R.styleable.NineGridImageView_imgGap, 0);
        this.mSingleImgSize = typedArray.getDimensionPixelSize(R.styleable.NineGridImageView_singleImgSize, -1);
        int maxSize = typedArray.getInt(R.styleable.NineGridImageView_maxSize, mMaxSize);
        int numColumns = typedArray.getInt(R.styleable.NineGridImageView_numColumns, mNumColumns);
        float sizeScale = typedArray.getFloat(R.styleable.NineGridImageView_sizeScale, mSizeScale);

        this.mNumColumns = numColumns > 0 ? numColumns : mNumColumns;
        this.mMaxSize = maxSize > 0 ? maxSize : mMaxSize;
        this.mSizeScale = sizeScale > 0 ? sizeScale : mSizeScale;

        typedArray.recycle();
        this.setVisibility(GONE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height;
        int totalWidth = width - getPaddingLeft() - getPaddingRight();
        if (mImgDataList != null && mImgDataList.size() > 0) {

            //如果只有一张图片，且有设置他的大小。
            if (mImgDataList.size() == 1 && mSingleImgSize != -1) {
//                mGridSize = Math.min(mSingleImgSize, totalWidth);
                mGridSizeWidth = Math.min(mSingleImgSize, totalWidth);
                mGridSizeHeight = (int) (mGridSizeWidth * mSizeScale);
            } else {
                mImageViewList.get(0).setScaleType(ImageView.ScaleType.CENTER_CROP);
//                mGridSize = (totalWidth - mGap * (mColumnCount - 1)) / mColumnCount;
                mGridSizeWidth = (totalWidth - mGap * (mColumnCount - 1)) / mColumnCount;
                mGridSizeHeight = (int) (mGridSizeWidth * mSizeScale);
            }

            height = mGridSizeHeight * mRowCount + mGap * (mRowCount - 1) + getPaddingTop() + getPaddingBottom();
            Log.e("NineGirdImage", "mGridSizeWidth = " + mGridSizeWidth + ",height = " + height + ",mColumnCount = " + mColumnCount + ",mRowCount = " + mRowCount);
            setMeasuredDimension(width, height);

        } else {
            height = width;
            setMeasuredDimension(width, height);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutChildrenView();
    }

    /**
     * 布局 ImageView
     */
    private void layoutChildrenView() {
        if (mImgDataList == null || mImgDataList.isEmpty()) {
            return;
        }
        int childrenCount = mImgDataList.size();
        for (int i = 0; i < childrenCount; i++) {
            ImageView childrenView = (ImageView) getChildAt(i);
            if (mAdapter != null) {
                mAdapter.onDisplayImage(getContext(), childrenView, mImgDataList.get(i));
            }
            int rowNum = i / mColumnCount;
            int columnNum = i % mColumnCount;
            int left = (mGridSizeWidth + mGap) * columnNum + getPaddingLeft();
            int top = (mGridSizeHeight + mGap) * rowNum + getPaddingTop();
            int right = left + mGridSizeWidth;
            int bottom = top + mGridSizeHeight;

            childrenView.layout(left, top, right, bottom);
        }
    }
    public void setImagesData(T[] ts) {
        if (ts != null && ts.length > 0) {
            setImagesData(Arrays.asList(ts));
        } else {
            this.setVisibility(GONE);
        }
    }

    /**
     * 设置图片数据
     *
     * @param lists 图片数据集合
     */
    public void setImagesData(List lists) {
        if (lists == null || lists.isEmpty()) {
            this.setVisibility(GONE);
            return;
        }

        this.setVisibility(VISIBLE);
        int imageCount = Math.min(mMaxSize, lists.size());
        lists = lists.subList(0, imageCount);
//        if (imageCount > mMaxSize) {
//            lists = lists.subList(0, mMaxSize);
//        }
//        if (imageCount < mNumColumns) {
//            mRowCount = 1;
//        } else {
//            mRowCount = imageCount / mNumColumns + (imageCount % mNumColumns == 0 ? 0 : 1);
//        }
        mRowCount = imageCount / mNumColumns + (imageCount % mNumColumns == 0 ? 0 : 1);
        mColumnCount = mNumColumns;
        Log.e("NineGirdImage", "mMaxSize = " + mMaxSize + ",imageCount = " +
                imageCount + ",mRowCount = " + mRowCount + " , mColumnCount = " + mColumnCount);
        if (mImgDataList == null) {
            int i = 0;
            while (i < lists.size()) {
                ImageView iv = getImageView(i);
                if (iv == null) {
                    return;
                }
                addView(iv, generateDefaultLayoutParams());
                i++;
            }
        } else {
            int oldViewCount = mImgDataList.size();
            int newViewCount = lists.size();
            if (oldViewCount > newViewCount) {
                removeViews(newViewCount, oldViewCount - newViewCount);
            } else if (oldViewCount < newViewCount) {
                for (int i = oldViewCount; i < newViewCount; i++) {
                    ImageView iv = getImageView(i);
                    if (iv == null) {
                        return;
                    }
                    addView(iv, generateDefaultLayoutParams());
                }
            }
        }
        mImgDataList = lists;
        requestLayout();
    }

    /**
     * 获得 ImageView
     * 保证了 ImageView 的重用
     *
     * @param position 位置
     */
    private ImageView getImageView(final int position) {
        if (position < mImageViewList.size()) {
            return mImageViewList.get(position);
        } else {
            if (mAdapter != null) {
                ImageView imageView = mAdapter.generateImageView(getContext());
                mImageViewList.add(imageView);
                imageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAdapter.onItemImageClick(getContext(), position, mImgDataList);
                    }
                });
                return imageView;
            } else {
                Log.e("NineGirdImageView", "Your must set a NineGridImageViewAdapter for NineGirdImageView");
                return null;
            }
        }
    }


    /**
     * 设置适配器
     *
     * @param adapter 适配器
     */
    public void setAdapter(NineGridImageViewAdapter adapter) {
        mAdapter = adapter;
    }

    /**
     * 设置宫格间距
     *
     * @param gap 宫格间距 px
     */
    public void setGap(int gap) {
        mGap = gap;
    }

    /**
     * 设置只有一张图片时的尺寸大小
     *
     * @param singleImgSize 单张图片的尺寸大小
     */
    public void setSingleImgSize(int singleImgSize) {
        mSingleImgSize = singleImgSize;
    }

    /**
     * 设置最大图片数
     *
     * @param maxSize 最大图片数
     */
    public void setMaxSize(int maxSize) {
        mMaxSize = maxSize;
    }
}