package com.example.lvkaixue.appmeager.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Z：HashMap集合中对象元素名称
 * L:HashMap集合中对象元素
 * E:ArrayList集合中的对象元素
 */
public  abstract class BaseRecyAdapter<T,V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {
    public Context mContext;
    public ArrayList<T> mArrayList;
    public BaseRecyAdapter(Context mContext,ArrayList<T> mArrayList){
        this.mContext = mContext;
        this.mArrayList = mArrayList;
    }

    public void notifyData(ArrayList<T> arrayList){
        this.mArrayList = arrayList;
    }
    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        return createViewHolder(mContext);
    }

    protected abstract V createViewHolder(Context mContext);

    @Override
    public abstract void onBindViewHolder(V holder, int position);

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }
}
