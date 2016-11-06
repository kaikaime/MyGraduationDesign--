package com.example.lvkaixue.appmeager.fragment;

import android.view.View;
import android.widget.ListView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.adapter.PinlunAdapter;
import com.example.lvkaixue.appmeager.base.BaseFragment;
import com.example.lvkaixue.appmeager.base.BasePager;
import com.example.lvkaixue.appmeager.bean.PingLunBean;
import com.example.lvkaixue.appmeager.protocol.childprotcol.PingLunProtocel;
import com.example.lvkaixue.appmeager.utils.HandlerUtils;

/**
 * Created by lvkaixue on 2016/9/8.
 */
public class CommentPage extends BaseFragment {
    private View mContextView;
    private PingLunBean pingLun;
    private ListView mListView;
    private PinlunAdapter pinlunAdapter;

    @Override
    protected View initToolBarView() {
        return null;
    }

    @Override
    public BasePager.ResultState getResultState() {
        if(HandlerUtils.checkNetWork()){
            pingLun = new PingLunProtocel("1").GetPost();
            if(pingLun != null && pingLun.object!=null){
                return BasePager.ResultState.SUCCESS;
            }else {
                return BasePager.ResultState.EMPTY;
            }
        }
        return BasePager.ResultState.SUCCESS;
    }

    @Override
    public View initSuccessView() {
        mContextView = View.inflate(mContext, R.layout.comment_item, null);
        mListView = (ListView) mContextView.findViewById(R.id.lv_item);
        setAdapter();
        return mContextView;
    }

    private void setAdapter(){
        if(pingLun.object != null){
            if(pinlunAdapter == null){
               pinlunAdapter = new PinlunAdapter(pingLun.object,mContext);
                mListView.setAdapter(pinlunAdapter);
            }else {
                pinlunAdapter.notifyData(pingLun.object);
            }
        }
    }
}
