package com.sfyc23.gankDaily.logic.widget.gridImageView;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.sfyc23.gankDaily.R;
import com.sfyc23.gankDaily.logic.widget.ForegroundImageView;

import java.util.List;

/**
 * 原项目地址GitHub: https://github.com/laobie
 */
public abstract class NineGridImageViewAdapter<T> {
    protected abstract void onDisplayImage(Context context, ImageView imageView, T t);

    protected void onItemImageClick(Context context, View view, int index, List<T> list) {
    }

    protected ImageView generateImageView(Context context) {
//        ImageView imageView = new ImageView(context);
        ImageView imageView = (ImageView) View.inflate(context, R.layout.list_item_mid_image, null);
//        ForegroundImageView imageView = new ForegroundImageView(context);
//        imageView.setForeground(context.getResources().getDrawable(R.drawable.mid_grey_ripple,null));
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }
}