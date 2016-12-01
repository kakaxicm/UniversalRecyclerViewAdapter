package com.star.xadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by star on 16/7/6.
 */
public class UniversalAdapter<T> extends RecyclerView.Adapter<UniversalViewHolder>{
    private List<T> mData;

    public UniversalAdapter(List<T> data , OnBindDataInterface<T> bindInterface){
        mData = data;
        mOnBindDataInterface = bindInterface;
    }

    public UniversalAdapter(List<T> data , OnMultiTypeBindDataInterface<T> bindInterface){
        mData = data;
        mOnMultiTypeBindDataInterface = bindInterface;
        mOnBindDataInterface = bindInterface;
    }

    /**
     * 绑定数据的接口
     * @param <T> model
     */
    interface OnBindDataInterface<T>{
        void onBindData(T model, UniversalViewHolder hodler, int type);
        int getItemLayoutId(int viewType);
    }

    /**
     * 多类型支持
     * @param <T>
     */
    interface OnMultiTypeBindDataInterface<T> extends OnBindDataInterface<T>{
        int getItemViewType(int postion);
    }

    private OnBindDataInterface<T> mOnBindDataInterface;
    private OnMultiTypeBindDataInterface<T> mOnMultiTypeBindDataInterface;

    @Override
    public int getItemViewType(int position) {
        if(mOnMultiTypeBindDataInterface != null){
            return mOnMultiTypeBindDataInterface.getItemViewType(position);
        }
        return 0;
    }

    @Override
    public UniversalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //构建ViewHolder 入参 parant viewType layoutid
        int layoutId = mOnBindDataInterface.getItemLayoutId(viewType);
        UniversalViewHolder holder = UniversalViewHolder.getViewHolder(parent, layoutId);
        return holder;
    }

    @Override
    public void onBindViewHolder(UniversalViewHolder holder, int position) {
        mOnBindDataInterface.onBindData(mData.get(position), holder, getItemViewType(position));
    }

    @Override
    public int getItemCount() {
        return mData == null? 0 : mData.size();
    }

}
