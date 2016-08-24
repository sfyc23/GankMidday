package com.sfyc23.gankDaily.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.sfyc23.gankDaily.R;
import com.sfyc23.gankDaily.logic.ui.adapter.CommonRvAdapter;
import com.sfyc23.gankDaily.logic.model.GanHuoDataBean;
import com.sfyc23.gankDaily.logic.ui.adapter.ViewHolder;

import java.util.List;

/**
 * Created by leilei on 2016/8/22.
 */
public class CategoryAdapter extends CommonRvAdapter<GanHuoDataBean>{

    public CategoryAdapter(Context context, List<GanHuoDataBean> beans) {
        super(context, beans);
    }

    @Override
    public int getLayoutID(int viewType) {
        return R.layout.list_item_ganhuo;
    }

    @Override
    public void getItemView(ViewHolder holder, GanHuoDataBean bean, int position) {
        TextView tvTitle = holder.getView(R.id.tv_item_title);
        TextView tvReferrer = holder.getView(R.id.tv_item_referrer);
        TextView tvTime = holder.getView(R.id.tv_item_time);
        TextView tvTag = holder.getView(R.id.tv_item_tag);

        String date = bean.getPublishedAt().replace('T', ' ').replace('Z', ' ');
        tvTitle.setText(bean.getDesc());
        tvReferrer.setText(bean.getWho());
        tvTime.setText(date);
    }


//    @Override
//    protected void onItemClick(int position) {
////        super.onItemClick(position);
//        String url = mBeans.get(position - 1).getUrl();
//        Intent intent = new Intent(mContext, WebViewActivity.class);
//        intent.putExtra(WebViewActivity.WEB_URL, url);
//        intent.putExtra(WebViewActivity.TITLE, mBeans.get(position - 1).getDesc());
//        mContext.startActivity(intent);
//    }
}
