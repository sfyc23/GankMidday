package com.sfyc23.gankDaily.logic.ui.adapter;

import java.util.List;

/**
 * Created by leilei on 2016/6/20.
 */
public interface IBaseAdapter<T> {
    /**
     * 判断数据是否为空
     *
     * @return 为空返回true，不为空返回false
     */
    public boolean isEmpty();

    /**
     * 增加集合
     * @param elem
     */
    public void addAll(List<T> elem);

    /**
     * 移除某一项,根据具体某项
     * @param elem
     */
    public void remove(T elem);

    /**
     * 移除某一项，根据具体位置
     * @param index
     */
    public void remove(int index);


    /**
     * 单独增加一项，在顶部
     * @param elem 类。
     */
    public void add(T elem);

    /**
     * 单独增加一项，在对应的位置
     * @param elem
     */
    public void add(T elem, int index);

    /**
     * 更新替换所有的数据
     * @param elem
     */
    public void replaceAll(List<T> elem);

    /**清空当前数据*/
    public void clear();

    public List<T> getData();

}
