package com.star.xadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by star on 16/7/6.
 * 该适配器是通用适配器演化的第一阶段，思想是把View层的Holder(H) 和 Model(T)分开,
 * 这种适配器把model和viewholder都暴露给用户来构造细想一下，viewholder的构造最关
 * 键的只有一个参数:布局文件id，而至于这个布局文件中有哪些view需要和model绑定,都交
 * 给viewholder封装即可，这样下一步的演化目标就是把Viweholder(即泛型H)的构造隐藏
 * 起来，用户只需要提供layoutid即可，关于下一步的演化看代码XAdapterBeta2和XViewHolderBeta2
 */
public class XAdapterBeta<T, H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<H>{
    private List<T> mData;

    public XAdapterBeta(List<T> data , OnBindDataInterface<T, H> bindInterface){
        mData = data;
        mOnBindDataInterface = bindInterface;
    }

    /**
     * 绑定数据的接口
     * @param <T> model
     * @param <H> viewholder
     */
    interface OnBindDataInterface<T, H extends RecyclerView.ViewHolder>{
        void onBindData(T model, H holder, int viewType);
        int getItemCount();
        H getViewHolder(ViewGroup parent, int viewType);
    }

    private OnBindDataInterface<T, H> mOnBindDataInterface;

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        //构建ViewHolder 入参 parant viewType layoutid
        return mOnBindDataInterface.getViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(H holder, int position) {
        mOnBindDataInterface.onBindData(mData.get(position), holder, getItemViewType(position));
    }

    @Override
    public int getItemCount() {
        return mOnBindDataInterface.getItemCount();
    }

}
