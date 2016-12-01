package com.star.xadapter;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by star on 16/7/6.
 *
 */
public class UniversalViewHolder extends RecyclerView.ViewHolder{
    private SparseArray<View> mViews;
    private View mContentView;

    public static UniversalViewHolder getViewHolder(ViewGroup parent, int layoutId)
    {
        return new UniversalViewHolder(View.inflate(parent.getContext(), layoutId, null));
    }

    public UniversalViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
        mContentView = itemView;
    }

    public <T extends View> T getSubView(int viewId){
        View view = mViews.get(viewId);
        if(view == null){
            view = mContentView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T)view;
    }

}
