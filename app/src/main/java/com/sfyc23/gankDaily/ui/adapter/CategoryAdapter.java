package com.sfyc23.gankDaily.ui.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.sfyc23.gankDaily.R;
import com.sfyc23.gankDaily.base.utils.CommonUtils;
import com.sfyc23.gankDaily.base.utils.TimeUtils;
import com.sfyc23.gankDaily.logic.ui.adapter.CommonRvAdapter;
import com.sfyc23.gankDaily.logic.model.GankDataBean;
import com.sfyc23.gankDaily.logic.ui.adapter.ViewHolder;
import com.sfyc23.gankDaily.logic.widget.gridImageView.NineGridImageView;
import com.sfyc23.gankDaily.logic.widget.gridImageView.NineGridImageViewAdapter;
import com.sfyc23.gankDaily.ui.activity.PictureActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by leilei on 2016/8/22.
 */
public class CategoryAdapter extends CommonRvAdapter<GankDataBean> {
    protected Activity mActivity;
    public CategoryAdapter(Context context,Activity activity ,List<GankDataBean> beans) {
        super(context, beans);
        this.mActivity = activity;
    }

    @Override
    public int getLayoutID(int viewType) {
        return R.layout.list_item_gank_common;
    }

    @Override
    public void getItemView(ViewHolder holder, GankDataBean bean, int position) {

        holder.setText(R.id.tv_item_title, bean.getDesc());

        holder.setText(R.id.tv_item_time, TimeUtils.getTimeFormatText2(bean.getPublishedAt()));


        String referrer = bean.getWho();
        if (CommonUtils.isValid(referrer)) {
            holder.setVisible(R.id.tv_item_referrer, true);
            holder.setText(R.id.tv_item_referrer, bean.getWho());
        } else {
            holder.setVisible(R.id.tv_item_referrer, false);
        }
        NineGridImageView ngiv = holder.getView(R.id.ngiv_item_image);

        NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, String url) {
                Picasso.with(mContext).load(url).into(imageView);
            }

            @Override
            protected void onItemImageClick(Context context, View view, int index, List<String> list) {

                Intent intent = PictureActivity.createStartIntent(mContext, list.get(index), "什么玩意");
                ActivityOptions options =
                        ActivityOptions.makeSceneTransitionAnimation(mActivity,
                                Pair.create(view, mActivity.getString(R.string
                                        .transition_name_picture)));
                ActivityCompat.startActivity(mActivity, intent, options.toBundle());
//                Intent intent = PictureActivity.createStartIntent(mContext, list.get(index), "什么玩意");
//                mActivity.startActivity(intent);
            }
        };
        ngiv.setAdapter(mAdapter);
        ngiv.setImagesData(bean.images);

    }
}
