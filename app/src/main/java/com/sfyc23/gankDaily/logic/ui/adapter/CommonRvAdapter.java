package com.sfyc23.gankDaily.logic.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leilei on 2016/8/22.
 */
@SuppressWarnings("deprecation")
public abstract class CommonRvAdapter<T>  extends
        RecyclerView.Adapter<ViewHolder> implements IBaseAdapter<T>{

    protected List<T> mDatas;
    protected Context mContext;

    public CommonRvAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas == null ? new ArrayList<T>() : new ArrayList<T>(datas);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        View view = inflater.inflate(getLayoutID(viewType), parent, false);
//        CommonRvViewHolder holder = new CommonRvViewHolder(view);
        ViewHolder viewHolder = ViewHolder.get(mContext, null, parent, getLayoutID(viewType), -1);
        setListener(parent, viewHolder, viewType);
        return viewHolder;
    }

//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        getItemView(holder, mDatas.get(position), position);
//    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.updatePosition(position);
        getItemView(holder,mDatas.get(position),position);
    }



    protected boolean isEnabled(int viewType) {
        return true;
    }


    protected void setListener(final ViewGroup parent, final ViewHolder viewHolder, int viewType) {
        if (!isEnabled(viewType)) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = getPosition(viewHolder);
                    //TODO 位置-1吗
                    mOnItemClickListener.onItemClick(parent, v, mDatas.get(position-1), position-1);
                }
            }
        });


        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = getPosition(viewHolder);
                    return mOnItemClickListener.onItemLongClick(parent, v, mDatas.get(position-1), position-1);
                }
                return false;
            }
        });
    }

    protected int getPosition(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 取得ItemView的布局文件
     *
     * @return
     */
    public abstract int getLayoutID(int viewType);

    /**
     * 绑定数据到Item的控件中去
     *
     * @param holder
     * @param bean
     */
    public abstract void getItemView(ViewHolder holder, T bean, int position);



    @Override
    public void add(T elem) {
        mDatas.add(elem);
        notifyDataSetChanged();
    }

    @Override
    public void add(T elem, int index) {
        mDatas.add(index, elem);
        notifyDataSetChanged();
    }

    @Override
    public void addAll(List<T> elem) {
        mDatas.addAll(elem);
        notifyDataSetChanged();
    }

    @Override
    public void replaceAll(List<T> elem) {
        mDatas.clear();
        mDatas.addAll(elem);
        notifyDataSetChanged();
    }


    /**清空当前数据*/
    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    /**
     * 移除某一项，根据具体位置
     * @param index
     */
    @Override
    public void remove(int index) {
        if (mDatas.size() >= index && index >= 0) {
            mDatas.remove(index);
            notifyDataSetChanged();
        }
    }
    /*
    * 移除某一项,根据具体某项
    * @param elem
    */
    @Override
    public void remove(T elem) {
        if (elem != null) {
            mDatas.remove(elem);
            notifyDataSetChanged();
        }
    }

    @Override
    public boolean isEmpty() {
        return mDatas.isEmpty();
    }

    @Override
    public List<T> getData() {
        return mDatas;
    }
}
