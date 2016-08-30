package com.sfyc23.gankDaily.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.sfyc23.gankDaily.base.ui.UiThreadHandler;
import com.sfyc23.gankDaily.logic.ui.adapter.CommonRvAdapter;
import com.sfyc23.gankDaily.android.XRecyclerViewFragment;
import com.sfyc23.gankDaily.base.utils.json.JsonConvert;
import com.sfyc23.gankDaily.logic.model.GanHuoDataBean;
import com.sfyc23.gankDaily.logic.network.Apis;
import com.sfyc23.gankDaily.logic.ui.adapter.OnItemClickListener;
import com.sfyc23.gankDaily.ui.activity.WebViewActivity;
import com.sfyc23.gankDaily.ui.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leilei on 2016/8/22.
 */
public class CategoryListFragment extends XRecyclerViewFragment<GanHuoDataBean> {
    private String mType;
    private boolean mBeginLoad;

    public static CategoryListFragment newInstance(String type,boolean beginLoad) {
        CategoryListFragment fragment = new CategoryListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putBoolean("beginLoad",beginLoad);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void getData() {
        mBeginLoad = getArguments().getBoolean("beginLoad");
        if(mBeginLoad){
            mStatusView.showLoading();
            UiThreadHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadData();
                }
            },500);

        }
    }

    @Override
    protected String getUrl(int mCurrentPageIndex) {
        mType = getArguments().getString("type");
        String url = Apis.Urls.GanHuoData + mType + "/10/" + mCurrentPageIndex;
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
        CategoryAdapter categoryAdapter =
                new CategoryAdapter(mContext, new ArrayList<GanHuoDataBean>());
        categoryAdapter.setOnItemClickListener(new OnItemClickListener<GanHuoDataBean>() {
            @Override
            public void onItemClick(ViewGroup parent, View view, GanHuoDataBean chdb, int position) {
                String url = chdb.getUrl();
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra(WebViewActivity.WEB_URL, url);
                intent.putExtra(WebViewActivity.TITLE, chdb.getDesc());
                mContext.startActivity(intent);
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
        return new LinearLayoutManager(mContext);
    }
}
