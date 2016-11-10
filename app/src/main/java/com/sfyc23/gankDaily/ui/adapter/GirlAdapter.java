package com.sfyc23.gankDaily.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.sfyc23.gankDaily.R;
import com.sfyc23.gankDaily.logic.model.GankDataBean;
import com.sfyc23.gankDaily.logic.ui.adapter.CommonRvAdapter;
import com.sfyc23.gankDaily.logic.ui.adapter.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by leilei on 2016/8/24.
 */
public class GirlAdapter  extends CommonRvAdapter<GankDataBean> {

    public GirlAdapter(Context context, List<GankDataBean> datas) {
        super(context, datas);
    }

    @Override
    public int getLayoutID(int viewType) {
        return R.layout.list_item_girl;
    }

    @Override
    public void getItemView(ViewHolder holder, GankDataBean bean, int position) {
        ImageView girlIv = holder.getView(R.id.iv_item_girl);
//        Glide.with(mContext)
//                .load(bean.getUrl())
//                .centerCrop()
////                .placeholder(R.drawable)
//                .crossFade()
//                .into(girlIv);
        Picasso.with(mContext).load(bean.getUrl()).into(girlIv);
    }
}
