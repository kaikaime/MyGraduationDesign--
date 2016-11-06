package com.example.lvkaixue.appmeager.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.base.BaseFragment;
import com.example.lvkaixue.appmeager.base.BasePager;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
public class FansPage extends BaseFragment {
    @Override
    protected View initToolBarView() {
        return null;
    }

    @Override
    public BasePager.ResultState getResultState() {
        return BasePager.ResultState.SUCCESS;
    }

    @Override
    public View initSuccessView() {
        TextView textView = new TextView(mContext);
        textView.setText(this.getClass().getSimpleName());
        return textView;
    }

}
