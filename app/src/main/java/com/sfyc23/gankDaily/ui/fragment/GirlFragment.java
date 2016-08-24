package com.sfyc23.gankDaily.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.sfyc23.gankDaily.R;
import com.sfyc23.gankDaily.android.BaseFragment;
import com.sfyc23.gankDaily.android.XRecyclerViewFragment;
import com.sfyc23.gankDaily.base.utils.json.JsonConvert;
import com.sfyc23.gankDaily.logic.model.GanHuoDataBean;
import com.sfyc23.gankDaily.logic.network.Apis;
import com.sfyc23.gankDaily.logic.ui.adapter.CommonRvAdapter;
import com.sfyc23.gankDaily.logic.ui.adapter.OnItemClickListener;
import com.sfyc23.gankDaily.ui.activity.MainActivity;
import com.sfyc23.gankDaily.ui.activity.PictureActivity;
import com.sfyc23.gankDaily.ui.activity.WebViewActivity;
import com.sfyc23.gankDaily.ui.adapter.CategoryAdapter;
import com.sfyc23.gankDaily.ui.adapter.GirlAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leilei on 2016/8/16.
 * 妹子福利页面
 */
public class GirlFragment  extends XRecyclerViewFragment<GanHuoDataBean> {


    public static GirlFragment newInstance() {
        GirlFragment fragment = new GirlFragment();
        return fragment;
    }

    @Override
    protected String getUrl(int mCurrentPageIndex) {

        String url = Apis.Urls.GanHuoData +"福利/10/" + mCurrentPageIndex;
        return url;
    }

    @Override
    protected List parseData(String result) {
        List<GanHuoDataBean> list;
        JsonConvert<List<GanHuoDataBean>> jsonConvert = new JsonConvert<List<GanHuoDataBean>>() {
        };
        jsonConvert.setDataName("results");
        list = jsonConvert.parseData(result);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    protected CommonRvAdapter setAdapter() {
        GirlAdapter categoryAdapter =
                new GirlAdapter(mContext, new ArrayList<GanHuoDataBean>());
        categoryAdapter.setOnItemClickListener(new OnItemClickListener<GanHuoDataBean>() {
            @Override
            public void onItemClick(ViewGroup parent, View view, GanHuoDataBean chdb, int position) {
                String url = chdb.getUrl();
//                Intent intent = new Intent(mContext, WebViewActivity.class);
//                intent.putExtra(WebViewActivity.WEB_URL, url);
//                intent.putExtra(WebViewActivity.TITLE, chdb.getDesc());
//                mContext.startActivity(intent);

                Intent intent = PictureActivity.newIntent(mContext, chdb.getUrl(), chdb.getDesc());
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(), view, PictureActivity.TRANSIT_PIC);
                try {
                    ActivityCompat.startActivity(getActivity(), intent, optionsCompat.toBundle());
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    startActivity(intent);
                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, GanHuoDataBean chdb, int position) {
                return false;
            }
        });
        return categoryAdapter;
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }
}
