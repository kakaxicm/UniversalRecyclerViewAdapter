package com.star.xadapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    RecyclerView rcv;
    List<String> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv = (RecyclerView) findViewById(R.id.rcv);
        mData = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            mData.add(""+i);
        }

//        XAdapterBeta<String, XViewHolderBeta> adapter = new XAdapterBeta<>(mData, new XAdapterBeta.OnBindDataInterface<String, XViewHolderBeta>() {
//            @Override
//            public void onBindData(String model, XViewHolderBeta holder, int type) {
//                holder.mTv.setText(model);
//            }
//
//            @Override
//            public int getItemCount() {
//                return mData.size();
//            }
//
//            @Override
//            public XViewHolderBeta getViewHolder(ViewGroup parent, int viewType) {
//                return new XViewHolderBeta(parent, R.layout.item_test);
//            }
//        });

//        UniversalAdapter<String> adapter = new UniversalAdapter(mData, new UniversalAdapter.OnBindDataInterface<String>() {
//            @Override
//            public void onBindData(String model, UniversalViewHolder holder, int type) {
//                TextView tv = holder.getSubView(R.id.tv);
//                tv.setText(model);
//            }
//
//            @Override
//            public int getItemLayoutId(int viewType) {
//                return R.layout.item_test;
//            }
//        });
        UniversalAdapter<String> adapter = new UniversalAdapter<>(mData, new UniversalAdapter.OnMultiTypeBindDataInterface<String>() {
            @Override
            public int getItemViewType(int postion) {
                if(postion % 2 == 0){
                    return 0;
                }
                if(postion % 3 == 0){
                    return 2;
                }
                return 1;
            }

            @Override
            public void onBindData(String model, UniversalViewHolder holder, int type) {
                switch (type){
                    case 0:
                        TextView tv = holder.getSubView(R.id.tv);
                        tv.setText(model);
                        break;
                    case 1:
                        break;
                    case 2:
                        Button btn = holder.getSubView(R.id.btn);
                        btn.setText(model);
                        break;
                    default:
                        break;
                }

            }

            @Override
            public int getItemLayoutId(int viewType) {
                switch (viewType){
                    case 0:
                        return R.layout.item_test;
                    case 1:
                        return R.layout.item_test2;
                    case 2:
                        return R.layout.item_test3;
                }
                return 0;
            }
        });

        LinearLayoutManager lm  = new GridLayoutManager(this, 2);
        rcv.setLayoutManager(lm);
        rcv.setAdapter(adapter);
    }
}
