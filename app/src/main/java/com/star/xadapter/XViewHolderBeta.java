package com.star.xadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by star on 16/7/6.
 * Beta版本的 XViewHolderBeta 测试类
 */
public class XViewHolderBeta extends RecyclerView.ViewHolder{
    TextView mTv;

    public XViewHolderBeta(View itemView) {
        super(itemView);
        mTv = (TextView) itemView.findViewById(R.id.tv);
    }

    public XViewHolderBeta(ViewGroup parent, int layoutId){
        this(View.inflate(parent.getContext(), layoutId, null));
    }
}
