package com.sfyc23.gankDaily.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sfyc23.gankDaily.R;
import com.sfyc23.gankDaily.android.MyApplication;
import com.sfyc23.gankDaily.base.utils.CommonUtils;
import com.sfyc23.gankDaily.base.utils.LogUtil;
import com.sfyc23.gankDaily.base.utils.TimeUtils;
import com.sfyc23.gankDaily.logic.ui.adapter.CommonRvAdapter;
import com.sfyc23.gankDaily.logic.model.GanHuoDataBean;
import com.sfyc23.gankDaily.logic.ui.adapter.ViewHolder;
import com.sfyc23.gankDaily.logic.widget.gridImageView.NineGridImageView;
import com.sfyc23.gankDaily.logic.widget.gridImageView.NineGridImageViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

/**
 * Created by leilei on 2016/8/22.
 */
public class CategoryAdapter extends CommonRvAdapter<GanHuoDataBean> {

    public CategoryAdapter(Context context, List<GanHuoDataBean> beans) {
        super(context, beans);
    }

    @Override
    public int getLayoutID(int viewType) {
        return R.layout.list_item_gank_common;
    }

    @Override
    public void getItemView(ViewHolder holder, GanHuoDataBean bean, int position) {

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
        };
        ngiv.setAdapter(mAdapter);
        ngiv.setImagesData(bean.images);
    }
}
