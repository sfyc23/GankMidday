package com.sfyc23.gankDaily.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.sfyc23.gankDaily.base.ui.UiThreadHandler;
import com.sfyc23.gankDaily.base.utils.LogUtil;
import com.sfyc23.gankDaily.base.utils.NetUtils;
import com.sfyc23.gankDaily.logic.ui.adapter.CommonRvAdapter;
import com.sfyc23.gankDaily.android.XRecyclerViewFragment;
import com.sfyc23.gankDaily.base.utils.json.JsonConvert;
import com.sfyc23.gankDaily.logic.model.GankDataBean;
import com.sfyc23.gankDaily.logic.network.Apis;
import com.sfyc23.gankDaily.logic.ui.adapter.OnItemClickListener;
import com.sfyc23.gankDaily.ui.activity.WebViewActivity;
import com.sfyc23.gankDaily.ui.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leilei on 2016/8/22.
 */
public class CategoryListFragment extends XRecyclerViewFragment<GankDataBean> {
    private String mType;
    private boolean mBeginLoad;


    public static CategoryListFragment newInstance(String type, boolean beginLoad) {
        CategoryListFragment fragment = new CategoryListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putBoolean("beginLoad", beginLoad);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void getData() {
        mBeginLoad = getArguments().getBoolean("beginLoad");
        if (mBeginLoad) {

            if (NetUtils.isAvailable(getActivity())) {
                mStatusView.showLoading();
                UiThreadHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadData();
                    }
                }, 500);
            } else {
                mType = getArguments().getString("type");
                List<GankDataBean> mas = liteOrm.query(new QueryBuilder(
                        GankDataBean.class).whereEquals("type", mType)
                        .limit(0, 10));
                if (mas != null && mas.size() > 0) {
                    mAdapter.replaceAll(mas);
                    mStatusView.showContent();
                }
            }


        } else {
            mType = getArguments().getString("type");
            List<GankDataBean> mas = liteOrm.query(new QueryBuilder(
                    GankDataBean.class).whereEquals("type", mType)
                    .limit(0, 10));
            if (mas != null && mas.size() > 0) {
                mAdapter.replaceAll(mas);
                mStatusView.showContent();
            }

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

        JsonConvert<List<GankDataBean>> jsonConvert = new JsonConvert<List<GankDataBean>>() {};
        jsonConvert.setDataName("results");
        List<GankDataBean> list = jsonConvert.parseData(result);
        if (list == null) {
            list = new ArrayList<>();
        }
        if (mCurrentPageIndex == 1) {
            saveDataToDd(list);
        }
        return list;
    }

    private void saveDataToDd(final List<GankDataBean> list) {
        UiThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (list.size() > 0) {
//                    String type = list.get(0).getType();
                    liteOrm.delete(WhereBuilder.create(GankDataBean.class).andEquals("type", mType));
                    liteOrm.save(list);
                }
            }
        });
    }

    @Override
    protected CommonRvAdapter setAdapter() {
        CategoryAdapter categoryAdapter =
                new CategoryAdapter(mContext, getActivity(), new ArrayList<GankDataBean>());
        categoryAdapter.setOnItemClickListener(new OnItemClickListener<GankDataBean>() {
            @Override
            public void onItemClick(ViewGroup parent, View view, GankDataBean chdb, int position) {
                String url = chdb.getUrl();
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra(WebViewActivity.WEB_URL, url);
                intent.putExtra(WebViewActivity.TITLE, chdb.getDesc());
                mContext.startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, GankDataBean chdb, int position) {
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
