package com.example.lvkaixue.appmeager.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.adapter.ZhuanFaAdapter;
import com.example.lvkaixue.appmeager.base.BaseFragment;
import com.example.lvkaixue.appmeager.base.BasePager;
import com.example.lvkaixue.appmeager.bean.ZhuanFaBean;
import com.example.lvkaixue.appmeager.protocol.childprotcol.ZhuanFaProtocol;
import com.example.lvkaixue.appmeager.utils.HandlerUtils;

/**
 * Created by lvkaixue on 2016/9/8.
 */
public class ForwardPage extends BaseFragment {
    private View mContextView;
    private ZhuanFaBean zhuanFa;
    private ListView mListView;
    private ZhuanFaAdapter mZhuanFaApdater;

    @Override
    protected View initToolBarView() {
        return null;
    }

    @Override
    public BasePager.ResultState getResultState() {
        if(HandlerUtils.checkNetWork()){
            zhuanFa = new ZhuanFaProtocol("1").GetPost();
            if(zhuanFa != null && zhuanFa.object!=null){
                return BasePager.ResultState.SUCCESS;
            }else {
                return BasePager.ResultState.EMPTY;
            }
        }
        return BasePager.ResultState.ERROR;
    }

    @Override
    public View initSuccessView() {
        mContextView = View.inflate(mContext, R.layout.comment_item, null);
        mListView = (ListView) mContextView.findViewById(R.id.lv_item);
        setAdapter();
        return mContextView;
    }

    private void setAdapter(){
        if(zhuanFa.object != null){
            if(mZhuanFaApdater == null){
                mZhuanFaApdater = new ZhuanFaAdapter(zhuanFa.object,mContext);
                mListView.setAdapter(mZhuanFaApdater);
            }else {
                mZhuanFaApdater.notifyData(zhuanFa.object);
            }
        }
    }
}
