package com.example.lvkaixue.appmeager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by kaikaime on 2016/10/30.
 */
public abstract class ListBaseAdapter<T> extends BaseAdapter {
    public ArrayList<T> mArray ;
    public Context context;
    public ListBaseAdapter(ArrayList<T> mArray,Context context){
        this.mArray = mArray;
        this.context = context;
    }

    public void notifyData(ArrayList<T> mArray){
        this.mArray = mArray;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mArray.size();
    }

    @Override
    public Object getItem(int position) {
        return mArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
