package com.example.lvkaixue.appmeager.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.base.BaseFragment;
import com.example.lvkaixue.appmeager.base.BasePager;
import com.example.lvkaixue.appmeager.bean.ZanBean;
import com.example.lvkaixue.appmeager.protocol.childprotcol.ZanProtocol;
import com.example.lvkaixue.appmeager.utils.HandlerUtils;

/**
 * Created by lvkaixue on 2016/9/8.
 */
public class ZambiaPage extends BaseFragment {
    private View mContextView;
    private ZanBean zan;

    @Override
    protected View initToolBarView() {
        return null;
    }

    @Override
    public BasePager.ResultState getResultState() {
        if(HandlerUtils.checkNetWork()){
            zan = new ZanProtocol("1").GetPost();
            if(zan != null && zan.object!=null){
                return BasePager.ResultState.SUCCESS;
            }else {
                return BasePager.ResultState.EMPTY;
            }
        }
        return BasePager.ResultState.ERROR;
    }

    @Override
    public View initSuccessView() {
        mContextView = View.inflate(mContext, R.layout.zambia_item, null);
        TextView textView = (TextView) mContextView.findViewById(R.id.text_title);
        textView.setText("没有任何人秒赞!");
        return mContextView;
    }
}
