package com.example.lvkaixue.appmeager.activity;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.adapter.CollectionAdapter;
import com.example.lvkaixue.appmeager.base.BaseActivity;
import com.example.lvkaixue.appmeager.customview.RecycleViewDivider;
import com.example.lvkaixue.appmeager.single.SingleSaveFriendItem;

public class CollectionActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private CollectionAdapter mCollection;

    @Override
    public void initView() {
        setContentView(R.layout.activity_collection);
        mRecyclerView = (RecyclerView) findViewById(R.id.rl_recy);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, 50, Color.BLACK));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        setRecyAdapter();//设置适配器
        setViewOnclick();
    }

    private void setViewOnclick() {
        ((ImageView) findViewById(R.id.goback_iv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setRecyAdapter() {
        if(mCollection == null){
            mCollection = new CollectionAdapter(this,SingleSaveFriendItem.getmSimleArrayMap());
            mRecyclerView.setAdapter(mCollection);
        }else{
           mCollection.setNotifyAdapterArrayData(SingleSaveFriendItem.getmSimleArrayMap());
        }
    }
}
